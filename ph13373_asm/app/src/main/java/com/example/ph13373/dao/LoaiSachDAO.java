package com.example.ph13373.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.LoaiSach;
import com.example.ph13373.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }
    public long insert(LoaiSach obj){
        ContentValues contentValues  =new ContentValues();
        contentValues.put("tenLoaiSach",obj.getTenLoaiSach());
        contentValues.put("nhaCC",obj.getNhaCC());

        return db.insert("loaiSach",null,contentValues);
    }

    public int update(LoaiSach obj){
        ContentValues contentValues = new ContentValues();

        contentValues.put("tenLoaiSach",obj.getTenLoaiSach());

        return db.update("loaiSach",contentValues,"maLoaiSach=?",new String[]{String.valueOf(obj.getMaLoaiSach())});
    }

    public int delete(int id){
        return db.delete("loaiSach","maLoaiSach=?",new String[]{String.valueOf(id)});
    }


    //=================================
    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);

        while (cursor.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoaiSach((cursor.getInt(0)));
            obj.setTenLoaiSach(cursor.getString(1));
            obj.setNhaCC(cursor.getString(2));

            _lst.add(obj);

        }
        cursor.close();
        return _lst;
    }

    public List<LoaiSach> getAll(){
        String sql="SELECT * FROM loaiSach";
        return getData(sql);
    }
    public LoaiSach getId(int id){
        String sql="SELECT * FROM loaiSach WHERE maLoaiSach=?";
        List<LoaiSach> _lst=getData(sql, String.valueOf(id));
        if (_lst.size()==0){
            return null;
        }
        return _lst.get(0);
    }
    public int getIDloaiSAch(String ten){

        List<LoaiSach> _lst=getAll();
        for (int i = 0; i < _lst.size(); i++) {
            LoaiSach loaiSach=_lst.get(i);
            if (loaiSach.getTenLoaiSach().equals(ten)){
                return loaiSach.getMaLoaiSach();
            }
        }
        return 0;
    }
}
