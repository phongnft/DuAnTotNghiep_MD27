package com.example.duantotnghiep_md27.Model;

public class MyInfo {
    private int manD;
    private String tenND;
    private int SDTND;
    private String DiachiND;
    private int TuoiND;
    private String Email;

    public MyInfo(int manD, String tenND, int SDTND, String diachiND, int tuoiND, String email) {
        this.manD = manD;
        this.tenND = tenND;
        this.SDTND = SDTND;
        DiachiND = diachiND;
        TuoiND = tuoiND;
        Email = email;
    }

    public int getManD() {
        return manD;
    }

    public void setManD(int manD) {
        this.manD = manD;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public int getSDTND() {
        return SDTND;
    }

    public void setSDTND(int SDTND) {
        this.SDTND = SDTND;
    }

    public String getDiachiND() {
        return DiachiND;
    }

    public void setDiachiND(String diachiND) {
        DiachiND = diachiND;
    }

    public int getTuoiND() {
        return TuoiND;
    }

    public void setTuoiND(int tuoiND) {
        TuoiND = tuoiND;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
