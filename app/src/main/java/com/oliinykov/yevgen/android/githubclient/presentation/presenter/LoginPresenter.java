package com.oliinykov.yevgen.android.githubclient.presentation.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationRequestBody;
import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationResponseBody;
import com.oliinykov.yevgen.android.githubclient.domain.interactor.LoginInteractor;
import com.oliinykov.yevgen.android.githubclient.presentation.view.LoginView;
import com.oliinykov.yevgen.android.githubclient.utils.PrefHelper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * {@link Presenter} that controls communication between views and models.
 */
public class LoginPresenter implements Presenter {

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;
    private Context mContext;
    private String mUsername;

    public LoginPresenter(LoginView loginView, Context context) {
        this.mLoginView = loginView;
        this.mLoginInteractor = new LoginInteractor();
        this.mContext = context;
    }

    public void validateCredentials(String username, String password) {
        boolean cancel = false;
        mLoginView.showProgress(true);
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || password.length() < 7) {
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(username) || username.length() < 3) {
            cancel = true;
        }
        if (cancel) {
            mLoginView.showProgress(false);
        } else {
            mUsername = username;
            AuthorizationRequestBody requestBody = buildRequestBody();
            mLoginInteractor.attemptLogin(username, password, requestBody, new Callback<AuthorizationResponseBody>() {
                @Override
                public void success(AuthorizationResponseBody responseBody, Response response) {
                    PrefHelper.writeIntToPreferences(mContext, PrefHelper.PREFS_AUTH_ID_KEY, responseBody.getId());
                    PrefHelper.writeStringToPreferences(mContext, PrefHelper.PREFS_AUTH_TOKEN_KEY,
                            responseBody.getToken());
                    PrefHelper.writeStringToPreferences(mContext, PrefHelper.PREFS_USERNAME_KEY, mUsername);
                    mLoginView.navigateToMain();
                }

                @Override
                public void failure(RetrofitError error) {
                    mLoginView.showProgress(false);
                    mLoginView.showErrorMessage(error.getMessage());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    private AuthorizationRequestBody buildRequestBody() {
        String tokenNote = mContext.getString(R.string.token_params_note);
        String[] tokenScope = mContext.getResources().getStringArray(R.array.token_params_scopes);
        return new AuthorizationRequestBody(
                tokenNote,
                tokenScope
        );
    }

}
