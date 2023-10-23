package com.example.duantotnghiep_md27.Model;

import java.util.ArrayList;
import java.util.List;

public class Product_home {
    String id;
    String name;
    String category;
    String price;
    String description;
    String currency;
    List<ProductImage> productImages=new ArrayList<>();

    public Product_home() {
    }

    public Product_home(String id, String name, String category, String price, String description, String currency, List<ProductImage> productImages) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.currency = currency;
        this.productImages = productImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
