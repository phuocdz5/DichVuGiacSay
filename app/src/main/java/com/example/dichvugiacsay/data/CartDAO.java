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
import com.example.dichvugiacsay.Model.Cart;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    private Context context;
    private String updateURL = IP.IP + "";
    private String deleteURL = IP.IP + "";
    private String createURL = IP.IP + "";
    private String readURL = IP.IP + "/giatsay/cartRead.php";

    public CartDAO(Context context) {
        this.context = context;
    }
    public interface CartITF{void xuli(Object obj);}
    public void readinner(String username, CartITF xuli ){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, readURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Cart> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new Cart(
                                jsonObject.getInt("idCart"),
                                jsonObject.getInt("idService"),
                                jsonObject.getInt("quantity"),
                                jsonObject.getInt("price"),
                                jsonObject.getString("name"),
                                jsonObject.getString("description"),
                                jsonObject.getString("img")
                        ));
                    }
                    xuli.xuli(arrayList);
                    Log.e("atuan", arrayList.toString());
                } catch (Exception e) {
                    Log.e("atuan err cart 58", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("atuan err cart 64", error.getMessage());
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

    public void updateGH(){

    }
    public void deleteGH(){

    }
    public void createGH(){

    }
}
