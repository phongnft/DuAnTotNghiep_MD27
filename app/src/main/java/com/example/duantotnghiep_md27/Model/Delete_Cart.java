package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class Delete_Cart {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private int data;

    public Delete_Cart(String message, int data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
