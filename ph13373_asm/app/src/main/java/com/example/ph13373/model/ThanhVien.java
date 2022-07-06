package com.example.ph13373.model;

import java.io.Serializable;

public class ThanhVien  implements Serializable {
    private int maTV;
    private String hoTen;
    private String sdt;

    public ThanhVien() {
    }

    public ThanhVien(String hoTen, String sdt) {
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public ThanhVien(int maTV, String hoTen, String sdt) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
