package com.oliinykov.yevgen.android.githubclient.presentation.view;

import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.Collection;

/**
 * Interface (V in MVP pattern) representing the main screen of application.
 */
public interface MainView extends DataLoadView {

    void navigateToLogin();

    void renderUserRepoList(Collection<Repo> repoCollection);
}
