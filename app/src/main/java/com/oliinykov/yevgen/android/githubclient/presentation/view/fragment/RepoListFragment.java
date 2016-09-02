package com.oliinykov.yevgen.android.githubclient.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.presentation.ClientApplication;
import com.oliinykov.yevgen.android.githubclient.presentation.view.RepoListView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.adapter.RepoAdapter;
import com.oliinykov.yevgen.android.githubclient.presentation.view.adapter.SimpleLineDivider;
import com.squareup.leakcanary.RefWatcher;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment that represents list of repositories.
 */
public class RepoListFragment extends Fragment implements RepoListView {

    @BindView(R.id.rv_repo_list) RecyclerView mRepoListView;
    private RepoAdapter mRepoAdapter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_repo_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRepoListView.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ClientApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public void renderRepoList(Collection<Repo> repoCollection) {
        mRepoAdapter.setRepoCollection(repoCollection);
    }

    private void setupRecyclerView() {
        mRepoListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRepoListView.addItemDecoration(new SimpleLineDivider(getActivity()));
        mRepoAdapter = new RepoAdapter(getActivity().getApplicationContext());
        mRepoListView.setAdapter(mRepoAdapter);
    }
}
