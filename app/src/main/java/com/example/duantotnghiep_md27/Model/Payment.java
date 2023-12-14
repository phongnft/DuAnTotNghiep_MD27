package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("userid")
    private String user_id;

    public Payment(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
