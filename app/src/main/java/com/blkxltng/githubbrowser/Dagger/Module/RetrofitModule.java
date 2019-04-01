package com.blkxltng.githubbrowser.Dagger.Module;

import com.blkxltng.githubbrowser.Constants;
import com.blkxltng.githubbrowser.Dagger.Scope.ApplicationScope;
import com.blkxltng.githubbrowser.Interfaces.GitHubService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    GitHubService getGitHubService(Retrofit retroFit) {
        return retroFit.create(GitHubService.class);
    }

}
