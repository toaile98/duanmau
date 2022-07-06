package com.example.ph13373.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.PhieuMuon;
import com.example.ph13373.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }

    public long insert(PhieuMuon obj){
        ContentValues contentValues  =new ContentValues();
        contentValues.put("maTT",obj.getMaTT());
        contentValues.put("maTV",obj.getMaTV());
        contentValues.put("maSach",obj.getMaSach());
        contentValues.put("tienThue",obj.getTienThue());
        contentValues.put("traSach",obj.getTraSach());
        contentValues.put("ngayMuon",obj.getNgayMuon());


        return db.insert("phieuMuon",null,contentValues);
    }

    public int update(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();

        contentValues.put("traSach",obj.getTraSach());


        return db.update("phieuMuon",contentValues,"maPhieuMuon=?",new String[]{String.valueOf(obj.getMaPhieuMuon())});
    }

    public int delete(int id){
        return db.delete("phieuMuon","maPhieuMuon=?",new String[]{String.valueOf(id)});
    }


    //=================================
    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);

        while (cursor.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPhieuMuon((cursor.getInt(0)));
            obj.setMaTT(cursor.getInt(1));
            obj.setMaTV(cursor.getInt(2));
            obj.setMaSach(cursor.getInt(3));
            obj.setTienThue(cursor.getInt(4));
            obj.setTraSach(cursor.getInt(5));
            obj.setNgayMuon(cursor.getString(6));

            _lst.add(obj);

        }
        cursor.close();
        return _lst;
    }

    public List<PhieuMuon> getAll(){
        String sql="SELECT * FROM phieuMuon";
        return getData(sql);
    }
    public PhieuMuon getId(int id){
        String sql="SELECT * FROM phieuMuon WHERE maPhieuMuon=?";
        List<PhieuMuon> _lst=getData(sql, String.valueOf(id));
        return _lst.get(0);
    }
}
