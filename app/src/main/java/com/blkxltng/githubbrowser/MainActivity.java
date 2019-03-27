package com.blkxltng.githubbrowser;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Fragments.RepoListFragment;
import com.blkxltng.githubbrowser.Fragments.SearchFragment;
import com.blkxltng.githubbrowser.Fragments.WebViewFragment;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.Network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends SingleFragmentActivity implements SearchFragment.SearchFragmentCallback, RepoAdapter.RepoAdapterCallback {

    private static final String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }

    @Override
    public void loadRepoList(String organization) {
        Fragment listFragment = RepoListFragment.newInstance(organization);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFragment).addToBackStack(null).commit();
    }

    @Override
    public void onClickRepo(String repoURL) {
        //do something
        Fragment webviewFragment = WebViewFragment.newInstance(repoURL);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, webviewFragment).addToBackStack(null).commit();
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        GitHubService service = RetrofitClientInstance.getRetrofitInstance().create(GitHubService.class);
//        Call<List<Repo>> call = service.listOrganizationRepos("google");
//        call.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.d(TAG, "onResponse: First repo name is: " + response.body().get(0).getRepoName());
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
