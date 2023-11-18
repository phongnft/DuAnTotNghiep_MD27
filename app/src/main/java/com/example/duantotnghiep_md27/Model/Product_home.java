package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product_home {
    private String product_id;
    private String product_name;
    private double price;
    private String image_url;
    private String description;
    private String category_id;
    private String creation_date;
    private Category category;

//    @SerializedName("product_id")
//    private String productId;
//    @SerializedName("product_name")
//    private String productName;
//    private double price;
//    @SerializedName("image_url")
//    private String imageUrl;
//    private String description;
//    @SerializedName("category_id")
//    private String categoryId;
//    @SerializedName("creation_date")
//    private String creationDate;
//    private Category category;

    public Product_home(String product_id, String product_name, double price, String image_url, String description, String category_id, String creation_date, Category category) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.image_url = image_url;
        this.description = description;
        this.category_id = category_id;
        this.creation_date = creation_date;
        this.category = category;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
