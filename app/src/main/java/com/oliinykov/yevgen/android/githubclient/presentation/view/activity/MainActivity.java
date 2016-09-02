package com.oliinykov.yevgen.android.githubclient.presentation.view.activity;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.oliinykov.yevgen.android.githubclient.R;
import com.oliinykov.yevgen.android.githubclient.domain.entity.Repo;
import com.oliinykov.yevgen.android.githubclient.presentation.navigation.Navigator;
import com.oliinykov.yevgen.android.githubclient.presentation.presenter.MainPresenter;
import com.oliinykov.yevgen.android.githubclient.presentation.view.MainView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.RepoListView;
import com.oliinykov.yevgen.android.githubclient.presentation.view.fragment.RepoListFragment;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A main screen that represents user repositories.
 */
public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.rl_progress) RelativeLayout mProgress;
    private MainPresenter mPresenter;
    private RepoListView mUserReposView;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sign_out: {
                showSignOutDialog();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, getApplicationContext());

        if (savedInstanceState == null) {
            mUserReposView = new RepoListFragment();
            addFragment(R.id.fl_main_fragment_container, (Fragment) mUserReposView, RepoListFragment.TAG);
        } else {
            mUserReposView = (RepoListView) getFragmentById(R.id.fl_main_fragment_container);
        }
        mPresenter.getUserRepoList();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mUserReposView = null;
    }

    @Override
    public void navigateToLogin() {
        Navigator.navigateToLogin(this);
    }

    @Override
    public void renderUserRepoList(Collection<Repo> userRepoCollection) {
        mUserReposView.renderRepoList(userRepoCollection);
    }

    @Override
    public void showProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.message_sign_out_dialog));
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.signOut(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
