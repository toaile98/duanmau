package com.example.ph13373.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQliteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="duan1.db";
    private static final int VERSION=1;

    private static final String usernameAdmin="\"admin\"";
    private static final String passwordAdmin="\"admin\"";

    public SQliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang thu thu
        final String createThuThu="CREATE TABLE thuThu (\n" +
                "    maTT     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    username TEXT    NOT NULL,\n" +
                "    password TEXT    NOT NULL\n" +
                ");\n";
        db.execSQL(createThuThu);

        final String createAdmin="INSERT INTO thuthu(username,password) VALUES("+usernameAdmin+","+passwordAdmin+");";
        db.execSQL(createAdmin);
        //tao bang thanh vien
        final String createThanhVien = "CREATE TABLE thanhVien (\n" +
                "    maTV  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    hoTen TEXT    NOT NULL,\n" +
                "    sdt   TEXT    NOT NULL\n" +
                ");\n";
        db.execSQL(createThanhVien);

        //tao bang loai sach
        final String createLoaiSach = "CREATE TABLE loaiSach (\n" +
                "    maLoaiSach  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenLoaiSach TEXT    NOT NULL\n" +
                "    ,nhaCC TEXT    NOT NULL\n" +
                ");\n";
        db.execSQL(createLoaiSach);

        //tao bang sach
        final String createSach  ="CREATE TABLE sach (\n" +
                "    maSach     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenSach    TEXT    NOT NULL,\n" +
                "    giaThue    INTEGER NOT NULL,\n" +
                "    maLoaiSach INTEGER NOT NULL\n" +
                "                       REFERENCES loaiSach (maLoaiSach) \n" +
                "    ,giaKM INTEGER NOT NULL\n" +
                ");\n";
        db.execSQL(createSach);

        //tao bang phieu muon
        final String createPhieuMuon = "CREATE TABLE phieuMuon (\n" +
                "    maPhieuMuon INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    maTT        INTEGER NOT NULL\n" +
                "                        REFERENCES thuThu (maTT),\n" +
                "    maTV        INTEGER NOT NULL\n" +
                "                        REFERENCES thanhVien (maTV),\n" +
                "    maSach      INTEGER NOT NULL\n" +
                "                        REFERENCES sach (maSach),\n" +
                "    tienThue    INTEGER NOT NULL,\n" +
                "    traSach     INTEGER NOT NULL,\n" +
                "    ngayMuon    DATE    NOT NULL\n" +
                ");\n";
        db.execSQL(createPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS thuThu");
        db.execSQL("DROP TABLE IF EXISTS thanhVien");
        db.execSQL("DROP TABLE IF EXISTS loaiSach");
        db.execSQL("DROP TABLE IF EXISTS sach");
        db.execSQL("DROP TABLE IF EXISTS phieuMuon");

        onCreate(db);

    }
}
