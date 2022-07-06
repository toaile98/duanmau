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
import com.example.ph13373.adapter.Adapter_loaiSach;
import com.example.ph13373.dao.LoaiSachDAO;
import com.example.ph13373.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Fragment_loaiSach extends Fragment {

    private RecyclerView rycLoaisach;
    private FloatingActionButton flbAddLoaiSach;
    LoaiSachDAO loaiSachDAO;
    Adapter_loaiSach adapter_loaiSach;




    public Fragment_loaiSach() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loai_sach, container, false);

        rycLoaisach = (RecyclerView) view.findViewById(R.id.ryc_loaisach);
        flbAddLoaiSach = (FloatingActionButton) view.findViewById(R.id.flb_add_loaiSach);
        loaiSachDAO=new LoaiSachDAO(getContext());


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycLoaisach.setLayoutManager(layoutManager);

        adapter_loaiSach = new Adapter_loaiSach(getContext(),loaiSachDAO.getAll());
        rycLoaisach.setAdapter(adapter_loaiSach);

        flbAddLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_loaisach);
                EditText editText =dialog.findViewById(R.id.txt_dialog_add_loaiSach);
                EditText editTextncc =dialog.findViewById(R.id.txt_dialog_add_loaiSach_ncc);
                Button ok,huy;
                ok=dialog.findViewById(R.id.btn_dialog_add_loaiSach_ok);
                huy=dialog.findViewById(R.id.btn_dialog_add_loaiSach_huy);
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().isEmpty()||editTextncc.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Hãy nhập thông tin!",Toast.LENGTH_SHORT).show();
                        }else {
                            LoaiSach loaiSach =new LoaiSach();
                            loaiSach.setTenLoaiSach(editText.getText().toString());
                            loaiSach.setNhaCC(editTextncc.getText().toString());
                            if (loaiSachDAO.insert(loaiSach)<0){
                                Toast.makeText(getContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
                                adapter_loaiSach = new Adapter_loaiSach(getContext(),loaiSachDAO.getAll());
                                rycLoaisach.setAdapter(adapter_loaiSach);
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