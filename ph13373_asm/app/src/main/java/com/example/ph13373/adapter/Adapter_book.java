package com.example.ph13373.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph13373.R;
import com.example.ph13373.dao.LoaiSachDAO;
import com.example.ph13373.dao.SachDAO;
import com.example.ph13373.model.LoaiSach;
import com.example.ph13373.model.Sach;

import java.util.List;

public class Adapter_book extends RecyclerView.Adapter<Adapter_book.Book_viewholder> {
    Context context;
    List<Sach> _lst;

    public Adapter_book(Context context, List<Sach> _lst) {
        this.context = context;
        this._lst = _lst;
    }
    public void updateList(List<Sach> lst){
        this._lst=lst;
        notifyDataSetChanged();
    }
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;

    public Book_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new Book_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter_book.Book_viewholder holder, int position) {

        Sach sach =_lst.get(position);
        sachDAO=new SachDAO(context.getApplicationContext());
        loaiSachDAO=new LoaiSachDAO(context.getApplicationContext());

        holder.tvItembookName.setText("Tên sách :"+sach.getTenSach());
        holder.tvItembookGiatien.setText("Giá thuê : "+String.valueOf(sach.getGiaThue()));

        LoaiSach loaiSach =loaiSachDAO.getId(sach.getMaLoaiSach());
        if (loaiSach!=null){
            String loaiTenSach = loaiSach.getTenLoaiSach();
            holder.tvItembookLoaisach.setText("Loại sách : "+loaiTenSach);
        }
        holder.tvItembookGiaKM.setText("Giá khuyến mại : "+sach.getGiaKM());

        holder.imgItembookEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgItembookDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_delete);
                Button ok,huy;
                ok=dialog.findViewById(R.id.btn_dialog_xoa_ok);
                huy=dialog.findViewById(R.id.btn_dialog_xoa_huy);
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if(sachDAO.delete(sach.getMaSach())<0){
                                Toast.makeText(v.getContext(), "Xóa thát bại!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(v.getContext(), "Xóa thành công!",Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }catch (Exception e){
                            Toast.makeText(v.getContext(), "Không thể xóa!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (_lst.size()!=0){
            return _lst.size();
        }
        return 0;
    }

    public class Book_viewholder extends RecyclerView.ViewHolder{

         TextView tvItembookName;
         TextView tvItembookGiatien;
         TextView tvItembookLoaisach;
         TextView tvItembookGiaKM;
         ImageView imgItembookEdit;
         ImageView imgItembookDelete;



        public Book_viewholder(@NonNull  View itemView) {
            super(itemView);
            tvItembookName = (TextView) itemView.findViewById(R.id.tv_itembook_name);
            tvItembookGiatien = (TextView) itemView.findViewById(R.id.tv_itembook_giatien);
            tvItembookLoaisach = (TextView) itemView.findViewById(R.id.tv_itembook_loaisach);
            tvItembookGiaKM = (TextView) itemView.findViewById(R.id.tv_itembook_giaKM);
            imgItembookEdit = (ImageView) itemView.findViewById(R.id.img_itembook_edit);
            imgItembookDelete = (ImageView) itemView.findViewById(R.id.img_itembook_delete);
        }
    }
}
