package com.blkxltng.githubbrowser.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repo implements Parcelable {

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

    protected Repo(Parcel in) {
        repoName = in.readString();
        repoURL = in.readString();
        repoStarCount = in.readByte() == 0x00 ? null : in.readInt();
        repoLanguage = in.readString();
        repoDefaultBranch = in.readString();
        repoDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repoName);
        dest.writeString(repoURL);
        if (repoStarCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(repoStarCount);
        }
        dest.writeString(repoLanguage);
        dest.writeString(repoDefaultBranch);
        dest.writeString(repoDescription);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
