package com.example.duantotnghiep_md27.Model;

public class User {

    private String full_name;
    private String password;
    private String phone_number;
    private String email;
    private String image_url;

//    String token;
//    String firebase_token;


    public User(String full_name, String password, String phone_number, String email, String image_url) {
        this.full_name = full_name;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.image_url = image_url;
    }

    public User(String full_name, String password,  String email) {
        this.full_name = full_name;
        this.password = password;
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
