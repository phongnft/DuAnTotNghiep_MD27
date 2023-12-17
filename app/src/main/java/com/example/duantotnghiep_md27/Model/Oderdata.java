package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Oderdata {
    String order_id;
    String user_id;
    int total_amount;
    String status;
    String order_date;
    @SerializedName("OrderDetails")
    List<OrderDetails> OrderDetails;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public List<com.example.duantotnghiep_md27.Model.OrderDetails> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<com.example.duantotnghiep_md27.Model.OrderDetails> orderDetails) {
        OrderDetails = orderDetails;
    }
}
