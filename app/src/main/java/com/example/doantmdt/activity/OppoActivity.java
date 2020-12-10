package com.example.doantmdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doantmdt.R;
import com.example.doantmdt.adapter.IphoneAdapter;
import com.example.doantmdt.model.sanpham;
import com.example.doantmdt.ultil.CheckConnection;
import com.example.doantmdt.ultil.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OppoActivity extends AppCompatActivity {

    Toolbar toolbarIP;
    ListView listViewIP;
    IphoneAdapter iphoneAdapter;
    ArrayList<sanpham> mangIP;
    int idIP = 0;
    int page = 1;
    View footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppo);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetIDLoaiSP();
            ActionToolBar();
            GetData(page);
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra lại kết nối!");
            finish();
        }

    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = Sever.urlIP+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID = 0;
                String TenSP = "";
                Integer GiaSP = 0;
                String HinhSP = "";
                String MoTaSP = "";
                int IDLoaiSP = 0;
                String ManHinhSP = "";
                String HDH = "";
                Integer BoNho = 0;
                String CamTrc = "";
                String CamSau = "";
                String CPU = "";
                Integer RAM = 0;
                Integer Pin = 0;
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenSP = jsonObject.getString("ten");
                            GiaSP = jsonObject.getInt("gia");
                            HinhSP = jsonObject.getString("hinh");
                            MoTaSP = jsonObject.getString("mota");
                            IDLoaiSP = jsonObject.getInt("idloai");
                            ManHinhSP = jsonObject.getString("manhinh");
                            HDH = jsonObject.getString("hdh");
                            BoNho = jsonObject.getInt("bonho");
                            CamTrc = jsonObject.getString("camtruoc");
                            CamSau = jsonObject.getString("camsau");
                            CPU = jsonObject.getString("cpu");
                            RAM = jsonObject.getInt("ram");
                            Pin = jsonObject.getInt("pin");

                            mangIP.add(new sanpham(ID,TenSP,GiaSP,HinhSP,MoTaSP,IDLoaiSP,ManHinhSP,HDH,BoNho,CamTrc,CamSau,CPU,RAM,Pin));
                            iphoneAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(idIP));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarIP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarIP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIDLoaiSP() {
        idIP = getIntent().getIntExtra("idLoaiSP",-1);
        Log.d("giatriloaisanpham",idIP+"");
    }

    private void AnhXa() {
        toolbarIP = findViewById(R.id.toolbarIphone);
        listViewIP = findViewById(R.id.listViewIphone);
        mangIP = new ArrayList<>();
        iphoneAdapter = new IphoneAdapter(getApplicationContext(),mangIP);
        listViewIP.setAdapter(iphoneAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar,null);
    }
}