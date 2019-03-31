package com.blkxltng.githubbrowser.Dagger.Module;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Qualifier.ActivityContext;
import com.blkxltng.githubbrowser.Dagger.Scope.ActivityScope;
import com.blkxltng.githubbrowser.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
