package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

public class ListCart {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private CartDataList cartDataList;

    public ListCart(String message, CartDataList cartDataList) {
        this.message = message;
        this.cartDataList = cartDataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CartDataList getCartDataList() {
        return cartDataList;
    }

    public void setCartDataList(CartDataList cartDataList) {
        this.cartDataList = cartDataList;
    }
}
