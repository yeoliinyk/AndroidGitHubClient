package com.oliinykov.yevgen.android.githubclient.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represents a Repository in the domain layer.
 */
public class Repo {

    @SerializedName("name")
    private String title;
    private String description;

    public Repo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Repo Details *****\n");
        stringBuilder.append("title = " + title + "\n");
        stringBuilder.append("description = " + description + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
