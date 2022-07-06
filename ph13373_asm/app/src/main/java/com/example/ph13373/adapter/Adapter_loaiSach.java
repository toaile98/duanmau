package com.example.ph13373.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph13373.R;
import com.example.ph13373.dao.LoaiSachDAO;
import com.example.ph13373.model.LoaiSach;

import java.util.List;
import java.util.logging.Handler;

public class Adapter_loaiSach extends RecyclerView.Adapter<Adapter_loaiSach.LoaiSachViewHolder> {
    private Context context;
    List<LoaiSach> _lst;

    LoaiSachDAO loaiSachDAO;

    public Adapter_loaiSach(Context context, List<LoaiSach> _lst) {
        this.context = context;
        this._lst = _lst;
    }

    public LoaiSachViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach,parent,false);
        return new LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter_loaiSach.LoaiSachViewHolder holder, int position) {
        loaiSachDAO=new LoaiSachDAO(context.getApplicationContext());

        LoaiSach loaiSach =_lst.get(position);
        if (loaiSach==null){
            return;
        }
        String ten=loaiSach.getTenLoaiSach();
        holder.tv.setText("Thể loại :"+ten);
        //
        if (ten.contains("N")){
            holder.tv.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.Green, null));
        }else if (ten.contains("A")){
            holder.tv.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.red, null));
        }else {
            holder.tv.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.black, null));
        }


        holder.tv2.setText("Nhà cung cấp :"+loaiSach.getNhaCC());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_loaisach_edit);
                EditText txt_ten;
                Button huy,ok;
                txt_ten=dialog.findViewById(R.id.txt_dialog_edit_loaisach);
                huy=dialog.findViewById(R.id.btn_dialog_editloaiSach_huy);
                ok=dialog.findViewById(R.id.btn_dialog_editloaiSach_ok);
                txt_ten.setText(loaiSach.getTenLoaiSach());
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txt_ten.getText().toString().isEmpty()){
                            Toast.makeText(v.getContext(), "Nhập dữ liệu!",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            loaiSach.setTenLoaiSach(txt_ten.getText().toString());
                            if (loaiSachDAO.update(loaiSach)<0){
                                Toast.makeText(v.getContext(), "Sửa thất bại!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(v.getContext(), "Sửa thành công!",Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(v.getContext());
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
                            if (loaiSachDAO.delete(loaiSach.getMaLoaiSach())<0){
                                Toast.makeText(v.getContext(), "Xoa that bai!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(v.getContext(), "Xoa thanh cong!",Toast.LENGTH_SHORT).show();

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

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder{

        private TextView tv,tv2;
        private ImageView edit,delete;

        public LoaiSachViewHolder(@NonNull  View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv_itemLoaiSach_tenloaisach);
            tv2=itemView.findViewById(R.id.tv_itemLoaiSach_tennhaCC);
            edit=itemView.findViewById(R.id.img_itemLoaiSach_edit);
            delete=itemView.findViewById(R.id.img_itemLoaiSach_delete);
        }
    }
}
