package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OderProduct {
    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<Oderdata> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Oderdata> getData() {
        return data;
    }

    public void setData(List<Oderdata> data) {
        this.data = data;
    }
}
