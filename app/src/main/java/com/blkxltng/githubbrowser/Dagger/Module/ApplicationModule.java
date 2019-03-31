package com.blkxltng.githubbrowser.Dagger.Module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

}
