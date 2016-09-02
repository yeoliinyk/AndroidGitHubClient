package com.oliinykov.yevgen.android.githubclient.presentation.presenter;

import com.oliinykov.yevgen.android.githubclient.domain.entity.SearchResponseBody;
import com.oliinykov.yevgen.android.githubclient.domain.interactor.SearchInteractor;
import com.oliinykov.yevgen.android.githubclient.presentation.view.SearchView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * {@link Presenter} that controls communication between views and models.
 */
public class SearchResultsPresenter implements Presenter {

    private SearchView mView;
    private SearchInteractor mSearchIntercator;

    public SearchResultsPresenter(SearchView view) {
        this.mView = view;
        this.mSearchIntercator = new SearchInteractor();
    }


    public void getSearchResultsByBestMatch(String query) {
        mView.showProgress(true);
        mSearchIntercator.loadSearchResults(query, null, null, new Callback<SearchResponseBody>() {
            @Override
            public void success(SearchResponseBody body, Response response) {
                mView.renderSearchRepoList(body.getRepos());
                mView.showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                mView.showProgress(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

}
