package com.oliinykov.yevgen.android.githubclient.presentation.view.activity;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.presentation.presenter.SearchResultsPresenter;
import com.oliinykov.yevgen.android.githubclient.presentation.view.RepoListView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.SearchView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.fragment.RepoListFragment;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A screen that represents the results of the search on GitHub repositories.
 */
public class SearchResultsActivity extends BaseActivity implements SearchView {

    @BindView(R.id.rl_progress) RelativeLayout mProgress;
    private SearchResultsPresenter mPresenter;
    private RepoListView mSearchReposView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        mPresenter = new SearchResultsPresenter(this);
        if (savedInstanceState == null) {
            mSearchReposView = new RepoListFragment();
            addFragment(R.id.fl_search_fragment_container, (Fragment) mSearchReposView, RepoListFragment.TAG);
        } else {
            mSearchReposView = (RepoListView) getFragmentById(R.id.fl_search_fragment_container);
        }
        handleIntent(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mSearchReposView = null;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v(SearchResultsActivity.class.getSimpleName(), "On new intent. Query = " + query);
            mPresenter.getSearchResultsByBestMatch(query);
        }
    }

    @Override
    public void renderSearchRepoList(Collection<Repo> searchRepoList) {
        mSearchReposView.renderRepoList(searchRepoList);
    }

    @Override
    public void showProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
