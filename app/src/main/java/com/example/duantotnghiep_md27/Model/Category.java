package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("description")
    private String description;

    List<Product_home> Products = new ArrayList<>();


    public Category(String category_id, String category_name, String image_url, String description) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.image_url = image_url;
        this.description = description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product_home> getProducts() {
        return Products;
    }

    public void setProducts(List<Product_home> products) {
        Products = products;
    }
}

