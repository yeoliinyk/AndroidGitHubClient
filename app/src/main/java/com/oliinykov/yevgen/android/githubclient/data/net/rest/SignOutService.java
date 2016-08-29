package com.oliinykov.yevgen.android.githubclient.data.net.rest;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.Path;

/**
 * RestApi for revoking user authorization from the GitHub API.
 */
public interface SignOutService {

    @DELETE("/authorizations/{id}")
    void deleteUserAuthorization(@Path("id") int id, Callback<Response> callback);

}
