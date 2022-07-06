package com.example.ph13373.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph13373.R;
import com.example.ph13373.dao.PhieuMuonDAO;
import com.example.ph13373.dao.SachDAO;
import com.example.ph13373.dao.ThanhVienDAO;
import com.example.ph13373.dao.ThuThuDAO;
import com.example.ph13373.model.PhieuMuon;
import com.example.ph13373.model.ThanhVien;

import java.util.List;

public class Adapter_Phieumuon extends RecyclerView.Adapter<Adapter_Phieumuon.PhieuMuon_viewholder> {
    Context context;
    List<PhieuMuon> _lst;

    ThuThuDAO thuThuDAO;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    PhieuMuonDAO phieuMuonDAO;

    public Adapter_Phieumuon(Context context, List<PhieuMuon> _lst) {
        this.context = context;
        this._lst = _lst;
    }

    public PhieuMuon_viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieumuon,parent,false);
        return new PhieuMuon_viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Adapter_Phieumuon.PhieuMuon_viewholder holder, int position) {

        PhieuMuon phieuMuon =_lst.get(position);

        thuThuDAO=new ThuThuDAO(context.getApplicationContext());
        thanhVienDAO = new ThanhVienDAO(context.getApplicationContext());
        sachDAO = new SachDAO(context.getApplicationContext()) ;
        phieuMuonDAO = new PhieuMuonDAO(context.getApplicationContext());

        holder.tvItemphieumuonId.setText("Id phiếu mượn : "+String.valueOf(phieuMuon.getMaPhieuMuon()));
        holder.tvItemphieumuonTt.setText("Thủ thư : "+thuThuDAO.getNameUsser(phieuMuon.getMaTT()));
        holder.tvItemphieumuonKh.setText("Khách hàng : "+(thanhVienDAO.getId(phieuMuon.getMaTV())).getHoTen());
        holder.tvItemphieumuonSach.setText("Tên sách : "+(sachDAO.getId(phieuMuon.getMaSach())).getTenSach());
        holder.tvItemphieumuonTien.setText("Tiền thuê : "+String.valueOf(sachDAO.getTienThueID(phieuMuon.getMaSach())));
        holder.tvItemphieumuonNgay.setText("Ngày thuê : "+String.valueOf(phieuMuon.getNgayMuon()));
        if (phieuMuon.getTraSach()==0){
            holder.tvItemphieumuonTrangthai.setText("Trạng thái: Chưa trả sách");
            holder.tvItemphieumuonTrangthai.setTextColor(R.color.red);
        }else {
            holder.tvItemphieumuonTrangthai.setText("Trạng thái: Đã trả sách");
            holder.tvItemphieumuonTrangthai.setTextColor(R.color.Green);
            holder.imgItembookEdit.setVisibility(View.INVISIBLE);
        }


        //////click
        holder.imgItembookEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phieuMuon.getTraSach()==0){
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.dialog_edit_phieumuon);
                     TextView tvDialogEditPhieumuonKh;
                     TextView tvDialogEditPhieumuonSach;
                     TextView tvDialogEditPhieumuonTien;
                     TextView tvDialogEditPhieumuonNgay;
                     CheckBox tvDialogEditPhieumuonTrasach;
                     Button btnDialogEditKHOk;
                     Button btnDialogEditKHHuy;

                    tvDialogEditPhieumuonKh = (TextView) dialog.findViewById(R.id.tv_dialog_edit_phieumuon_kh);
                    tvDialogEditPhieumuonSach = (TextView) dialog.findViewById(R.id.tv_dialog_edit_phieumuon_sach);
                    tvDialogEditPhieumuonTien = (TextView) dialog.findViewById(R.id.tv_dialog_edit_phieumuon_tien);
                    tvDialogEditPhieumuonNgay = (TextView) dialog.findViewById(R.id.tv_dialog_edit_phieumuon_ngay);
                    tvDialogEditPhieumuonTrasach = (CheckBox) dialog.findViewById(R.id.tv_dialog_edit_phieumuon_trasach);
                    btnDialogEditKHOk = (Button) dialog.findViewById(R.id.btn_dialog_edit_KH_ok);
                    btnDialogEditKHHuy = (Button) dialog.findViewById(R.id.btn_dialog_edit_KH_huy);

                    tvDialogEditPhieumuonKh.setText((thanhVienDAO.getId(phieuMuon.getMaTV())).getHoTen());
                    tvDialogEditPhieumuonSach.setText((sachDAO.getId(phieuMuon.getMaSach())).getTenSach());
                    tvDialogEditPhieumuonTien.setText("Tiền thuê : "+String.valueOf(sachDAO.getTienThueID(phieuMuon.getMaSach())));
                    tvDialogEditPhieumuonNgay.setText("Ngày thuê : "+String.valueOf(phieuMuon.getNgayMuon()));



                    btnDialogEditKHHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btnDialogEditKHOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (tvDialogEditPhieumuonTrasach.isChecked()){
                                phieuMuon.setTraSach(1);

                                if (phieuMuonDAO.update(phieuMuon)<0){
                                    Toast.makeText(context.getApplicationContext(), "Sửa thất bại!",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(context.getApplicationContext(), "Sửa thành công!",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();

                                    dialog.dismiss();
                                }
                            }else {
                                
                                dialog.dismiss();
                            }
                        }
                    });



                    dialog.show();
                }
            }
        });
//        holder.imgItembookDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog =new Dialog(v.getContext());
//                dialog.setContentView(R.layout.dialog_delete);
//                Button ok,huy;
//                ok=dialog.findViewById(R.id.btn_dialog_xoa_ok);
//                huy=dialog.findViewById(R.id.btn_dialog_xoa_huy);
//                huy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            if (phieuMuonDAO.delete(phieuMuon.getMaPhieuMuon())<0){
//                                Toast.makeText(v.getContext(), "Xoa that bai!",Toast.LENGTH_SHORT).show();
//                            }else {
//                                Toast.makeText(v.getContext(), "Xoa thành công!",Toast.LENGTH_SHORT).show();
//                                notifyDataSetChanged();
//                                dialog.dismiss();
//                            }
//                        }catch (Exception e){
//                            Toast.makeText(v.getContext(),"Không thể xóa!",Toast.LENGTH_SHORT ).show();
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                dialog.show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (_lst.size()!=0){
            return _lst.size();
        }
        return 0;
    }

    public class PhieuMuon_viewholder extends RecyclerView.ViewHolder{

        private TextView tvItemphieumuonId;
        private TextView tvItemphieumuonTt;
        private TextView tvItemphieumuonKh;
        private TextView tvItemphieumuonSach;
        private TextView tvItemphieumuonTien;
        private TextView tvItemphieumuonNgay;
        private TextView tvItemphieumuonTrangthai;
        private ImageView imgItembookEdit;
        private ImageView imgItembookDelete;



        public PhieuMuon_viewholder(@NonNull  View itemView) {
            super(itemView);
            tvItemphieumuonId = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_id);
            tvItemphieumuonTt = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_tt);
            tvItemphieumuonKh = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_kh);
            tvItemphieumuonSach = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_sach);
            tvItemphieumuonTien = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_tien);
            tvItemphieumuonNgay = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_ngay);
            tvItemphieumuonTrangthai = (TextView) itemView.findViewById(R.id.tv_itemphieumuon_trangthai);
            imgItembookEdit = (ImageView) itemView.findViewById(R.id.img_itembook_edit);
//            imgItembookDelete = (ImageView) itemView.findViewById(R.id.img_itembook_delete);
        }
    }
}
