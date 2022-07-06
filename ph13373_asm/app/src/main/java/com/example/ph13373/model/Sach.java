package com.example.ph13373.model;

import java.io.Serializable;

public class Sach  implements Serializable {
    private int maSach;
    private  String tenSach;
    private int giaThue;
    private int maLoaiSach;
    private double giaKM;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoaiSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoaiSach = maLoaiSach;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(int maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public double getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(double giaKM) {
        this.giaKM = giaKM;
    }
}
