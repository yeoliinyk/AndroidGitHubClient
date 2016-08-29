package com.oliinykov.yevgen.android.githubclient.domain.interactor;

import android.util.Log;

import com.oliinykov.yevgen.android.githubclient.data.net.ServiceGenerator;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.RepoService;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.SignOutService;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * This class is a use case for retrieving data related to authorized user {@link Repo} list.
 */
public class MainInteractor {

    public void loadUserRepos(String token, Callback<List<Repo>> callback) {
        RepoService repoService = ServiceGenerator.createService(RepoService.class, token);
        repoService.requestUserRepos(callback);
    }

    public void deleteUserAuthorization(int id, String username, String password, Callback<Response> callback) {
        Log.v(MainInteractor.class.getSimpleName(), "Credentials: " + username + ":" + password);
        SignOutService signOutService = ServiceGenerator.createService(SignOutService.class, username, password);
        signOutService.deleteUserAuthorization(id, callback);
    }
}
