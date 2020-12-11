package com.example.doantmdt.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doantmdt.R;
import com.example.doantmdt.ultil.CheckConnection;
import com.example.doantmdt.ultil.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKHActivity extends AppCompatActivity {
    EditText edtTenKH,edtSDTKH,edtEmailKH;
    Button btnXacNhan, btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_k_h);
        AnhXa();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối ");
        }
    }

    private void EventButton() {
            btnXacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                final     String ten = edtTenKH.getText().toString().trim();
                final     String sdt = edtSDTKH.getText().toString().trim();
                final     String email = edtEmailKH.getText().toString().trim();
                    if(ten.length() > 0 && sdt.length() > 0 && email.length() > 0){
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.urlInfoKH, new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String madonhang) {
                                Log.d("madonhang",madonhang);
                                if (Integer.parseInt(madonhang) > 0 ){
                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest request = new StringRequest(Request.Method.POST, Sever.urlChiTietDonHang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if(response.equals("1")){
                                                MainActivity.mangGioHang.clear();
                                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                startActivity(intent);

                                            } else {
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for(int i =0; i<MainActivity.mangGioHang.size();i++){
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("madonhang", madonhang);
                                                    jsonObject.put("masanpham", MainActivity.mangGioHang.get(i).getId());
                                                    jsonObject.put("tensanpham", MainActivity.mangGioHang.get(i).getTen());
                                                    jsonObject.put("giasanpham", MainActivity.mangGioHang.get(i).getGia());
                                                    jsonObject.put("soluongsanpham", MainActivity.mangGioHang.get(i).getSl());

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                jsonArray.put(jsonObject);
                                            }
                                            HashMap<String,String> hashMap = new HashMap<String,String>();
                                            hashMap.put("json", jsonArray.toString());
                                            return hashMap;
                                        }
                                    };
                                    queue.add(request);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap = new HashMap<String,String>();
                                hashMap.put("tenkhachang", ten);
                                hashMap.put("sdt", sdt);
                                hashMap.put("email", email);
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinKHActivity.this);
                        builder.setTitle("Xác nhận thông tin khách hàng");
                        builder.setMessage("Bạn hãy kiểm tra lại thông tin đã nhập!");
                        builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    }

                }
            });
    }

    private void AnhXa() {
        edtTenKH = findViewById(R.id.edtTenKH);
        edtSDTKH = findViewById(R.id.edtSDTKH);
        edtEmailKH = findViewById(R.id.edtEmailKH);
        btnXacNhan = findViewById(R.id.btnXacNhanInfoKH);
        btnTroVe = findViewById(R.id.btnTroVeInfoKH);
    }
}