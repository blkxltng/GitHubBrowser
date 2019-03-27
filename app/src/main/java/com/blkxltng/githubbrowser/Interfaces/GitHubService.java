package com.blkxltng.githubbrowser.Interfaces;

import com.blkxltng.githubbrowser.Models.Organization;
import com.blkxltng.githubbrowser.Models.Repo;
import com.blkxltng.githubbrowser.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listUserRepos(@Path("user") String user);

    @GET("orgs/{organization}/repos")
    Call<List<Repo>> listOrganizationRepos(@Path("organization") String organization);

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("orgs/{organization}")
    Call<Organization> getOrganization(@Path("organization") String organization);
}
