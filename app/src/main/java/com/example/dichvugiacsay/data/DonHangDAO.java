package com.example.dichvugiacsay.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dichvugiacsay.Model.DonHang;
import com.example.dichvugiacsay.Model.DonHangOuter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonHangDAO {
    private Context context;
    private String DonhangInnerURL = IP.IP + "/giatsay/getOrderInner.php";
    private String readOutterURL = IP.IP + "/giatsay/getDonHangOuter.php";
    private String hoanthanhOutterURL = IP.IP + "/giatsay/getDonHangHoanThanh.php";


    private String dangGiaoURL = IP.IP + "/giatsay/getDonHangDangGiao.php";
    private String InsertURL = IP.IP + "/giatsay/DonHanginsert.php";
    private String InsertCTDHURL = IP.IP + "/giatsay/chitietdonhanginsert.php";
    public interface DonHangITF{void xuli(Object object);}

    public DonHangDAO(Context context) {
        this.context = context;
    }
    public void getDonHang(String iddonhang, DonHangITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DonhangInnerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DonHang> arrayList = new ArrayList<>();
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new DonHang(
                                jsonObject.getInt("quantity"),
                                jsonObject.getInt("price"),
                                jsonObject.getString("img"),
                                jsonObject.getString("name"),
                                jsonObject.getString("description"),
                                jsonObject.getString("address")
                        ));
                    }
                    xuli.xuli(arrayList);
                }catch (Exception e){
                    Log.e("error xacnhan " , e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error xacnhan " , error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iddonhang", iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getOutter(String username , CartDAO.CartITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, readOutterURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DonHangOuter> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new DonHangOuter(
                                jsonObject.getString("id"),
                                jsonObject.getString("date"),
                                jsonObject.getString("ship")
                        ));
                    }
                    xuli.xuli(arrayList);
                    Log.e("atuan", arrayList.toString());
                } catch (Exception e) {
                    Log.e("atuan err don hang 99", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("atuan err don hang 105", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getOutter(String username , CartDAO.CartITF xuli, int stt){
        if (stt == 2){
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, dangGiaoURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ArrayList<DonHangOuter> arrayList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            arrayList.add(new DonHangOuter(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("date"),
                                    jsonObject.getString("ship")
                            ));
                        }
                        xuli.xuli(arrayList);
                        Log.e("atuan", arrayList.toString());
                    } catch (Exception e) {
                        Log.e("atuan err don hang 141", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("atuan err don hang 147", error.getMessage());
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", username);
                    return map;
                }
            };
            requestQueue.add(stringRequest);
        }

    }
}
