package com.oliinykov.yevgen.android.githubclient.domain.interactor;

import com.oliinykov.yevgen.android.githubclient.data.net.ServiceGenerator;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.SignOutService;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by Yevgen Oliinykov on 31-Aug-16.
 */
public class SignOutInteractor {

    public void deleteUserAuthorization(int id, String username, String password, Callback<Response> callback) {
        SignOutService signOutService = ServiceGenerator.createService(SignOutService.class, username, password);
        signOutService.deleteUserAuthorization(id, callback);
    }

}
