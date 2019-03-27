package com.blkxltng.githubbrowser.Dagger.Component;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Module.ContextModule;
import com.blkxltng.githubbrowser.Dagger.Module.RetrofitModule;
import com.blkxltng.githubbrowser.Dagger.Qualifier.ApplicationContext;
import com.blkxltng.githubbrowser.Dagger.Scope.ApplicationScope;
import com.blkxltng.githubbrowser.GitHubBrowserApplication;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public GitHubService getGitHubService();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(GitHubBrowserApplication gitHubBrowserApplication);
}
