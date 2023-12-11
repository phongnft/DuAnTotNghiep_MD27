package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetails {
    String order_detail_id;
    String order_id;
    String product_id;
    int quantity;
    @SerializedName("product")
    List<OderProduct> oderProducts_list;

    public String getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(String order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OderProduct> getOderProducts_list() {
        return oderProducts_list;
    }

    public void setOderProducts_list(List<OderProduct> oderProducts_list) {
        this.oderProducts_list = oderProducts_list;
    }
}