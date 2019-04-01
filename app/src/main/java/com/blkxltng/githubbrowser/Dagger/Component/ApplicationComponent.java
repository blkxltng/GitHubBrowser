package com.blkxltng.githubbrowser.Dagger.Component;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Module.ApplicationModule;
import com.blkxltng.githubbrowser.Dagger.Module.RetrofitModule;
import com.blkxltng.githubbrowser.Dagger.Scope.ApplicationScope;
import com.blkxltng.githubbrowser.Fragments.RepoListFragment;
import com.blkxltng.githubbrowser.Fragments.SearchFragment;
import com.blkxltng.githubbrowser.GitHubBrowserApplication;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public GitHubService getGitHubService();

    public Context getContext();

    void injectApplication(GitHubBrowserApplication gitHubBrowserApplication);

    void injectSearchFragment(SearchFragment fragment);
    void injectRepoListFragment(RepoListFragment fragment);

}
