package com.example.duantotnghiep_md27.Model;

public class Profile {
    private int maND;
    private String tenND;
    private int SDTND;

    public Profile(int maND, String tenND, int SDTND) {
        this.maND = maND;
        this.tenND = tenND;
        this.SDTND = SDTND;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
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
}
