package com.oliinykov.yevgen.android.githubclient.data.net.rest;

import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * RestApi for retrieving authorized user repositories from the GitHub API.
 */
public interface RepoService {

    @GET("/user/repos")
    void requestUserRepos(Callback<List<Repo>> callback);

}
