package com.example.dichvugiacsay.Fragment;

import static com.example.dichvugiacsay.R.id.frameLayoutMain;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dichvugiacsay.Login;
import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.OrderActivity;
import com.example.dichvugiacsay.R;
import com.example.dichvugiacsay.data.UserDAO;

public class Fragment_Account extends Fragment {
    private MainActivity activity;
    private RelativeLayout rlInfo,rlChangePass, rlLogout,rlDonHang;
    private TextView tvFullname,tvSdt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__account, container, false);
            rlInfo = view.findViewById(R.id.rlInfo);
            rlDonHang = view.findViewById(R.id.rlDonHang);
            rlChangePass = view.findViewById(R.id.rlChangePass);
            rlLogout = view.findViewById(R.id.rlLogout);
            tvFullname = view.findViewById(R.id.tv_UserName);
            tvSdt = view.findViewById(R.id.tv_SDT);
            activity = (MainActivity) getActivity();
            User user = activity.getUser();
            tvFullname.setText(user.getName());
            tvSdt.setText(user.getPhone());
            rlDonHang.setOnClickListener(v->{
                Intent i = new Intent(getContext(), OrderActivity.class);
                i.putExtra("user", user);
                startActivity(i);
            });
            rlInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadFragment(new Fragment_Info());
                }
            });
            rlChangePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadFragment(new Fragment_ChangePassword());
                }
            });
            rlLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
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