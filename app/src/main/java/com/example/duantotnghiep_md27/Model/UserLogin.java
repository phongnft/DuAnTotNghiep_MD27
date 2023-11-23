package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;


public class UserLogin {

    @SerializedName("data")
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
