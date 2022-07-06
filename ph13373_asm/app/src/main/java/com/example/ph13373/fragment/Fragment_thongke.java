package com.example.ph13373.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ph13373.R;
import com.example.ph13373.adapter.Adapter_top;
import com.example.ph13373.dao.ThongKeDAO;


public class Fragment_thongke extends Fragment {

    private RecyclerView rycTop10;
    ThongKeDAO thongKeDAO;
    Adapter_top adapter_top;


    public Fragment_thongke() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_thongke, container, false);
        rycTop10 = (RecyclerView) view.findViewById(R.id.ryc_top10);
        thongKeDAO=new ThongKeDAO(getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rycTop10.setLayoutManager(layoutManager);

        adapter_top=new Adapter_top(getContext(),thongKeDAO.getTop());
        rycTop10.setAdapter(adapter_top);



        return view;
    }
}