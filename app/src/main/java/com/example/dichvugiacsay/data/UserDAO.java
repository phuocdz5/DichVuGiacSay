package com.example.dichvugiacsay.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import android.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dichvugiacsay.Fragment.Fragment_ChangePassword;
import com.example.dichvugiacsay.Login;
import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.itf.RememberUS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private String checkLoginURL = IP.IP+"/giatsay/checklogin.php";
    private String regAccount = IP.IP + "/giatsay/regAccount.php";
    private String changPasswordURL = IP.IP + "/giatsay/changePassword.php";

    private String changInfoURL = IP.IP + "/giatsay/userChangInfo.php";
    private String forgotPass = IP.IP + "/giatsay/forgot.php";

    private Context context;
    public UserDAO(Context context) {
        this.context = context;
    }
    // check login
    public void checkLogin(String us, String pw, RememberUS rememberUS){
        if (!us.isEmpty() && !pw.isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, checkLoginURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String result = jsonObject.getString("status");

                        if (result.equals("success")) {
                            JSONArray userArray = jsonObject.getJSONArray("users");
                            JSONObject userObject = userArray.getJSONObject(0);
                            User user = new User(
                                    userObject.getString("id"),
                                    userObject.getString("name"),
                                    userObject.getString("address"),
                                    userObject.getString("email"),
                                    userObject.getString("phone"),
                                    us);
                            rememberUS.remember();
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("user", user);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context, "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,  "Some thing wrong"+error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", us);
                    map.put("password", pw);
                    return map;
                }
            };
            requestQueue.add(stringRequest);

        }

    }

    public boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    public boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra xem chuỗi chỉ chứa đúng 10 ký tự số và bắt đầu bằng số 0
        return phoneNumber.matches("^0[0-9]{9}$");
    }

    // reg account
    public void setRegAccount(String name, String email, String phone, String us, String pw){
        if (validateForm(name) && validateForm(email) && validateForm(phone) &&validateForm(us) && validateForm(pw)){
            Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            Toast.makeText(context, "Sai định dạng email!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra định dạng số điện thoại
        if (!isValidPhoneNumber(phone)) {
            Toast.makeText(context, "Số điện thoại phải có đúng 10 số và bắt đầu bằng số 0", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, regAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String check = jsonObject.getString("status");
                    if(check.equals("success")){
                        context.startActivity(new Intent(context ,  Login.class));
                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Username hoặc Email đã tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Some thing wrong", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("username" ,us);
                map.put("password", pw);
                map.put("name" ,name);
                map.put("email", email);
                map.put("phone" ,phone);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
    public void changePassword(String us, String oldpw, String newpw){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changPasswordURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")){
                    Toast.makeText(context, "Mật khẩu đã được thay đổi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Bạn nhập sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something wrong"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", us);
                map.put("oldpassword", oldpw);
                map.put("newpassword", newpw);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-HTTP-Method-Override", "PATCH");
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void forgotPassword(String email,AlertDialog alertDialog,ProgressDialog progressDialog){

        if(!validateForm(email)){
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, forgotPass, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String mail = jsonObject.getString("mail");
                        if(mail.equals("send")){
                            Toast.makeText(context, "Vui lòng kiểm tra gmail của bạn", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            progressDialog.dismiss();
                        }if(mail.equals("notmail")){
                            Toast.makeText(context, "Email chưa được đăng ký", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> forgotMail = new HashMap<>();
                    forgotMail.put("email",email);
                    return forgotMail;
                }
            };
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }

    private boolean validateForm(String str){
        return (str.isEmpty() || str.equals("")) ? true : false ;

    }

    public interface UserITF{void xuli(Object obj);}
    public void update(User user, UserITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changInfoURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error userdao 179", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", user.getUsername());
                map.put("name", user.getName());
                map.put("phone", user.getPhone());
                map.put("email", user.getEmail());
                map.put("address", user.getAddress());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
