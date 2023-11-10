package com.example.duantotnghiep_md27.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product_home{
//    String id;
//    String name;
//    String category;
//    String price;
//    String description;
//    String currency;


    int maSP;
    String TenSP;
    int GiaSP;
    int SizeSP;
    String ColorSP;
    String HinhanhSP;
    String Mota;
    int MaLoai;
    int SoLuong;
    int MaNH;

    public Product_home(int maSP, String tenSP, int giaSP, int sizeSP, String colorSP, String hinhanhSP, String mota, int maLoai, int soLuong, int maNH) {
        this.maSP = maSP;
        TenSP = tenSP;
        GiaSP = giaSP;
        SizeSP = sizeSP;
        ColorSP = colorSP;
        HinhanhSP = hinhanhSP;
        Mota = mota;
        MaLoai = maLoai;
        SoLuong = soLuong;
        MaNH = maNH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getSizeSP() {
        return SizeSP;
    }

    public void setSizeSP(int sizeSP) {
        SizeSP = sizeSP;
    }

    public String getColorSP() {
        return ColorSP;
    }

    public void setColorSP(String colorSP) {
        ColorSP = colorSP;
    }

    public String getHinhanhSP() {
        return HinhanhSP;
    }

    public void setHinhanhSP(String hinhanhSP) {
        HinhanhSP = hinhanhSP;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }
}
