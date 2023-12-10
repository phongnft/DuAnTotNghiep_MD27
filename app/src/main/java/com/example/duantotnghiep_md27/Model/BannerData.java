package com.example.duantotnghiep_md27.Model;

import com.bdtopcoder.smart_slider.SliderItem;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerData {
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<SliderItem> data = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SliderItem> getData() {
        return data;
    }

    public void setData(List<SliderItem> data) {
        this.data = data;
    }
}
