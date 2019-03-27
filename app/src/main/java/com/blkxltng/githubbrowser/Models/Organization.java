package com.blkxltng.githubbrowser.Models;

import com.google.gson.annotations.SerializedName;

public class Organization {

    @SerializedName("name")
    private String organizationName;
    @SerializedName("description")
    private String organizationDescription;
    @SerializedName("login")
    private String organizationLogin;
    @SerializedName("avatar_url")
    private String organizationAvatarURL;
    @SerializedName("location")
    private String organizationLocation;

    public Organization(String organizationName, String organizationDescription, String organizationLogin, String organizationAvatarURL, String organizationLocation) {
        this.organizationName = organizationName;
        this.organizationDescription = organizationDescription;
        this.organizationLogin = organizationLogin;
        this.organizationAvatarURL = organizationAvatarURL;
        this.organizationLocation = organizationLocation;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationLogin() {
        return organizationLogin;
    }

    public void setOrganizationLogin(String organizationLogin) {
        this.organizationLogin = organizationLogin;
    }

    public String getOrganizationAvatarURL() {
        return organizationAvatarURL;
    }

    public void setOrganizationAvatarURL(String organizationAvatarURL) {
        this.organizationAvatarURL = organizationAvatarURL;
    }

    public String getOrganizationLocation() {
        return organizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        this.organizationLocation = organizationLocation;
    }
}
