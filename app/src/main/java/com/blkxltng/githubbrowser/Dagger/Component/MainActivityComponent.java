package com.blkxltng.githubbrowser.Dagger.Component;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Qualifier.ActivityContext;
import com.blkxltng.githubbrowser.Dagger.Scope.ActivityScope;
import com.blkxltng.githubbrowser.MainActivity;

import dagger.Component;
import dagger.Provides;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

//    @ActivityContext
//    Context getContext();
//
//    void injectMainActivity(MainActivity mainActivity);

}