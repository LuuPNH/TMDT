package com.example.doantmdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doantmdt.R;
import com.example.doantmdt.adapter.LoaispAdapter;
import com.example.doantmdt.adapter.SanPhamAdapter;
import com.example.doantmdt.model.Loaisp;
import com.example.doantmdt.model.sanpham;
import com.example.doantmdt.ultil.CheckConnection;
import com.example.doantmdt.ultil.Sever;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtloi;
    Toolbar toolbarHome;
    ViewFlipper viewFlipperHome;
    RecyclerView recyclerViewHome;
    NavigationView navigationViewHome;
    ListView listViewHome;
    DrawerLayout drawerLayoutHome;
    ArrayList<Loaisp> mangLoaiSP;
    LoaispAdapter loaispAdapter;
    int id = 0;
    String tenLoaiSP = "";
    String hinhAnhLoaiSP = "";
    ArrayList<sanpham> mangsanpham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            AcTionViewFlipper();
            GetDuLieuLoaiSP();
            GetDuLieuSanPhamMoiNhat();
            CatchOnItemListView();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet của bạn");
        }

    }

    private void CatchOnItemListView() {
        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, IphoneActivity.class);
                            intent.putExtra("idLoaiSP",mangLoaiSP.get(position).getId());
                            startActivity(intent);
                        }  else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SamsungActivity.class);
                            intent.putExtra("idLoaiSP",mangLoaiSP.get(position).getId());
                            startActivity(intent);
                        }  else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, OppoActivity.class);
                            intent.putExtra("idLoaiSP",mangLoaiSP.get(position).getId());
                            startActivity(intent);
                        }  else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, XiaomiActivity.class);
                            intent.putExtra("idLoaiSP",mangLoaiSP.get(position).getId());
                            startActivity(intent);
                        }  else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        }  else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayoutHome.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.urlSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
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
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
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

                            mangsanpham.add(new sanpham(ID,TenSP,GiaSP,HinhSP,MoTaSP,IDLoaiSP,ManHinhSP,HDH,BoNho,CamTrc,CamSau,CPU,RAM,Pin));
                            sanPhamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
                txtloi.setText(error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaiSP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.urlLoaiSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenLoaiSP = jsonObject.getString("tenloaisp");
                            hinhAnhLoaiSP = jsonObject.getString("hinhloaisp");
                            mangLoaiSP.add(new Loaisp(id,tenLoaiSP,hinhAnhLoaiSP));

                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mangLoaiSP.add(5, new Loaisp(0, "Liên hệ", "https://cdn0.iconfinder.com/data/icons/simpline-mix/64/simpline_49-256.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
                txtloi.setText(error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        //txtloi = findViewById(R.id.txtLoi);
        toolbarHome = findViewById(R.id.toolbarHome);
        viewFlipperHome = findViewById(R.id.viewFlipperHome);
        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerviewHome);
        navigationViewHome = findViewById(R.id.navigationHome);
        listViewHome = findViewById(R.id.listviewHome);
        drawerLayoutHome = findViewById(R.id.drawerHome);
        mangLoaiSP = new ArrayList<>();
        mangLoaiSP.add(0 , new Loaisp(0,"Trang chính","https://cdn4.iconfinder.com/data/icons/basic-ui-2-line/32/house-home-main-menu-start-building-256.png"));
        loaispAdapter = new LoaispAdapter(mangLoaiSP, getApplicationContext());
        listViewHome.setAdapter(loaispAdapter);
        mangsanpham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewHome.setAdapter(sanPhamAdapter);
    }
    private void ActionBar() {
        setSupportActionBar(toolbarHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutHome.openDrawer(GravityCompat.START);
            }
        });
    }
    private void AcTionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://icdn.dantri.com.vn/thumb_w/640/2020/09/23/jack-chinh-thuc-tro-thanh-dai-su-cho-san-pham-vivo-v-20-tai-viet-namdocx-1600870939572.png");
        mangquangcao.add("https://cellphones.com.vn/sforum/wp-content/uploads/2018/08/38297852_1101376116682103_6484630722597355520_n-600x600.jpg");
        mangquangcao.add("https://i.ytimg.com/vi/DbtkVmDNXxw/maxresdefault.jpg");
        mangquangcao.add("https://genk.mediacdn.vn/139269124445442048/2020/9/17/anh2cznv-16003273933031177526744.jpg");
        for (int i=0; i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).placeholder(R.drawable.load).error(R.drawable.error).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperHome.addView(imageView);
        }
        viewFlipperHome.setFlipInterval(5000);
        viewFlipperHome.setAutoStart(true);
    }
}