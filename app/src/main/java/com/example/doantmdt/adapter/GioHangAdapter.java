package com.example.doantmdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.activity.GioHangActivity;
import com.example.doantmdt.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> mangGioHang;

    public GioHangAdapter(Context context, ArrayList<Giohang> mangGioHang) {
        this.context = context;
        this.mangGioHang = mangGioHang;
    }

    @Override
    public int getCount() {
        return mangGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return mangGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
            public TextView txtTenGH, txtGiaGH;
            public ImageView imgHinhGioHang;
            public Button btnGiamSPGH,btnTangSPGH,btnValueGH;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_gio_hang,null);
            viewHolder.txtTenGH = convertView.findViewById(R.id.textViewTenSPGioHang);
            viewHolder.txtGiaGH = convertView.findViewById(R.id.textViewGiaSPGioHang);
            viewHolder.imgHinhGioHang = convertView.findViewById(R.id.imgGioHang);
            viewHolder.btnGiamSPGH = convertView.findViewById(R.id.btnGiamSLGioHang);
            viewHolder.btnValueGH = convertView.findViewById(R.id.btnValueSLGioHang);
            viewHolder.btnTangSPGH = convertView.findViewById(R.id.btnTangSLGioHang);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.txtTenGH.setText(giohang.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGH.setText("Giá: "+decimalFormat.format(giohang.getGia())+ "Đ");
        Picasso.get().load(giohang.getHinh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imgHinhGioHang);
        viewHolder.btnValueGH.setText(giohang.getSl() + "");
        return convertView;
    }
}
