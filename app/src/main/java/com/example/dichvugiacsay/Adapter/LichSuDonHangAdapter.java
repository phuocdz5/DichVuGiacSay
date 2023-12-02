package com.example.dichvugiacsay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.R;

import java.util.List;


public class LichSuDonHangAdapter extends RecyclerView.Adapter<LichSuDonHangAdapter.LichSuDonHangViewHoderl>{
    private final Context context;
    private List<LichSuDonHang> mlist;

    public LichSuDonHangAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LichSuDonHang> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LichSuDonHangViewHoderl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_lich_su_don_hang, parent, false);
        return new LichSuDonHangViewHoderl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDonHangViewHoderl holder, int position) {
        LichSuDonHang lichSuDonHang = mlist.get(position);
        if ( lichSuDonHang == null){
            return;
        }
        // set dử liệu
        holder.tv_DH.setText(lichSuDonHang.getMaDH());
        holder.tv_name.setText(lichSuDonHang.getName());
        holder.tv_trangthai.setText(lichSuDonHang.getTrangthai());
        holder.tv_gia.setText(lichSuDonHang.getGia());



    }

    @Override
    public int getItemCount() {
        if (mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class LichSuDonHangViewHoderl extends RecyclerView.ViewHolder{
        private final TextView tv_DH, tv_name, tv_trangthai, tv_gia;
        private ImageView img;
        private Button btnDatLai, btnDanhGia;

        public LichSuDonHangViewHoderl(@NonNull View itemView) {
            super(itemView);
            tv_DH =itemView.findViewById(R.id.tv_DH);
            tv_name =itemView.findViewById(R.id.tv_Name);
            tv_trangthai =itemView.findViewById(R.id.tv_giaoHang);
            tv_gia =itemView.findViewById(R.id.tv_gia);
        }
    }
}
