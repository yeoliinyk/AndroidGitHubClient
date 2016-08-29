package com.oliinykov.yevgen.android.githubclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper class for managing shared preferences.
 */
public class PrefHelper {

    public static final String PREFS_AUTH_ID_KEY = "auth_id";
    public static final String PREFS_AUTH_TOKEN_KEY = "auth_token";
    public static final String PREFS_USERNAME_KEY = "username";
    private static final String SETTINGS_FILE_NAME = "GitHubClient.Settings";


    public static void writeIntToPreferences(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static void writeStringToPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static boolean removeStringFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().remove(key).commit();
    }

    public static boolean isAuthenticated(Context context) {
        return getStringFromPreferences(context, PREFS_AUTH_TOKEN_KEY) != null ? true : false;
    }
}
