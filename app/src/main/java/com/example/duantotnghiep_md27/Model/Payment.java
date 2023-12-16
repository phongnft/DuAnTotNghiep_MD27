package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("userid")
    private String user_id;
    @SerializedName("total")
    private int total;


    public Payment(String user_id, int total) {
        this.user_id = user_id;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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
