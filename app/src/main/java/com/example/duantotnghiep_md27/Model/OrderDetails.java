package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetails {
    private boolean isButtonDisabled;
    String order_detail_id;
    String order_id;
    String product_id;
    int quantity;
    @SerializedName("product")
    Product_home product;

    public OrderDetails(boolean isButtonDisabled) {
        this.isButtonDisabled = false;
    }

    public boolean isButtonDisabled() {
        return isButtonDisabled;
    }

    public void setButtonDisabled(boolean buttonDisabled) {
        isButtonDisabled = buttonDisabled;
    }


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

    public Product_home getProduct() {
        return product;
    }

    public void setProduct(Product_home product) {
        this.product = product;
    }
}
