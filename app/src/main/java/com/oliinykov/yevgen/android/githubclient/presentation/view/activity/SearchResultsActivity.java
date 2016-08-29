package com.oliinykov.yevgen.android.githubclient.presentation.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.presentation.presenter.SearchResultsPresenter;
import com.oliinykov.yevgen.android.githubclient.presentation.view.RepoListView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.adapter.RepoAdapter;
import com.oliinykov.yevgen.android.githubclient.presentation.view.adapter.SimpleLineDivider;

import java.util.Collection;

/**
 * A screen that represents the results of the search on GitHub repositories.
 */
public class SearchResultsActivity extends AppCompatActivity implements RepoListView {

    private SearchResultsPresenter mPresenter;
    private RepoAdapter mRepoAdapter;
    private RecyclerView mReposListView;
    private RelativeLayout mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mPresenter = new SearchResultsPresenter(this);
        mProgress = (RelativeLayout) findViewById(R.id.rl_progress);
        mReposListView = (RecyclerView) findViewById(R.id.rv_search_results);
        setupRecyclerView();
        handleIntent(getIntent());
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mPresenter.getSearchResultsByBestMatch(query);
        }
    }

    @Override
    public void renderReposList(Collection<Repo> reposCollection) {
        mRepoAdapter.setReposCollection(reposCollection);
    }

    @Override
    public void showProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    private void setupRecyclerView() {
        mReposListView.setLayoutManager(new LinearLayoutManager(this));
        mReposListView.addItemDecoration(new SimpleLineDivider(this));
        mRepoAdapter = new RepoAdapter(getApplicationContext());
        mReposListView.setAdapter(mRepoAdapter);
    }
}
