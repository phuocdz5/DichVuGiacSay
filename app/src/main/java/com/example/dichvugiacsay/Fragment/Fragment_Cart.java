package com.example.dichvugiacsay.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.Adapter.CartAdapter;
import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.Cart;
import com.example.dichvugiacsay.R;
import com.example.dichvugiacsay.data.CartDAO;

import java.util.ArrayList;

public class Fragment_Cart extends Fragment {
    private RecyclerView rcv;
    private Button btnbook;
    private CartAdapter cartAdapter;
    private CartDAO cartDAO;
    private TextView txtprice;
    private ArrayList<Cart> arr;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cart, container, false);
        cartDAO = new CartDAO(getContext());
        rcv = view.findViewById(R.id.cartFragment_rcv);
        btnbook = view.findViewById(R.id.cartFragment_book);
        txtprice = view.findViewById(R.id.cartFramgent_price);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(RecyclerView.VERTICAL);
        rcv.setLayoutManager(l);
        mainActivity = (MainActivity) getActivity();
        setData();
        return view;
    }
    private void setData(){
        ArrayList<Cart> arrprice= new ArrayList<>();
        cartDAO.readinner(mainActivity.getUser().getUsername(), new CartDAO.CartITF() {
            @Override
            public void xuli(Object obj) {
                arr = (ArrayList<Cart>) obj;
                cartAdapter = new CartAdapter(getContext(), arr, new CartAdapter.CartITF() {
                    @Override
                    public void xuli(Object obj) {
                        //sub
                        Cart cart = (Cart) obj;
                        for (int i = 0; i < arrprice.size(); i++) {
                            if (arrprice.get(i).getIdService() == cart.getIdService()) {
                                arrprice.get(i).setQuantity(cart.getQuantity());
                                if (arrprice.get(i).getQuantity() < 1) {
                                    arrprice.remove(i);
                                    break;
                                }
                            }
                        }
                        setPirce(arrprice);
                    }
                }, new CartAdapter.CartITF() {
                    @Override
                    public void xuli(Object obj) {
                        // plus
                        Cart cart = (Cart) obj;
                        for (int i = 0; i < arrprice.size(); i++) {
                            if (arrprice.get(i).getIdService() == cart.getIdService()) {
                                arrprice.get(i).setQuantity(cart.getQuantity());
                            } else {
                                arrprice.add(cart);
                            }
                        }
                        setPirce(arrprice);
                    }
                }, new CartAdapter.CartITF() {
                    @Override
                    public void xuli(Object obj) {
                        int giatien = (int) obj;
                        txtprice.setText(giatien + " VND");
                    }
                });
                rcv.setAdapter(cartAdapter);
            }
        });
    }
    private void setPirce(ArrayList<Cart> arr){
        int sumprice = 0;
        for (int i = 0; i < arr.size(); i++) {
            sumprice = arr.get(i).getQuantity() * arr.get(i).getPriceService();
        }
        txtprice.setText(sumprice + "VND");
    }
}