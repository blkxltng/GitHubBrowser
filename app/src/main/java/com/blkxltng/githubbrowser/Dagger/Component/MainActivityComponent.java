package com.blkxltng.githubbrowser.Dagger.Component;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Module.RepoAdapterModule;
import com.blkxltng.githubbrowser.Dagger.Qualifier.ActivityContext;
import com.blkxltng.githubbrowser.Dagger.Scope.ActivityScope;
import com.blkxltng.githubbrowser.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = RepoAdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}