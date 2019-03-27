package com.blkxltng.githubbrowser.Dagger.Module;

import com.blkxltng.githubbrowser.Adapters.RepoAdapter;
import com.blkxltng.githubbrowser.Dagger.Scope.ActivityScope;
import com.blkxltng.githubbrowser.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class RepoAdapterModule {

//    @Provides
//    @ActivityScope
//    public RepoAdapter getRepoList(RepoAdapter.RepoAdapterCallback mCallback) {
//        return new RepoAdapter(mCallback);
//    }

    @Provides
    @ActivityScope
    public RepoAdapter.RepoAdapterCallback getCallback(MainActivity mainActivity) {
        return mainActivity;
    }

}
