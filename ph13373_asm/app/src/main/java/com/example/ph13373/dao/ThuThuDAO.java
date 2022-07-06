package com.example.ph13373.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }


    public long insert(ThuThu obj){
        ContentValues contentValues  =new ContentValues();
        contentValues.put("username",obj.getUsername());
        contentValues.put("password",obj.getPassword());

        return db.insert("thuThu",null,contentValues);
    }

    public int update(ThuThu obj){
        ContentValues contentValues = new ContentValues();


        contentValues.put("password",obj.getPassword());

        return db.update("thuThu",contentValues,"maTT=?",new String[]{String.valueOf(obj.getMaTT())});
    }

    public int delete(int id){
        return db.delete("thuthu","maTT=?",new String[]{String.valueOf(id)});
    }


    //=================================
    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);

        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();

            thuThu.setMaTT(cursor.getInt(cursor.getColumnIndex("maTT")));
            thuThu.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            thuThu.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            _lst.add(thuThu);

        }
        cursor.close();
        return _lst;
    }

    public List<ThuThu> getAll(){
        String sql="SELECT * FROM thuThu";
        return getData(sql);
    }
    public ThuThu getUsername(String u){
        String sql="SELECT * FROM thuThu WHERE username=?";
        List<ThuThu> _lst=getData(sql, u);
        return _lst.get(0);
    }
    public String getNameUsser(int id){
        String sql="SELECT * FROM thuThu WHERE maTT=?";
        List<ThuThu> _lst=getData(sql, String.valueOf(id));
        return _lst.get(0).getUsername();
    }
    public int checkLogin(String u,String p){
        String query = "SELECT * FROM thuthu WHERE username=? AND password=?";
        List<ThuThu> list = getData(query,u,p);
        if (list.size() == 0){
            return -1;
        }

        return list.get(0).getMaTT();
    }
    public int checkExist(String u){
        String query = "SELECT * FROM thuthu WHERE username=? ";
        List<ThuThu> list = getData(query,u);
        if (list.size() == 0){
            return -1;
        }

        return list.get(0).getMaTT();
    }
}
