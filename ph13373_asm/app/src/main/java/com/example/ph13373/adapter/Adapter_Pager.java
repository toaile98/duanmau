package com.example.ph13373.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ph13373.fragment.Fragment_book;
import com.example.ph13373.fragment.Fragment_khachhang;

public class Adapter_Pager extends FragmentStateAdapter {

    public Adapter_Pager( Fragment fragment) {
        super(fragment);
    }

    public Fragment createFragment(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new Fragment_book();
                break;
            case 1:
                fragment=new Fragment_khachhang();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
