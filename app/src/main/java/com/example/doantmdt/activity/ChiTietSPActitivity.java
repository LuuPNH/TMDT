package com.example.doantmdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSPActitivity extends AppCompatActivity {
    Toolbar toolbarChiTiet;
    ImageView imgViewChiTiet;
    TextView txtTenChiTiet,txtGiaChiTiet,txtMoTaChiTiet;
    Spinner spinnerChiTiet;
    Button btnThemGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_s_p_actitivity);
        AnhXa();
        ActionToolBar();
        GetInfo();
        CatchEventSpinner();
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,soluong);
        spinnerChiTiet.setAdapter(arrayAdapter);
    }

    private void GetInfo() {
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
        sanpham sp = (sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        ID = sp.getId();
        TenSP = sp.getTen();
        GiaSP = sp.getGia();
        HinhSP = sp.getHinh();
        MoTaSP = sp.getMota();
        IDLoaiSP = sp.getIdLoai();
        ManHinhSP = sp.getManhinh();
        HDH = sp.getHdh();
        BoNho = sp.getBonho();
        CamTrc = sp.getCamtrc();
        CamSau = sp.getCamsau();
        CPU = sp.getCpu();
        RAM = sp.getRam();
        Pin = sp.getPin();
        txtTenChiTiet.setText(TenSP);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá: "+decimalFormat.format(GiaSP)+ "Đ");
        txtMoTaChiTiet.setText(MoTaSP);
        Picasso.get().load(HinhSP)
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(imgViewChiTiet);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChiTiet = findViewById(R.id.toolbarChiTietSP);
        imgViewChiTiet = findViewById(R.id.imgChiTietSP);
        txtTenChiTiet = findViewById(R.id.textViewTenChiTietSP);
        txtGiaChiTiet = findViewById(R.id.textViewGiaChiTietSP);
        txtMoTaChiTiet = findViewById(R.id.textMoTaChiTietSP);
        spinnerChiTiet = findViewById(R.id.spinerChiTietSP);
        btnThemGioHang = findViewById(R.id.btnThemGioHang);


    }
}