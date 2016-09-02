package com.oliinykov.yevgen.android.githubclient.presentation.presenter;

import android.content.Context;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.domain.interactor.RepoListInteractor;
import com.oliinykov.yevgen.android.githubclient.domain.interactor.SignOutInteractor;
import com.oliinykov.yevgen.android.githubclient.presentation.view.MainView;
import com.oliinykov.yevgen.android.githubclient.utils.PrefHelper;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * {@link Presenter} that controls communication between views and models.
 */
public class MainPresenter implements Presenter {

    private MainView mView;
    private Context mContext;
    private RepoListInteractor mUserRepoInteractor;
    private SignOutInteractor mSignOutInteractor;

    public MainPresenter(MainView mainView, Context context) {
        this.mView = mainView;
        this.mContext = context;
        this.mUserRepoInteractor = new RepoListInteractor();
        this.mSignOutInteractor = new SignOutInteractor();
    }

    public void getUserRepoList() {
        mView.showProgress(true);
        String token = PrefHelper.getStringFromPreferences(mContext, PrefHelper.PREFS_AUTH_TOKEN_KEY);
        mUserRepoInteractor.loadUserRepos(token, new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repos, Response response) {
                mView.renderUserRepoList(repos);
                mView.showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                mView.showProgress(false);
                String errorMessage = error.getMessage();
                if (errorMessage.equals(mContext.getString(R.string.error_unauthorized))) {
                    invalidateUserToken();
                    mView.navigateToLogin();
                }
            }
        });
    }


    public void signOut(String password) {
        int authorizationId = PrefHelper.getIntFromPreferences(mContext, PrefHelper.PREFS_AUTH_ID_KEY);
        String username = PrefHelper.getStringFromPreferences(mContext, PrefHelper.PREFS_USERNAME_KEY);
        mSignOutInteractor.deleteUserAuthorization(authorizationId, username, password, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                if (response.getStatus() == 204) {
                    invalidateUserToken();
                    mView.navigateToLogin();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void invalidateUserToken() {
        PrefHelper.removeStringFromPreferences(mContext, PrefHelper.PREFS_AUTH_TOKEN_KEY);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }


}

