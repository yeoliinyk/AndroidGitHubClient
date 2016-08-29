package com.oliinykov.yevgen.android.githubclient.presentation.view;

import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.Collection;

/**
 * Interface (V in MVP pattern) representing a list of {@link Repo}.
 */
public interface RepoListView extends DataLoadView {

    void renderReposList(Collection<Repo> reposCollection);

}
