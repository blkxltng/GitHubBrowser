package com.blkxltng.githubbrowser.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Organization implements Parcelable {

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

    protected Organization(Parcel in) {
        organizationName = in.readString();
        organizationDescription = in.readString();
        organizationLogin = in.readString();
        organizationAvatarURL = in.readString();
        organizationLocation = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(organizationName);
        dest.writeString(organizationDescription);
        dest.writeString(organizationLogin);
        dest.writeString(organizationAvatarURL);
        dest.writeString(organizationLocation);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Organization> CREATOR = new Parcelable.Creator<Organization>() {
        @Override
        public Organization createFromParcel(Parcel in) {
            return new Organization(in);
        }

        @Override
        public Organization[] newArray(int size) {
            return new Organization[size];
        }
    };
}
