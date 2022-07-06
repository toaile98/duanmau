package com.example.ph13373.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph13373.database.SQliteHelper;
import com.example.ph13373.model.Sach;
import com.example.ph13373.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    private Context context;

    public ThongKeDAO(Context context) {
        this.context=context;
        SQliteHelper sQliteHelper = new SQliteHelper(context);
        db=sQliteHelper.getWritableDatabase();
    }
    public List<Top> getTop(){
        String sqlTop="SELECT maSach,count(maSach) as soLuong FROM phieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> _lst =new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = db.rawQuery(sqlTop,null);
        while (cursor.moveToNext()){
            Top top = new Top();
            Sach sach = sachDAO.getId(Integer.parseInt(cursor.getString(0)));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            _lst.add(top);


        }
        cursor.close();
        return _lst;
    }

    public int getDoanhThu(String tuNgay,String denNgay){
        String sqlDoangThu="SELECT SUM(tienThue) as doanhThu FROM phieuMuon WHERE ngayMuon BETWEEN ? AND ?";
        List<Integer> _lst = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoangThu,new String[]{tuNgay,denNgay});

        while (cursor.moveToNext()){
            try{
                _lst.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                _lst.add(0);
            }
        }
        return _lst.get(0);

    }
}
