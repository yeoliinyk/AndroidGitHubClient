package com.oliinykov.yevgen.android.githubclient.data.net.rest;

import com.oliinykov.yevgen.android.githubclient.domain.entity.SearchResponseBody;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * RestApi for retrieving search results from the GitHub API.
 */
public interface SearchService {

    @GET("/search/repositories")
    void requestReposSearch(@Query("q") String query, @Query("sort") String sort, @Query("order") String order,
                            Callback<SearchResponseBody> callback);

}
