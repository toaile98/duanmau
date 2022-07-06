package com.example.ph13373.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.dao.ThongKeDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_doanhthu extends Fragment {

    private EditText txtDoanhthuBd;
    private EditText txtDoanhthuKt;
    private Button tiplDoanhthu3;
    private TextView tvKq;

    ThongKeDAO thongKeDAO;


    public Fragment_doanhthu() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doanhthu, container, false);
        txtDoanhthuBd = (EditText) view.findViewById(R.id.txt_doanhthu_bd);
        txtDoanhthuKt = (EditText) view.findViewById(R.id.txt_doanhthu_kt);
        tiplDoanhthu3 = (Button) view.findViewById(R.id.tipl_doanhthu_3);
        tvKq = (TextView) view.findViewById(R.id.tv_kq);
        thongKeDAO=new ThongKeDAO(getContext());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());
        txtDoanhthuKt.setText(strDate);

        txtDoanhthuBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                    txtDoanhthuBd.setText(year + "-" + (month + 1) + "-" + day);
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        txtDoanhthuKt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                    txtDoanhthuKt.setText(year + "-" + (month + 1) + "-" + day);
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        tiplDoanhthu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDoanhthuBd.getText().toString().isEmpty()||txtDoanhthuKt.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Nhập ngày tháng năm!",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    int doanhthuu=thongKeDAO.getDoanhThu(txtDoanhthuBd.getText().toString(),txtDoanhthuKt.getText().toString());
                    tvKq.setText("Tổng doanh thu: "+doanhthuu+" vnđ.");
                }
            }
        });

        return view;
    }
}