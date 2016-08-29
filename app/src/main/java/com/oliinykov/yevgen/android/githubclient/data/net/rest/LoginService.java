package com.oliinykov.yevgen.android.githubclient.data.net.rest;

import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationRequestBody;
import com.oliinykov.yevgen.android.githubclient.domain.entity.AuthorizationResponseBody;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * RestApi for retrieving authorization from the GitHub API.
 */
public interface LoginService {

    @POST("/authorizations")
    void requestUserToken(@Body AuthorizationRequestBody requestBody, Callback<AuthorizationResponseBody> callback);

}
