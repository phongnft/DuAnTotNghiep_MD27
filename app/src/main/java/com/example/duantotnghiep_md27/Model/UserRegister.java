package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class UserRegister {
    @SerializedName("user")
    User user;

    @SerializedName("message")
    String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


