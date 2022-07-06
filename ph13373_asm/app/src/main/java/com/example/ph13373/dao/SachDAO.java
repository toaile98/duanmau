package com.example.ph13373.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.LoaiSach;
import com.example.ph13373.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }
    public long insert(Sach obj){
        ContentValues contentValues  =new ContentValues();
        contentValues.put("tenSach",obj.getTenSach());
        contentValues.put("giaThue",obj.getGiaThue());
        contentValues.put("maLoaiSach",obj.getMaLoaiSach());
        contentValues.put("giaKM",0);

        return db.insert("sach",null,contentValues);
    }

    public int update(Sach obj){
        ContentValues contentValues = new ContentValues();

        contentValues.put("tenSach",obj.getTenSach());
        contentValues.put("giaThue",obj.getGiaThue());
        contentValues.put("maLoaiSach",obj.getMaLoaiSach());


        return db.update("loaiSach",contentValues,"maSach=?",new String[]{String.valueOf(obj.getMaSach())});
    }

    public int delete(int id){
        return db.delete("sach","maSach=?",new String[]{String.valueOf(id)});
    }


    //=================================
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);

        while (cursor.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach((cursor.getInt(0)));
            obj.setTenSach(cursor.getString(1));
            obj.setGiaThue(cursor.getInt(2));
            obj.setMaLoaiSach(cursor.getInt(3));

            obj.setGiaKM( (cursor.getInt(4)));
            _lst.add(obj);

        }
        cursor.close();
        return _lst;
    }

    public List<Sach> getAll(){
        String sql="SELECT * FROM sach";
        return getData(sql);
    }
    public Sach getId(int id){
        String sql="SELECT * FROM sach WHERE maSach=?";
        List<Sach> _lst=getData(sql, String.valueOf(id));
        return _lst.get(0);
    }
    public int getTienThueID(int id){
        String sql="SELECT * FROM sach WHERE maSach=?";
        List<Sach> _lst=getData(sql, String.valueOf(id));
        return _lst.get(0).getGiaThue();
    }
}
