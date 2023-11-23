package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
