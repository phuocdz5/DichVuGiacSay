package com.example.dichvugiacsay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.R;
import com.google.android.material.textfield.TextInputEditText;

public class Fragment_Info extends Fragment {
    private TextView tv_name;
    private TextInputEditText edtName,edtEmail,edtPhone,edtAddress;

    private Button btnBack;
    private MainActivity activity;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__info, container, false);
        btnBack = view.findViewById(R.id.btnBack);
        tv_name = view.findViewById(R.id.tv_nameInfo);
        edtName = view.findViewById(R.id.edtUserName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhoneNumber);
        edtAddress = view.findViewById(R.id.edtAddress);
        activity = (MainActivity) getActivity();
        user = activity.getUser();
        tv_name.setText(user.getName());
        edtName.setText(user.getName());
        edtEmail.setText(user.getEmail());
        edtPhone.setText(user.getPhone());
        btnBack.setOnClickListener(v->{
            loadFragment(new Fragment_Account());
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