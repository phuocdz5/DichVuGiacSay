package com.example.dichvugiacsay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dichvugiacsay.Adapter.LichSuDonHangAdapter;
import com.example.dichvugiacsay.Model.LichSuDonHang;
import com.example.dichvugiacsay.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_History extends Fragment {
    private RecyclerView recyclerView;
    private LichSuDonHangAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__history, container, false);

        recyclerView = view.findViewById(R.id.rcv_lichSuDonHang);
        adapter = new LichSuDonHangAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter.setData(getListUser());
        recyclerView.setAdapter(adapter);
        return view;
    }
    private List<LichSuDonHang> getListUser(){
        List<LichSuDonHang > list = new ArrayList<>();
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));
        list.add(new LichSuDonHang("#DH_123","Áo/Quần","Đã Giao  SL:3","10000VND",R.drawable.img));

        return list;

    }
}