package com.oliinykov.yevgen.android.githubclient.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.oliinykov.yevgen.android.githubclient.presentation.view.activity.LoginActivity;
import com.oliinykov.yevgen.android.githubclient.presentation.view.activity.MainActivity;


/**
 * Class used to navigate through the application.
 */
public class Navigator {

    public static void navigateToMain(Context context) {
        context.startActivity(new Intent(MainActivity.getCallingIntent(context)));
    }

    public static void navigateToLogin(Context context) {
        context.startActivity(new Intent(LoginActivity.getCallingIntent(context)));
    }

}
