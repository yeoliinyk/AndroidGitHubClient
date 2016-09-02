package com.oliinykov.yevgen.android.githubclient.presentation.view.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Base {@link android.support.v7.app.AppCompatActivity} class for every Activity in this application.
 */
public class BaseActivity extends AppCompatActivity {

    protected void addFragment(int containerId, Fragment fragment, String tag) {
        getFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .commit();
    }

    protected Fragment getFragmentById(int id) {
        return getFragmentManager().findFragmentById(id);
    }
}
