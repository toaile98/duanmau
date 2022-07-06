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
import com.example.ph13373.dao.ThanhVienDAO;
import com.example.ph13373.model.ThanhVien;

import java.util.List;

public class Adapter_khachhang extends RecyclerView.Adapter<Adapter_khachhang.Khachhang_viewholder> {
    Context context;
    List<ThanhVien> _lst;

    public Adapter_khachhang(Context context, List<ThanhVien> _lst) {
        this.context = context;
        this._lst = _lst;
    }

    ThanhVienDAO thanhVienDAO;

    public Khachhang_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang,parent,false);
        return new Khachhang_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_khachhang.Khachhang_viewholder holder, int position) {

        ThanhVien thanhVien = _lst.get(position);
        thanhVienDAO=new ThanhVienDAO(context.getApplicationContext());
        holder.tvItemkhachhang.setText(thanhVien.getHoTen());
        holder.tvItemthanviensdt.setText("Sdt : "+thanhVien.getSdt());
        holder.imgItemkhachangEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgItemkhachhangDelete.setOnClickListener(new View.OnClickListener() {
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
                            if (thanhVienDAO.delete(thanhVien.getMaTV())<0){
                                Toast.makeText(v.getContext(), "Xóa thất bại!",Toast.LENGTH_SHORT).show();
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

    public class Khachhang_viewholder extends RecyclerView.ViewHolder{

        private TextView tvItemkhachhang;
        private ImageView imgItemkhachangEdit;
        private ImageView imgItemkhachhangDelete;
        private TextView tvItemthanviensdt;






        public Khachhang_viewholder(@NonNull View itemView) {
            super(itemView);
            tvItemkhachhang = (TextView) itemView.findViewById(R.id.tv_itemkhachhang);
            imgItemkhachangEdit = (ImageView) itemView.findViewById(R.id.img_itemkhachang_edit);
            imgItemkhachhangDelete = (ImageView) itemView.findViewById(R.id.img_itemkhachhang_delete);
            tvItemthanviensdt = (TextView) itemView.findViewById(R.id.tv_itemkhachhangsdt);
        }
    }
}
