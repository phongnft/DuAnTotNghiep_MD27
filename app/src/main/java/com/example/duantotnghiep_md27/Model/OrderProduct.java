package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class OrderProduct {
    @SerializedName("userId")
    private String userId;
    @SerializedName("productId")
    private String productId;
    @SerializedName("quantity")
    private int quantity;

    @SerializedName("size")
    private String size;

    public OrderProduct(String userId, String productId, int quantity, String size) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.size = size;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
