package com.oliinykov.yevgen.android.githubclient.domain.entity;

/**
 * Class that represents a AuthorizationResponseBody in the domain layer.
 */
public class AuthorizationResponseBody {

    private int id;
    private String token;

    public AuthorizationResponseBody(int id, String token) {
        this.id = id;
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** AuthorizationResponseBody Details *****\n");
        stringBuilder.append("id = " + id + "\n");
        stringBuilder.append("token = " + token + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
