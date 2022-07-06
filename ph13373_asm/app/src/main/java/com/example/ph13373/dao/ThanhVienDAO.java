package com.example.ph13373.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.ThanhVien;
import com.example.ph13373.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }
    public long insert(ThanhVien obj){
        ContentValues contentValues  =new ContentValues();
        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("sdt",obj.getSdt());

        return db.insert("thanhVien",null,contentValues);
    }

    public int update(ThanhVien obj){
        ContentValues contentValues = new ContentValues();

        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("sdt",obj.getSdt());

        return db.update("thanhVien",contentValues,"maTV=?",new String[]{String.valueOf(obj.getMaTV())});
    }

    public int delete(int id){
        return db.delete("thanhVien","maTV=?",new String[]{String.valueOf(id)});
    }


    //=================================
    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);

        while (cursor.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV((cursor.getInt(0)));
            obj.setHoTen(cursor.getString(1));
            obj.setSdt(cursor.getString(2));

            _lst.add(obj);

        }
        cursor.close();
        return _lst;
    }

    public List<ThanhVien> getAll(){
        String sql="SELECT * FROM thanhVien";
        return getData(sql);
    }
    public ThanhVien getId(int id){
        String sql="SELECT * FROM thanhVien WHERE maTV=?";
        List<ThanhVien> _lst=getData(sql, String.valueOf(id));
        return _lst.get(0);
    }
}
