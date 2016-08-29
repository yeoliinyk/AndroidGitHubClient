package com.oliinykov.yevgen.android.githubclient.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.oliinykov.yevgen.android.githubclient.presentation.navigation.Navigator;
import com.oliinykov.yevgen.android.githubclient.utils.PrefHelper;

/**
 * Main application activity that checks for existing user authorization. This is the app entry point.
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PrefHelper.isAuthenticated(this)) {
            Navigator.navigateToMain(this);
        } else {
            Navigator.navigateToLogin(this);
        }
        finish();
    }
}
