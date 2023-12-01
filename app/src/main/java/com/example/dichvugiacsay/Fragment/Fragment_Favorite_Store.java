package com.example.dichvugiacsay.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.dichvugiacsay.Adapter.StoreAdapter;
import com.example.dichvugiacsay.R;


public class Fragment_Favorite_Store extends Fragment {
    private RecyclerView recyclerView2;
    private StoreAdapter storeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__favorite__store, container, false);
        Button btnback = view.findViewById(R.id.btnBackStor);
        recyclerView2 = view.findViewById(R.id.recyclerViewStor);

        storeAdapter = new StoreAdapter(getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager1);

        recyclerView2.setAdapter(storeAdapter);
        storeAdapter.setListener(new StoreAdapter.StoreListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        loadFragment(new Fragment_Favorite_Store());
                        break;
                }
            }
        });
        btnback.setOnClickListener(v->{
            loadFragment(new Fragment_HomePage());
        });
        return view;
    }

    public void loadFragment(Fragment fragment){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main,null);
        FrameLayout frameLayout = view.findViewById(R.id.frameLayoutMain);
        getActivity().getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),fragment).commit();
    }
}