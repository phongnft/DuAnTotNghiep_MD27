package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class OderCall {
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Oderdata data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Oderdata getData() {
        return data;
    }

    public void setData(Oderdata data) {
        this.data = data;
    }
}
