package com.example.ph13373.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.activities.LoginActivity;
import com.example.ph13373.adapter.Adapter_book;
import com.example.ph13373.adapter.Adapter_loaiSach;
import com.example.ph13373.dao.LoaiSachDAO;
import com.example.ph13373.dao.SachDAO;
import com.example.ph13373.model.LoaiSach;
import com.example.ph13373.model.Sach;
import com.google.android.material.datepicker.OnSelectionChangedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Fragment_book extends Fragment {


    private RecyclerView rycBook;
    private FloatingActionButton flbAddBook;
    private EditText txtFrgBookSearch;



    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;
    Adapter_book adapter_book;



    public Fragment_book() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_book, container, false);



        sachDAO= new SachDAO(getContext());
        loaiSachDAO= new LoaiSachDAO(getContext());

        rycBook = (RecyclerView) view.findViewById(R.id.ryc_book);
        flbAddBook = (FloatingActionButton) view.findViewById(R.id.flb_add_book);
        txtFrgBookSearch = (EditText) view.findViewById(R.id.txt_frg_book_search);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycBook.setLayoutManager(layoutManager);

        adapter_book= new Adapter_book(getContext(),sachDAO.getAll());
        rycBook.setAdapter(adapter_book);

        flbAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_book);
                 EditText txtDialogAddSachTen;
                 EditText txtDialogAddSachTien;
                 Spinner spnDialogAddSach;
                 Button btnDialogAddSachOk;
                 Button btnDialogAddSachHuy;

                txtDialogAddSachTen = (EditText) dialog.findViewById(R.id.txt_dialog_add_sach_ten);
                txtDialogAddSachTien = (EditText) dialog.findViewById(R.id.txt_dialog_add_sach_tien);
                spnDialogAddSach = (Spinner) dialog.findViewById(R.id.spn_dialog_add_sach);
                btnDialogAddSachOk = (Button) dialog.findViewById(R.id.btn_dialog_add_sach_ok);
                btnDialogAddSachHuy = (Button) dialog.findViewById(R.id.btn_dialog_add_sach_huy);

                List<LoaiSach> _lst_loai = new ArrayList<>();
                _lst_loai=loaiSachDAO.getAll();
                if (_lst_loai.size()==0){
                    Toast.makeText(getContext(),"Thêm loại sách trước!",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> _lst_tenLoai = new ArrayList<>();
                for (int i = 0; i < _lst_loai.size(); i++) {
                    LoaiSach loaiSach = _lst_loai.get(i);
                    _lst_tenLoai.add(loaiSach.getTenLoaiSach());
                }
                ArrayAdapter<String> adapterLoai = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,_lst_tenLoai);
                adapterLoai.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spnDialogAddSach.setAdapter(adapterLoai);

                btnDialogAddSachHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogAddSachOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtDialogAddSachTen.getText().toString().isEmpty()||txtDialogAddSachTien.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Hãy nhâp thông tin sách!",Toast.LENGTH_SHORT).show();
                        }else {
                            Sach sach = new Sach();
                            sach.setTenSach(txtDialogAddSachTen.getText().toString());
                            sach.setGiaThue(Integer.parseInt(txtDialogAddSachTien.getText().toString()));
                            int id =loaiSachDAO.getIDloaiSAch(String.valueOf(spnDialogAddSach.getSelectedItem()) );
                            sach.setMaLoaiSach(id);

                            if (sachDAO.insert(sach)<0){
                                Toast.makeText(getContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
                                adapter_book=new Adapter_book(getContext(),sachDAO.getAll());
                                rycBook.setAdapter(adapter_book);
                                dialog.dismiss();
                            }
                        }
                    }
                });
                dialog.show();

            }
        });
        txtFrgBookSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tim(s.toString());
            }
        });
        return view;
    }

    private void tim(String toString) {
        List<Sach> tim = new ArrayList<>();
        List<Sach> all= sachDAO.getAll();
        for (int i = 0; i < all.size(); i++) {
            Sach sach = all.get(i);
            if (sach.getTenSach().contains(toString)){
                tim.add(sach);
            }
        }
        adapter_book.updateList(tim);

    }
}