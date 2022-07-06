package com.example.ph13373.model;

import java.io.Serializable;

public class ThuThu implements Serializable {
    private int maTT;
    private String username;
    private String password;

    public ThuThu() {
    }

    public ThuThu(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ThuThu(int maTT, String username, String password) {
        this.maTT = maTT;
        this.username = username;
        this.password = password;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
