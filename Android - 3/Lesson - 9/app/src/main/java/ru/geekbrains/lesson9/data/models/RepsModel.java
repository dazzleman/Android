package ru.geekbrains.lesson9.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class RepsModel {
    @SerializedName("name")
    @Expose
    private String name = "name";

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("owner")
    @Expose
    private GithubUser owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public GithubUser getOwner() {
        return owner;
    }

    public void setOwner(GithubUser owner) {
        this.owner = owner;
    }

    public RepsModel() {
    }

    public RepsModel(String name, String fullName, GithubUser owner) {
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepsModel repsModel = (RepsModel) o;
        return name.equals(repsModel.name) &&
                fullName.equals(repsModel.fullName) &&
                owner.equals(repsModel.owner);
    }
}
