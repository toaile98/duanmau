package com.example.ph13373.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ph13373.R;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.ThuThu;

public class Splash_activity extends AppCompatActivity {

    ThuThu thuThu;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        thuThuDAO = new ThuThuDAO(getApplicationContext());
        thuThu=new ThuThu();

        thuThu.setUsername("admin");
        thuThu.setPassword("admin");

        if (thuThuDAO.checkExist("admin")==-1){
            thuThuDAO.insert(thuThu);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },1500);
    }
}