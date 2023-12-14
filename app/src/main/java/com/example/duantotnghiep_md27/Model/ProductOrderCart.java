package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class ProductOrderCart {
    @SerializedName("cart_id")
    private String cart_id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("status")
    private String status;
    @SerializedName("size")
    private String size;
    @SerializedName("product")
    private ProductForCart productForCart;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }



    public ProductOrderCart(String cart_id, String user_id, String product_id, int quantity, String status, String size, ProductForCart productForCart) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.status = status;
        this.size = size;
        this.productForCart = productForCart;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductForCart getProductForCart() {
        return productForCart;
    }

    public void setProductForCart(ProductForCart productForCart) {
        this.productForCart = productForCart;
    }
}
