package com.oliinykov.yevgen.android.githubclient.domain.interactor;

import com.oliinykov.yevgen.android.githubclient.data.net.ServiceGenerator;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.LoginService;
import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationRequestBody;
import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationResponseBody;

import retrofit.Callback;


/**
 * This class is a use case for performing user authorization.
 */
public class LoginInteractor {

    public void attemptLogin(final String username, final String password, final AuthorizationRequestBody requestBody,
                             final Callback<AuthorizationResponseBody> callback) {
        LoginService loginService = ServiceGenerator.createService(LoginService.class, username, password);
        loginService.requestUserToken(requestBody, callback);
    }
}
