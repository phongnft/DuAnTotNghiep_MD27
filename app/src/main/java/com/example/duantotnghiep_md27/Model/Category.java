package com.example.duantotnghiep_md27.Model;

import java.util.ArrayList;
import java.util.List;

public class Category {
//    String id;
//    String category;
//    String cateimg;
//    String token;
//    String category_id;

    int MaLoai;
    String TenLoai,HinhanhLSP;


    public Category(int maLoai, String tenLoai, String hinhanhLSP) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        HinhanhLSP = hinhanhLSP;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getHinhanhLSP() {
        return HinhanhLSP;
    }

    public void setHinhanhLSP(String hinhanhLSP) {
        HinhanhLSP = hinhanhLSP;
    }
}

