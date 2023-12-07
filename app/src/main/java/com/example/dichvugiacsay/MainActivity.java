package com.example.dichvugiacsay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dichvugiacsay.Fragment.Fragment_Account;
import com.example.dichvugiacsay.Fragment.Fragment_Cart;
import com.example.dichvugiacsay.Fragment.Fragment_HomePage;
import com.example.dichvugiacsay.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottom_navigation;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        if(savedInstanceState==null){
            fragmentData(new Fragment_HomePage());
        }


        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.trang_chu) {
                    fragmentData(new Fragment_HomePage());
                } else if (id == R.id.don_hang) {
                    fragmentData(new Fragment_Cart());
                } else {
                   fragmentData(new Fragment_Account());
                }
                return true;
            }
        });




    }
    public void fragmentData(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain,fragment).commit();
    }
    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }

        this.doubleBack = true;
        Toast.makeText(this, "Bấm thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBack=false;
            }
        }, 2000);
    }

    public User getUser(){
        return user;
    }




}