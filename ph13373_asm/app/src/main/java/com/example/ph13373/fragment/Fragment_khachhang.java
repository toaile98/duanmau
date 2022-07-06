package com.example.ph13373.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.adapter.Adapter_khachhang;
import com.example.ph13373.dao.ThanhVienDAO;
import com.example.ph13373.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Fragment_khachhang extends Fragment {

    private RecyclerView rycKhachhang;
    private FloatingActionButton flbAddKhachhang;

    ThanhVienDAO thanhVienDAO;
    Adapter_khachhang adapter_khachhang;



    public Fragment_khachhang() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_khachhang, container, false);
        rycKhachhang = (RecyclerView) view.findViewById(R.id.ryc_khachhang);
        flbAddKhachhang = (FloatingActionButton) view.findViewById(R.id.flb_add_khachhang);
        thanhVienDAO=new ThanhVienDAO(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycKhachhang.setLayoutManager(layoutManager);
         adapter_khachhang=new Adapter_khachhang(getContext(),thanhVienDAO.getAll());
         rycKhachhang.setAdapter(adapter_khachhang);

         flbAddKhachhang.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Dialog dialog = new Dialog(getContext());
                 dialog.setContentView(R.layout.dialog_add_kachhang);
                  EditText txtDialogAddKHTen;
                  EditText txtDialogAddKHSdt;
                  Button btnDialogAddKHOk;
                  Button btnDialogAddKHHuy;

                 txtDialogAddKHTen = (EditText) dialog.findViewById(R.id.txt_dialog_add_KH_ten);
                 txtDialogAddKHSdt = (EditText) dialog.findViewById(R.id.txt_dialog_add_KH_sdt);
                 btnDialogAddKHOk = (Button) dialog.findViewById(R.id.btn_dialog_add_KH_ok);
                 btnDialogAddKHHuy = (Button) dialog.findViewById(R.id.btn_dialog_add_KH_huy);

                 btnDialogAddKHHuy.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dialog.dismiss();
                     }
                 });
                 btnDialogAddKHOk.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (txtDialogAddKHTen.getText().toString().isEmpty()|| txtDialogAddKHSdt.getText().toString().isEmpty()){
                             Toast.makeText(getContext(),"Hãy nhập thông tin!",Toast.LENGTH_SHORT);
                         }else {
                             ThanhVien thanhVien = new ThanhVien();
                             thanhVien.setHoTen(txtDialogAddKHTen.getText().toString());
                             thanhVien.setSdt(txtDialogAddKHSdt.getText().toString());
                             if (thanhVienDAO.insert(thanhVien)<0){
                                 Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                             }else {
                                 Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                                 adapter_khachhang=new Adapter_khachhang(getContext(),thanhVienDAO.getAll());
                                 rycKhachhang.setAdapter(adapter_khachhang);
                                 dialog.dismiss();
                             }
                         }
                     }
                 });
                 dialog.show();
             }
         });


        return view;
    }
}