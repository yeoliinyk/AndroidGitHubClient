package com.oliinykov.yevgen.android.githubclient.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class that represents a SearchResponseBody in the domain layer.
 */
public class SearchResponseBody {

    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("items")
    private List<Repo> repos;

    public SearchResponseBody(int totalCount, List<Repo> repos) {
        this.totalCount = totalCount;
        this.repos = repos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** SearchResponseBody Details *****\n");
        stringBuilder.append("totalCount = " + totalCount + "\n");
        stringBuilder.append("repos = " + repos.toString() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Repo> getRepos() {
        return repos;
    }
}
