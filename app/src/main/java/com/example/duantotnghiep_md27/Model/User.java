package com.example.duantotnghiep_md27.Model;

public class User {
   // String id, name, email, password, token, firebase_token;

   // String id;
    String tenND;
    String MatKhauND;
    String SDTND;
    String DiachiND;
    String TuoiND;
    String Email;
    String HinhAnhND;
    String token;
    String firebase_token;


    public User(String id, String tenND, String matKhauND, String SDTND, String diachiND, String tuoiND, String email, String hinhAnhND, String token, String firebase_token) {
        this.tenND = tenND;
        MatKhauND = matKhauND;
        this.SDTND = SDTND;
        DiachiND = diachiND;
        TuoiND = tuoiND;
        Email = email;
        HinhAnhND = hinhAnhND;
        this.token = token;
        this.firebase_token = firebase_token;
    }

    public User(String tenND, String email, String matKhauND, String firebase_token) {
        this.tenND = tenND;
        Email = email;
        MatKhauND = matKhauND;
        this.firebase_token = firebase_token;
    }



    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getMatKhauND() {
        return MatKhauND;
    }

    public void setMatKhauND(String matKhauND) {
        MatKhauND = matKhauND;
    }

    public String getSDTND() {
        return SDTND;
    }

    public void setSDTND(String SDTND) {
        this.SDTND = SDTND;
    }

    public String getDiachiND() {
        return DiachiND;
    }

    public void setDiachiND(String diachiND) {
        DiachiND = diachiND;
    }

    public String getTuoiND() {
        return TuoiND;
    }

    public void setTuoiND(String tuoiND) {
        TuoiND = tuoiND;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHinhAnhND() {
        return HinhAnhND;
    }

    public void setHinhAnhND(String hinhAnhND) {
        HinhAnhND = hinhAnhND;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }
}
