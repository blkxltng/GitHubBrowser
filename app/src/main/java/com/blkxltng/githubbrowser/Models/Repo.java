package com.blkxltng.githubbrowser.Models;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("name")
    private String repoName;
    @SerializedName("html_url")
    private String repoURL;
    @SerializedName("stargazers_count")
    private Integer repoStarCount;
    @SerializedName("language")
    private String repoLanguage;
    @SerializedName("default_branch")
    private String repoDefaultBranch;
    @SerializedName("description")
    private String repoDescription;

    public Repo(String repoName, String repoURL, Integer repoStarCount, String repoLanguage, String repoDefaultBranch, String repoDescription) {
        this.repoName = repoName;
        this.repoURL = repoURL;
        this.repoStarCount = repoStarCount;
        this.repoLanguage = repoLanguage;
        this.repoDefaultBranch = repoDefaultBranch;
        this.repoDescription = repoDescription;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoURL() {
        return repoURL;
    }

    public void setRepoURL(String repoURL) {
        this.repoURL = repoURL;
    }

    public Integer getRepoStarCount() {
        return repoStarCount;
    }

    public void setRepoStarCount(Integer repoStarCount) {
        this.repoStarCount = repoStarCount;
    }

    public String getRepoLanguage() {
        return repoLanguage;
    }

    public void setRepoLanguage(String repoLanguage) {
        this.repoLanguage = repoLanguage;
    }

    public String getRepoDefaultBranch() {
        return repoDefaultBranch;
    }

    public void setRepoDefaultBranch(String repoDefaultBranch) {
        this.repoDefaultBranch = repoDefaultBranch;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }
}
