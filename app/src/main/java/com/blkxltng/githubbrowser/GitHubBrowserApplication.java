package com.blkxltng.githubbrowser;

import android.app.Application;

import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Component.DaggerApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Module.ApplicationModule;

public class GitHubBrowserApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.injectApplication(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
