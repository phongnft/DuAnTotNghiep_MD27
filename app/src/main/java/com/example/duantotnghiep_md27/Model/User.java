package com.example.duantotnghiep_md27.Model;

public class User {
    String user_id;
    String full_name;
    String image_url;
    String oldPassword;
    String password;
    String newPassword;
    String phone_number;
    String email;
    String status;
    String otp;
    String address;

    public User() {
    }


    public User(String user_id, String oldPassword, String newPassword) {
        this.user_id = user_id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public User(String user_id, String full_name, String phone_number, String email, String address) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

    public User(String full_name, String phone_number, String email, String password) {
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
    }



    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
