package com.blkxltng.githubbrowser;

import android.app.Activity;
import android.app.Application;

import com.blkxltng.githubbrowser.Dagger.Component.ApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Component.DaggerApplicationComponent;
import com.blkxltng.githubbrowser.Dagger.Module.ContextModule;

public class GitHubBrowserApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static GitHubBrowserApplication get(Activity activity){
        return (GitHubBrowserApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
