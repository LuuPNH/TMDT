package com.example.doantmdt.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.adapter.GioHangAdapter;
import com.example.doantmdt.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView listViewGioHang;
    TextView txtViewGioHangRong;
    static TextView txtViewTongTien;
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
        CatchOnItemListView();

        EventButton();
    }

    private void EventButton() {
        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGioHang.size() > 0 ){
                     Intent intent = new Intent(getApplicationContext(), ThongTinKHActivity.class);
                     startActivity(intent);
                } else {
                    btnThanhToan.setEnabled(false);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Không thể thanh toán khi giỏ hàng của bạn rỗng");
                }
            }
        });
    }

    private void CatchOnItemListView() {
       listViewGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
               builder.setTitle("Xác nhận xóa sản phẩm");
               builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
               builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.mangGioHang.size()<=0){
                            txtViewGioHangRong.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.mangGioHang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.mangGioHang.size()<=0){
                                txtViewGioHangRong.setVisibility(View.VISIBLE);
                            } else {
                                txtViewGioHangRong.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                   }
               });
               builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       gioHangAdapter.notifyDataSetChanged();
                       EventUltil();

                   }
               });
               builder.show();
               return true;
           }
       });
    }

    public static void EventUltil() {
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