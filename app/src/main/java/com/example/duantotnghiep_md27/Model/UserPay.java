package com.example.duantotnghiep_md27.Model;

import android.media.Image;

public class UserPay {
    String Name, Conscious, Ward, Commune, Adress;
    Number Phone;
    String Size, color;
    Image oder;

    public UserPay() {

    }

    public UserPay(String name, String conscious, String ward, String commune, String adress, Number phone, String size, String color, Image oder, Number quantity, Number price, Number priceDiscount, Number order, Number sumPirce) {
        Name = name;
        Conscious = conscious;
        Ward = ward;
        Commune = commune;
        Adress = adress;
        Phone = phone;
        Size = size;
        this.color = color;
        this.oder = oder;
        Quantity = quantity;
        Price = price;
        PriceDiscount = priceDiscount;
        this.order = order;
        SumPirce = sumPirce;
    }

    Number Quantity, Price,PriceDiscount, order, SumPirce;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getConscious() {
        return Conscious;
    }

    public void setConscious(String conscious) {
        Conscious = conscious;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }

    public String getCommune() {
        return Commune;
    }

    public void setCommune(String commune) {
        Commune = commune;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public Number getPhone() {
        return Phone;
    }

    public void setPhone(Number phone) {
        Phone = phone;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Image getOder() {
        return oder;
    }

    public void setOder(Image oder) {
        this.oder = oder;
    }

    public Number getQuantity() {
        return Quantity;
    }

    public void setQuantity(Number quantity) {
        Quantity = quantity;
    }

    public Number getPrice() {
        return Price;
    }

    public void setPrice(Number price) {
        Price = price;
    }

    public Number getPriceDiscount() {
        return PriceDiscount;
    }

    public void setPriceDiscount(Number priceDiscount) {
        PriceDiscount = priceDiscount;
    }

    public Number getOrder() {
        return order;
    }

    public void setOrder(Number order) {
        this.order = order;
    }

    public Number getSumPirce() {
        return SumPirce;
    }

    public void setSumPirce(Number sumPirce) {
        SumPirce = sumPirce;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Conscious='" + Conscious + '\'' +
                ", Ward='" + Ward + '\'' +
                ", Commune='" + Commune + '\'' +
                ", Adress='" + Adress + '\'' +
                ", Phone=" + Phone +
                ", Size='" + Size + '\'' +
                ", color='" + color + '\'' +
                ", oder=" + oder +
                ", Quantity=" + Quantity +
                ", Price=" + Price +
                ", PriceDiscount=" + PriceDiscount +
                ", order=" + order +
                ", SumPirce=" + SumPirce +
                '}';
    }
}

