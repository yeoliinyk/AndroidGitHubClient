package com.oliinykov.yevgen.android.githubclient.domain.interactor;

import com.oliinykov.yevgen.android.githubclient.data.net.ServiceGenerator;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.SearchService;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.domain.entity.SearchResponseBody;

import retrofit.Callback;

/**
 * This class is a use case for retrieving GitHub {@link Repo} list by search query.
 */
public class SearchInteractor {

    public void loadSearchResults(String query, String sort, String order, Callback<SearchResponseBody> callback) {
        SearchService searchService = ServiceGenerator.createService(SearchService.class);
        searchService.requestReposSearch(query, sort, order, callback);
    }
}
