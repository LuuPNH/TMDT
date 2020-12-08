package com.example.doantmdt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbarHome;
    ViewFlipper viewFlipperHome;
    RecyclerView recyclerViewHome;
    NavigationView navigationViewHome;
    ListView listViewHome;
    DrawerLayout drawerLayoutHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ActionBar();
        AcTionViewFlipper();
    }
    private void AnhXa() {
        toolbarHome = findViewById(R.id.toolbarHome);
        viewFlipperHome = findViewById(R.id.viewFlipperHome);
        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerviewHome);
        navigationViewHome = findViewById(R.id.navigationHome);
        listViewHome = findViewById(R.id.listviewHome);
        drawerLayoutHome = findViewById(R.id.drawerHome);
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
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperHome.addView(imageView);
        }
        viewFlipperHome.setFlipInterval(5000);
        viewFlipperHome.setAutoStart(true);
    }
}