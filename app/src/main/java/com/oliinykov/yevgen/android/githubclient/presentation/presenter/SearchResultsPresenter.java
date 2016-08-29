package com.oliinykov.yevgen.android.githubclient.presentation.presenter;

import com.oliinykov.yevgen.android.githubclient.domain.entity.SearchResponseBody;
import com.oliinykov.yevgen.android.githubclient.domain.interactor.SearchResultsInteractor;
import com.oliinykov.yevgen.android.githubclient.presentation.view.RepoListView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * {@link Presenter} that controls communication between views and models.
 */
public class SearchResultsPresenter implements Presenter {

    private RepoListView mView;
    private SearchResultsInteractor mIntercator;

    public SearchResultsPresenter(RepoListView view) {
        this.mView = view;
        this.mIntercator = new SearchResultsInteractor();
    }


    public void getSearchResultsByBestMatch(String query) {
        mView.showProgress(true);
        mIntercator.loadSearchResults(query, null, null, new Callback<SearchResponseBody>() {
            @Override
            public void success(SearchResponseBody body, Response response) {
                mView.renderReposList(body.getRepos());
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
