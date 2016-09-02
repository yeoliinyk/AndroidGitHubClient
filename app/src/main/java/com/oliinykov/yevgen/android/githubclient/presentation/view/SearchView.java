package com.oliinykov.yevgen.android.githubclient.presentation.view;

import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.Collection;

/**
 * Interface (V in MVP pattern) representing the repos search screen of application.
 */
public interface SearchView extends DataLoadView {

    void renderSearchRepoList(Collection<Repo> searchRepoList);

}
