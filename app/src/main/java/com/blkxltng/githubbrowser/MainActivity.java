package com.blkxltng.githubbrowser;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.Fragments.RepoListFragment;
import com.blkxltng.githubbrowser.Fragments.SearchFragment;
import com.blkxltng.githubbrowser.Fragments.WebViewFragment;
import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;

import java.util.List;

public class MainActivity extends SingleFragmentActivity implements SearchFragment.SearchFragmentCallback, RepoAdapter.RepoAdapterCallback {

    private static final String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }

    @Override
    public void loadRepoList(Organization orgData, List<Repo> repoData) {
        Fragment listFragment = RepoListFragment.newInstance(orgData, repoData);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).addToBackStack(null).commit();
    }

    @Override
    public void onClickRepo(String repoURL) {
        //do something
        Fragment webviewFragment = WebViewFragment.newInstance(repoURL);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, webviewFragment).addToBackStack(null).commit();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((GitHubBrowserApplication) getApplication()).getApplicationComponent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
                if(getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                    removeBackButton();
                }
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() < 1) {
            removeBackButton();
        }
    }

    private void removeBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
