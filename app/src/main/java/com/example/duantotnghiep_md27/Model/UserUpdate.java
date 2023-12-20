package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class UserUpdate {
    String message;
    @SerializedName("data")
    User data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
