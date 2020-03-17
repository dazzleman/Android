package ru.geekbrains.lesson9.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubUser {
    @SerializedName("login")
    @Expose
    private String login = "";

    @SerializedName("avatar_url")
    @Expose
    private String avatar;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public GithubUser() {
    }

    public GithubUser(String login, String avatar) {
        this.login = login;
        this.avatar = avatar;
    }
}
