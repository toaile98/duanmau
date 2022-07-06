package com.example.ph13373.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.activities.LoginActivity;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.ThuThu;


public class Fragment_doiMK extends Fragment {



    private EditText txtChangeMk;
    private EditText txtChangeMkNew1;
    private EditText txtChangeMkNew2;
    private Button btnChangeOk;
    private Button btnChangeHuy;
    private Button btnChangeDangxuat;



    ThuThuDAO thuThuDAO;

    public Fragment_doiMK() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doi_m_k, container, false);


        txtChangeMk = (EditText) view.findViewById(R.id.txt_change_mk);
        txtChangeMkNew1 = (EditText) view.findViewById(R.id.txt_change_mk_new1);
        txtChangeMkNew2 = (EditText) view.findViewById(R.id.txt_change_mk_new2);
        btnChangeOk = (Button) view.findViewById(R.id.btn_change_ok);
        btnChangeHuy = (Button) view.findViewById(R.id.btn_change_huy);
        btnChangeDangxuat = (Button) view.findViewById(R.id.btn_change_dangxuat);


        thuThuDAO=new ThuThuDAO(getContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        btnChangeHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChangeMk.setText("");
                txtChangeMkNew1.setText("");
                txtChangeMkNew2.setText("");
            }
        });
        btnChangeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu =thuThuDAO.getUsername(user);
                    thuThu.setPassword(txtChangeMkNew1.getText().toString());
                    if (thuThuDAO.update(thuThu)<0){
                        Toast.makeText(getContext(),"Thay đổi mật khẩu thất bại!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"Thay đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                        txtChangeMk.setText("");
                        txtChangeMkNew1.setText("");
                        txtChangeMkNew2.setText("");
                    }
                }
            }
        });
        btnChangeDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_dangxuat);
                Button btn_ok,btn_huy;
                btn_huy=dialog.findViewById(R.id.btn_dialog_dangxuat_huy);
                btn_ok=dialog.findViewById(R.id.btn_dialog_dangxuat_ok);
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                dialog.show();
            }
        });
    }

    private int validate() {
        if (txtChangeMk.getText().toString().isEmpty() || txtChangeMkNew1.getText().toString().isEmpty() || txtChangeMkNew2.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"Hãy nhập đầy đủ thông tin mật khẩu!",Toast.LENGTH_SHORT).show();
            return -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld =pref.getString("PASSWORD","");
            String pass =txtChangeMkNew1.getText().toString();
            String rePass=txtChangeMkNew2.getText().toString();
            if (!passOld.equals(txtChangeMk.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai!",Toast.LENGTH_SHORT).show();
                return -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu mới không khớp!",Toast.LENGTH_SHORT).show();
                return -1;
            }
        }
        return 1;
    }
}