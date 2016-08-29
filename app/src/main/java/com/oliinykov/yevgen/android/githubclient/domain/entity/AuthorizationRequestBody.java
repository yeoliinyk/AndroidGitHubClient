package com.oliinykov.yevgen.android.githubclient.domain.entity;

import java.util.Arrays;

/**
 * Class that represents a AuthorizationRequestBody in the domain layer.
 */
public class AuthorizationRequestBody {

    private String note;
    private String[] scopes;

    public AuthorizationRequestBody(String note, String[] scopes) {
        this.note = note;
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** AuthorizationRequestBody Details *****\n");
        stringBuilder.append("note = " + note + "\n");
        stringBuilder.append("scopes = " + Arrays.asList(scopes).toString() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

    public String getNote() {
        return note;
    }

    public String[] getScopes() {
        return scopes;
    }
}
