package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResult {
    @SerializedName("product")
    List<Product_home> product_homeList;

    @SerializedName("status")
    int status;

    @SerializedName("message")
    String message;

    public List<Product_home> getProduct_homeList() {
        return product_homeList;
    }

    public void setProduct_homeList(List<Product_home> product_homeList) {
        this.product_homeList = product_homeList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
