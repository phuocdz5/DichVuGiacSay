package com.example.dichvugiacsay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dichvugiacsay.Adapter.AddressAdapter;
import com.example.dichvugiacsay.Model.Address;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.data.AddressDAO;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {
    private Intent intent;
    private LinearLayout address_add;
    private RecyclerView rcv;
    private AddressDAO addressDAO;
    private User user;
    private AddressAdapter addressAdapter;
    private Address address;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(RecyclerView.VERTICAL);
        rcv = findViewById(R.id.address_rcv);
        rcv.setLayoutManager(l);
        back = findViewById(R.id.address_back);
        address_add = findViewById(R.id.address_add);
        addressDAO = new AddressDAO(this);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setData();
        address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialogADD();
            }
        });
    }

    private void setData(){
        addressDAO.getAll(user.getUsername(), new AddressDAO.AddressITF() {
            @Override
            public void xuli(Object obj) {
                addressAdapter = new AddressAdapter((ArrayList<Address>) obj, AddressActivity.this, new AddressAdapter.AddressAdapterITF() {
                    @Override
                    public void xuli(Object obj) {
                        address = (Address) obj;
                        Intent i = new Intent(AddressActivity.this, ThanhToanActivity.class);
                        i.putExtra("user", user);
                        i.putExtra("address", address);
                        i.putExtra("arrItem", intent.getSerializableExtra("arrItem"));
                        startActivity(i);
                        finish();
                    }
                }, new AddressAdapter.AddressAdapterITF() {
                    @Override
                    public void xuli(Object obj) {
                        setDialogUpdate((Address) obj);
                    }
                });
                rcv.setAdapter(addressAdapter);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddressActivity.this, ThanhToanActivity.class);
                i.putExtra("user", user);
                i.putExtra("address", address);
                i.putExtra("arrItem", intent.getSerializableExtra("arrItem"));
                startActivity(i);
                finish();
            }
        });
    }
    private void setDialogUpdate(Address address1){
        Dialog dialog = new Dialog(AddressActivity.this);
        dialog.setContentView(R.layout.activity_update_address);
        EditText edtname = dialog.findViewById(R.id.updateaddress_name);
        EditText edtphone = dialog.findViewById(R.id.updateaddress_phone);
        EditText edtaddress = dialog.findViewById(R.id.updateaddress_address);
        TextView delete = dialog.findViewById(R.id.updateaddress_delete);
        TextView update = dialog.findViewById(R.id.updateaddress_update);
        edtname.setText(address1.getName());
        edtphone.setText(address1.getPhone());
        edtaddress.setText(address1.getAddress());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressDAO.delete(address1, new AddressDAO.AddressITF() {
                    @Override
                    public void xuli(Object obj) {
                        setData();
                        dialog.dismiss();
                    }
                });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressDAO.update(new Address(
                        address1.getId(),
                        edtname.getText().toString().trim(),
                        edtphone.getText().toString().trim(),
                        edtaddress.getText().toString().trim()
                ), new AddressDAO.AddressITF() {
                    @Override
                    public void xuli(Object obj) {
                        setData();
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }

    private void setDialogADD(){
        Dialog dialog = new Dialog(AddressActivity.this);
        dialog.setContentView(R.layout.address_dialog_add);
        EditText edtname = dialog.findViewById(R.id.addaddress_name);
        EditText edtphone = dialog.findViewById(R.id.addaddress_phone);
        EditText edtaddress = dialog.findViewById(R.id.addaddress_address);
        TextView add = dialog.findViewById(R.id.addaddress_update);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namestr = edtname.getText().toString().trim();
                String phonestr = edtphone.getText().toString().trim();
                String addressstr = edtaddress.getText().toString().trim();
                Log.e("string address", namestr + phonestr+ addressstr);
                if (namestr.equals("") || phonestr.equals("") || addressstr.equals("")){
                    Toast.makeText(AddressActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    addressDAO.add(new Address(
                            user.getId(),
                            namestr,
                            phonestr,
                            addressstr
                    ), user, new AddressDAO.AddressITF() {
                        @Override
                        public void xuli(Object obj) {
                            setData();
                            dialog.dismiss();
                        }
                    });
                    Toast.makeText(AddressActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}