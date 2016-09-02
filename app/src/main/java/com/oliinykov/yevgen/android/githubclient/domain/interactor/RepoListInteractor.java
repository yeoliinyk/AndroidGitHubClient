package com.oliinykov.yevgen.android.githubclient.domain.interactor;

import com.oliinykov.yevgen.android.githubclient.data.net.ServiceGenerator;
import com.oliinykov.yevgen.android.githubclient.data.net.rest.RepoService;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;

import java.util.List;

import retrofit.Callback;

/**
 * This class is a use case for retrieving data related to authorized user {@link Repo} list.
 */
public class RepoListInteractor {

    public void loadUserRepos(String token, Callback<List<Repo>> callback) {
        RepoService repoService = ServiceGenerator.createService(RepoService.class, token);
        repoService.requestUserRepos(callback);
    }

}
