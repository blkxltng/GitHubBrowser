package com.blkxltng.githubbrowser.Network;

import com.blkxltng.githubbrowser.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GITHUB_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


//    GitHubService service = retrofit.create(GitHubService.class);
//
//    Call<List<Repo>> repos = service.listUserRepos("blkxltng");
}
