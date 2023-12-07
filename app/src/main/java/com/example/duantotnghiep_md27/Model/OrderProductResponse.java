package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class OrderProductResponse {
    @SerializedName("message")
   private String message;

    @SerializedName("data")
    private OrderProductData orderProductData;

    public OrderProductResponse(String message, OrderProductData orderProductData) {
        this.message = message;
        this.orderProductData = orderProductData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderProductData getOrderProductData() {
        return orderProductData;
    }

    public void setOrderProductData(OrderProductData orderProductData) {
        this.orderProductData = orderProductData;
    }
}
