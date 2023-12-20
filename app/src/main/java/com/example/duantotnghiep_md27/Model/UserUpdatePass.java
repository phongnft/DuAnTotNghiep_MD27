package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class UserUpdatePass {
    @SerializedName("data")
    Dataaa data;

    public Dataaa getData() {
        return data;
    }

    public void setData(Dataaa data) {
        this.data = data;
    }
}
