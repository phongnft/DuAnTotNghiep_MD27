package com.example.duantotnghiep_md27.Model;

public class ResPas {
    private String phone_number;
    private String otp;
    private String oldPassword;  // New field for old password
    private String newPassword;

    public ResPas(String phone_number, String otp, String oldPassword, String newPassword) {
        this.phone_number = phone_number;
        this.otp = otp;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ResPas() {
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}