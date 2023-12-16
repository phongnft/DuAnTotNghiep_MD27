package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class LoginGoogleData {
    @SerializedName("message")
    String message;
    @SerializedName("data")
    User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
