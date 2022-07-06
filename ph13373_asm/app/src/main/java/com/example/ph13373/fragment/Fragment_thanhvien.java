package com.example.ph13373.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.ph13373.adapter.Adapter_thanhvien;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.ThanhVien;
import com.example.ph13373.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Fragment_thanhvien extends Fragment {

    private RecyclerView rycThanhvien;
    private FloatingActionButton flbAddThanhvien;
    private EditText txtSearchTv;




    ThuThuDAO thuThuDAO;
    Adapter_thanhvien adapter_thanhvien;




    public Fragment_thanhvien() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_thanhvien, container, false);

        rycThanhvien = (RecyclerView) view.findViewById(R.id.ryc_thanhvien);
        flbAddThanhvien = (FloatingActionButton) view.findViewById(R.id.flb_add_thanhvien);


        thuThuDAO=new ThuThuDAO(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycThanhvien.setLayoutManager(layoutManager);

        adapter_thanhvien=new Adapter_thanhvien(thuThuDAO.getAll(),getContext());
        rycThanhvien.setAdapter(adapter_thanhvien);

        flbAddThanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_thanhvien);
                 EditText txtDialogAddThanhvienU;
                 EditText txtDialogAddThanhvienP;
                 Button btnDialogAddThanhvienOk;
                 Button btnDialogAddThanhvienHuy;

                txtDialogAddThanhvienU = (EditText) dialog.findViewById(R.id.txt_dialog_add_thanhvien_u);
                txtDialogAddThanhvienP = (EditText) dialog.findViewById(R.id.txt_dialog_add_thanhvien_p);
                btnDialogAddThanhvienOk = (Button) dialog.findViewById(R.id.btn_dialog_add_thanhvien_ok);
                btnDialogAddThanhvienHuy = (Button) dialog.findViewById(R.id.btn_dialog_add_thanhvien_huy);

                btnDialogAddThanhvienHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogAddThanhvienOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String regex="^([A-Z])";

                        if (txtDialogAddThanhvienU.getText().toString().isEmpty()||txtDialogAddThanhvienP.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Hãy nhập đủ thông tindsgdsfg!",Toast.LENGTH_SHORT).show();
                            return;
                        }else if (txtDialogAddThanhvienU.getText().toString().length()<5 || txtDialogAddThanhvienU.getText().toString().length()>15){
                            Toast.makeText(getContext(),"Độ dài username tối thiểu là 5 và tối đa là 15 kí tự!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (!txtDialogAddThanhvienU.getText().toString().substring(0,1).matches(regex)){
                            Toast.makeText(getContext(),"Username bắt đầu bằng chữ cái in hoa!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            ThuThu thuThu =new ThuThu();
                            String usern=txtDialogAddThanhvienU.getText().toString();
                            thuThu.setUsername(usern);
                            thuThu.setPassword(txtDialogAddThanhvienP.getText().toString());
                            if (thuThuDAO.checkExist(usern)==-1){
                                if (thuThuDAO.insert(thuThu)<0){
                                    Toast.makeText(getContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
                                    adapter_thanhvien=new Adapter_thanhvien(thuThuDAO.getAll(),getContext());
                                    rycThanhvien.setAdapter(adapter_thanhvien);
                                    dialog.dismiss();
                                }
                            }else {
                                Toast.makeText(getContext(),"Tài khoản "+usern+" đã tồn tại!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        rycThanhvien.setFilterTouchesWhenObscured(true);

    }
}