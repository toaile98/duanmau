package com.example.ph13373.adapter;

import android.annotation.SuppressLint;
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
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph13373.R;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.ThuThu;

import java.util.List;

import static com.example.ph13373.R.color.Green;
import static com.example.ph13373.R.color.red;

public class Adapter_thanhvien extends RecyclerView.Adapter<Adapter_thanhvien.Thanhvien_viewholder> {
    List<ThuThu> _lst;

    Context context;

    public Adapter_thanhvien(List<ThuThu> _lst, Context context) {
        this._lst = _lst;
        this.context = context;
    }
    ThuThuDAO thuThuDAO;


    @Override
    public Thanhvien_viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhvien,parent,false);
        return new Thanhvien_viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull  Adapter_thanhvien.Thanhvien_viewholder holder, int position) {


        ThuThu thuThu =_lst.get(position);
        thuThuDAO = new ThuThuDAO(context.getApplicationContext());
        holder.tv.setText(thuThu.getUsername());


//        if (position%2==0){
//            holder.tv.setTextColor(ResourcesCompat.getColor(context.getResources(), Green, null));
//
//
//        }else {
//            holder.tv.setTextColor(ResourcesCompat.getColor(context.getResources(), red, null));        }



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(v.getContext());
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
                        if (thuThuDAO.delete(thuThu.getMaTT())<0){
                            Toast.makeText(v.getContext(), "Xoa that bai!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(v.getContext(), "Xoa thanh cong!",Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
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

    public class Thanhvien_viewholder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView edit,delete;
        public Thanhvien_viewholder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv_itemthanvien);
            edit=itemView.findViewById(R.id.img_itemthanhvien_edit);
            delete=itemView.findViewById(R.id.img_itemthanhvien_delete);
        }
    }
}
