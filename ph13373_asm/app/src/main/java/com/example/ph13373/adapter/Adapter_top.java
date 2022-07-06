package com.example.ph13373.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph13373.R;
import com.example.ph13373.model.Top;

import java.util.List;

public class Adapter_top extends RecyclerView.Adapter<Adapter_top.Top_viewHolder>{
   Context context;
    List<Top> _lst;

    public Adapter_top(Context context, List<Top> _lst) {
        this.context = context;
        this._lst = _lst;
    }

    public Top_viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10,parent,false);
        return new Top_viewHolder(view);
    }

    @Override
    public void onBindViewHolder( Adapter_top.Top_viewHolder holder, int position) {

        Top top =_lst.get(position);
        holder.tvItemTopTen.setText(top.getTenSach());
        holder.tvItemTopSl.setText("Số lượt mượn:"+String.valueOf(top.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        if (_lst.size()!=0){
            return _lst.size();
        }
        return 0;
    }

    public  class Top_viewHolder extends RecyclerView.ViewHolder{

        private TextView tvItemTopTen;
        private TextView tvItemTopSl;



        public Top_viewHolder( View itemView) {
            super(itemView);
            tvItemTopTen = (TextView) itemView.findViewById(R.id.tv_itemTop_ten);
            tvItemTopSl = (TextView) itemView.findViewById(R.id.tv_itemTop_sl);
        }
    }
}
