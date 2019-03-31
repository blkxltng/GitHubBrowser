package com.blkxltng.githubbrowser;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Component.DaggerMainActivityComponent;
import com.blkxltng.githubbrowser.Dagger.Component.MainActivityComponent;
import com.blkxltng.githubbrowser.Dagger.Module.MainActivityContextModule;
import com.blkxltng.githubbrowser.Fragments.RepoListFragment;
import com.blkxltng.githubbrowser.Fragments.SearchFragment;
import com.blkxltng.githubbrowser.Fragments.WebViewFragment;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.Network.RetrofitClientInstance;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends SingleFragmentActivity implements SearchFragment.SearchFragmentCallback, RepoAdapter.RepoAdapterCallback {

    MainActivityComponent mainActivityComponent;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponent applicationComponent = getApplicationComponent();
//        mainActivityComponent = DaggerMainActivityComponent.builder()
//                .mainActivityContextModule(new MainActivityContextModule(this))
//                .applicationComponent(applicationComponent)
//                .build();
//
//        mainActivityComponent.injectMainActivity(this);

    }

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

//    @Inject
//    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
//
//    //simplified
//
//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return fragmentDispatchingAndroidInjector;
//    }

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
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
                break;
        }
        return true;
    }
}
