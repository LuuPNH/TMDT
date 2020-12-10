package com.example.doantmdt.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class IphoneAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arrayListIphone;

    public IphoneAdapter(Context context, ArrayList<sanpham> arrayListIphone) {
        this.context = context;
        this.arrayListIphone = arrayListIphone;
    }

    @Override
    public int getCount() {
        return arrayListIphone.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListIphone.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
            public TextView txtTenIP,txtGiaIP,txtMoTaIP;
            public ImageView imgViewIP;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.dong_iphone,null);
                viewHolder.txtTenIP = convertView.findViewById(R.id.textViewTenSPIphone);
                viewHolder.txtGiaIP = convertView.findViewById(R.id.textViewGiaSPIphone);
                viewHolder.txtMoTaIP = convertView.findViewById(R.id.textViewMoTaSPIphone);
                viewHolder.imgViewIP = convertView.findViewById(R.id.imgIphone);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            sanpham sp = (sanpham) getItem(position);
            viewHolder.txtTenIP.setText(sp.getTen().toString());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            viewHolder.txtGiaIP.setText("Giá: "+decimalFormat.format(sp.getGia())+ "Đ");
            viewHolder.txtMoTaIP.setMaxLines(2);
            viewHolder.txtMoTaIP.setEllipsize(TextUtils.TruncateAt.END);
            viewHolder.txtMoTaIP.setText(sp.getMota());
            Picasso.get().load(sp.getHinh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imgViewIP);
            return convertView;
    }
}
