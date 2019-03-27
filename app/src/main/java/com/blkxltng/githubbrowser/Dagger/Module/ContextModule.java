package com.blkxltng.githubbrowser.Dagger.Module;

import android.content.Context;

import com.blkxltng.githubbrowser.Dagger.Qualifier.ApplicationContext;
import com.blkxltng.githubbrowser.Dagger.Scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
