package com.example.doantmdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView listViewGioHang;
    TextView txtViewGioHangRong,txtViewTongTien;
    Button btnThanhToan,btnTiepTucMuaHang;
    Toolbar toolbarGioHang;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        ActionToolBar();
        CheckData();
        EventUltil();
    }

    private void EventUltil() {
        long tongtien = 0;
        for(int i = 0; i<MainActivity.mangGioHang.size();i++){
            tongtien+= MainActivity.mangGioHang.get(i).getGia();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtViewTongTien.setText(decimalFormat.format(tongtien)+ " Đ");
        }
    }

    private void CheckData() {
        if(MainActivity.mangGioHang.size() <= 0){
            gioHangAdapter.notifyDataSetChanged();
            txtViewGioHangRong.setVisibility(View.VISIBLE);
            listViewGioHang.setVisibility(View.INVISIBLE);
        } else {
            gioHangAdapter.notifyDataSetChanged();
            txtViewGioHangRong.setVisibility(View.INVISIBLE);
            listViewGioHang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        listViewGioHang = findViewById(R.id.listViewGioHang);
        txtViewGioHangRong = findViewById(R.id.textViewGioHangRong);
        txtViewTongTien = findViewById(R.id.textViewTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToanGioHang);
        btnTiepTucMuaHang = findViewById(R.id.btnTiepTucMuaHang);
        toolbarGioHang = findViewById(R.id.toolbarGioHang);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, MainActivity.mangGioHang);
        listViewGioHang.setAdapter(gioHangAdapter);
    }
}