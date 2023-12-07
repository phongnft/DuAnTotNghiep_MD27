package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CartDataList {
    @SerializedName("total")
        private String total;
    @SerializedName("cart")
   private ArrayList<ProductOrderCart> productOrderCartArrayList;

    public CartDataList(String total, ArrayList<ProductOrderCart> productOrderCartArrayList) {

        this.total = total;
        this.productOrderCartArrayList = productOrderCartArrayList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<ProductOrderCart> getProductOrderCartArrayList() {
        return productOrderCartArrayList;
    }

    public void setProductOrderCartArrayList(ArrayList<ProductOrderCart> productOrderCartArrayList) {
        this.productOrderCartArrayList = productOrderCartArrayList;
    }
}
