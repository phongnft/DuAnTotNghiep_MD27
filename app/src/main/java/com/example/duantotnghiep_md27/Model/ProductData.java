package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {
    @SerializedName("product")
    List<Product_home> productList;
    private String message;

    @SerializedName("status")
    int status;
    private List<Product_home> data;

    public ProductData(List<Product_home> productList, String message, int status, List<Product_home> data) {
        this.productList = productList;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public List<Product_home> getProductList() {
        return productList;
    }

    public void setProductList(List<Product_home> productList) {
        this.productList = productList;
    }

    public double getStatus() {
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

    public List<Product_home> getData() {
        return data;
    }

    public void setData(List<Product_home> data) {
        this.data = data;
    }
}
