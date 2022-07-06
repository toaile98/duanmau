package com.example.ph13373.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.activities.MainActivity;
import com.example.ph13373.adapter.Adapter_Phieumuon;
import com.example.ph13373.dao.PhieuMuonDAO;
import com.example.ph13373.dao.SachDAO;
import com.example.ph13373.dao.ThanhVienDAO;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.PhieuMuon;
import com.example.ph13373.model.Sach;
import com.example.ph13373.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Fragment_phieumuon extends Fragment {


    private RecyclerView rycPhieumuon;
    private FloatingActionButton flbAddPhieumuon;

    ThuThuDAO thuThuDAO;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    PhieuMuonDAO phieuMuonDAO;
    Adapter_Phieumuon adapter_phieumuon;

    int maKH,maSach,tien;



    List<Sach> _lst_sach;
    List<ThanhVien> _lst_KH ;
    public Fragment_phieumuon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_phieumuon, container, false);
        rycPhieumuon = (RecyclerView) view.findViewById(R.id.ryc_phieumuon);
        flbAddPhieumuon = (FloatingActionButton) view.findViewById(R.id.flb_add_phieumuon);

        thuThuDAO = new ThuThuDAO(getContext());
        thanhVienDAO=new ThanhVienDAO(getContext());
        sachDAO=new SachDAO(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycPhieumuon.setLayoutManager(layoutManager);

        adapter_phieumuon = new Adapter_Phieumuon(getContext(),phieuMuonDAO.getAll());
        rycPhieumuon.setAdapter(adapter_phieumuon);



        flbAddPhieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_phieumuon);
                 Spinner spnDialogAddPhieuKh;
                 Spinner spnDialogAddPhieuSach;
                 TextView tvDialogAddPhieuTien;
                 EditText txtDialogAddPhieuNgay;
                 CheckBox ckbDialogAddPhieuTrasach;
                 Button btnDialogAddKHOk;
                 Button btnDialogAddKHHuy;

                spnDialogAddPhieuKh = (Spinner) dialog.findViewById(R.id.spn_dialog_add_phieu_kh);
                spnDialogAddPhieuSach = (Spinner) dialog.findViewById(R.id.spn_dialog_add_phieu_sach);
                tvDialogAddPhieuTien = (TextView) dialog.findViewById(R.id.tv_dialog_add_phieu_tien);
                txtDialogAddPhieuNgay = (EditText) dialog.findViewById(R.id.txt_dialog_add_phieu_ngay);
                ckbDialogAddPhieuTrasach = (CheckBox) dialog.findViewById(R.id.ckb_dialog_add_phieu_trasach);
                btnDialogAddKHOk = (Button) dialog.findViewById(R.id.btn_dialog_add_KH_ok);
                btnDialogAddKHHuy = (Button) dialog.findViewById(R.id.btn_dialog_add_KH_huy);

                // spiner KH
                _lst_KH =new ArrayList<>();
                _lst_KH=thanhVienDAO.getAll();
                if (_lst_KH.size()==0){
                    Toast.makeText(getContext(),"Hãy thêm mới khách hàng!",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> _lst_KH2=new ArrayList<>();
                for (int i = 0; i < _lst_KH.size(); i++) {
                    ThanhVien thanhVien =_lst_KH.get(i);
                    _lst_KH2.add(thanhVien.getHoTen());
                }
                ArrayAdapter<String> adapterKH = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,_lst_KH2);
                adapterKH.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spnDialogAddPhieuKh.setAdapter(adapterKH);

                spnDialogAddPhieuKh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maKH=_lst_KH.get(position).getMaTV();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //

                //spiner sach
                _lst_sach = new ArrayList<>();
                _lst_sach=sachDAO.getAll();
                if (_lst_sach.size()==0){
                    Toast.makeText(getContext(),"Hãy thêm mới sách!",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> _lst_sach2=new ArrayList<>();
                for (int i = 0; i < _lst_sach.size(); i++) {
                    Sach sach =_lst_sach.get(i);
                    _lst_sach2.add(sach.getTenSach());
                }
                ArrayAdapter<String> adapterSach = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,_lst_sach2);
                adapterSach.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spnDialogAddPhieuSach.setAdapter(adapterSach);

                //tv gia thue
                spnDialogAddPhieuSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSach=_lst_sach.get(position).getMaSach();
                        tien=_lst_sach.get(position).getGiaThue();

                        tvDialogAddPhieuTien.setText("Giá thuê : "+String.valueOf(tien));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // date


                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = sdf.format(c.getTime());
                txtDialogAddPhieuNgay.setText(strDate);

                    txtDialogAddPhieuNgay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                                txtDialogAddPhieuNgay.setText(year + "-" + (month + 1) + "-" + day);
                            }, Calendar.getInstance().get(Calendar.YEAR),
                                    Calendar.getInstance().get(Calendar.MONTH),
                                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                            datePickerDialog.show();
                        }
                    });



                // on click
                btnDialogAddKHHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogAddKHOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PhieuMuon phieuMuon=new PhieuMuon();
                        phieuMuon.setMaTT(MainActivity.id_user);
                        phieuMuon.setMaTV(maKH);
                        phieuMuon.setMaSach(maSach);
                        phieuMuon.setTienThue(tien);

                        if (ckbDialogAddPhieuTrasach.isChecked()){
                            phieuMuon.setTraSach(1);
                        }else {
                            phieuMuon.setTraSach(0);
                        }
                        phieuMuon.setNgayMuon(txtDialogAddPhieuNgay.getText().toString());

                        if (phieuMuonDAO.insert(phieuMuon)<0){
                            Toast.makeText(getContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
                            adapter_phieumuon = new Adapter_Phieumuon(getContext(),phieuMuonDAO.getAll());
                            rycPhieumuon.setAdapter(adapter_phieumuon);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
        return  view;
    }
}