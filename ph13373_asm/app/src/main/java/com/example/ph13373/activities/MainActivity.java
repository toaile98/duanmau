package com.example.ph13373.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ph13373.R;
import com.example.ph13373.fragment.Fragment_book;
import com.example.ph13373.fragment.Fragment_doanhthu;
import com.example.ph13373.fragment.Fragment_doiMK;
import com.example.ph13373.fragment.Fragment_khachhang;
import com.example.ph13373.fragment.Fragment_loaiSach;
import com.example.ph13373.fragment.Fragment_phieumuon;
import com.example.ph13373.fragment.Fragment_thanhvien;
import com.example.ph13373.fragment.Fragment_thongke;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    Intent intent;

    public static  int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"Chào mừng đến với trang chủ!",Toast.LENGTH_SHORT).show();

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        intent=getIntent();
        String user = intent.getStringExtra("user");
         id_user=intent.getIntExtra("iduser",-1);

        List<com.shrikanthravi.customnavigationdrawer2.data.MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Phiếu mượn",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Loại sách",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Sách",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Khách hàng",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Thống kê top 10",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Doanh thu",R.drawable.ic_book));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("USER : " +user,R.drawable.ic_book));
        if (true){
            menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Quản lý User",R.drawable.ic_book));
        }


        //then add them to navigation drawer

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  Fragment_phieumuon.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }



        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){

                    case 0:{
                        fragmentClass = Fragment_phieumuon.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = Fragment_loaiSach.class;
                        break;
                    }
                    case 2:{
                        fragmentClass = Fragment_book.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = Fragment_khachhang.class;
                        break;
                    }
                    case 4:{
                        fragmentClass = Fragment_thongke.class;
                        break;
                    }
                    case 5:{
                        fragmentClass = Fragment_doanhthu.class;
                        break;
                    }
                    case 6:{
                        fragmentClass = Fragment_doiMK.class;
                        break;
                    }
                    case 7:{
                        fragmentClass = Fragment_thanhvien.class;
                        break;
                    }

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }
}

//    setTypeface(null,Typeface.BOLD);