package com.blkxltng.githubbrowser.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String userName;
    @SerializedName("avatar_url")
    private String userAvatarURL;
    @SerializedName("login")
    private String userLogin;

    public User(String userName, String userAvatarURL, String userLogin) {
        this.userName = userName;
        this.userAvatarURL = userAvatarURL;
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
