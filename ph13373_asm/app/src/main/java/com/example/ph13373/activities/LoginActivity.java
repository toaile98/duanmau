package com.example.ph13373.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {

    ThuThuDAO thuThuDAO;
    private EditText txtLoginUsername;
    private EditText txtLoginPassword;
    private CheckBox ckbLoginCheckluumk;
    private Button btnLoginDangnhap;
    private Button btnLoginHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        txtLoginUsername = (EditText) findViewById(R.id.txt_login_username);
        txtLoginPassword = (EditText) findViewById(R.id.txt_login_password);
        ckbLoginCheckluumk = (CheckBox) findViewById(R.id.ckb_login_checkluumk);
        btnLoginDangnhap = (Button) findViewById(R.id.btn_login_dangnhap);
        btnLoginHuy = (Button) findViewById(R.id.btn_login_huy);

        thuThuDAO = new ThuThuDAO(this);

        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        txtLoginUsername.setText(pref.getString("USERNAME",""));
        txtLoginPassword.setText(pref.getString("PASSWORD",""));
        ckbLoginCheckluumk.setChecked(pref.getBoolean("REMEMBER",false));

    }

    public void huy(View view) {
        txtLoginUsername.setText("");
        txtLoginPassword.setText("");
    }
    public void login(View view) {
        checkLogin();
    }
    public void checkLogin(){
        String username,pass;
        username=txtLoginUsername.getText().toString();
        pass=txtLoginPassword.getText().toString();
        if (username.isEmpty()||pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }else {
            int check=thuThuDAO.checkLogin(username,pass);
            if (check!=-1){
                Toast.makeText(getApplicationContext(),"Login thành công",
                        Toast.LENGTH_SHORT).show();
                rememberUSER(username,pass,ckbLoginCheckluumk.isChecked());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("iduser",check);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUSER(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit =pref.edit();

        if (!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }

        edit.commit();
    }


}