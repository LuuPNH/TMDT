package com.example.doantmdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doantmdt.R;
import com.example.doantmdt.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListLoaiSP;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListLoaiSP, Context context) {
        this.arrayListLoaiSP = arrayListLoaiSP;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSP.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaiSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView textViewTenLoaiSP;
        ImageView imgViewTenLoaiSP;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.textViewTenLoaiSP = convertView.findViewById(R.id.textViewLoaiSP);
            viewHolder.imgViewTenLoaiSP = convertView.findViewById(R.id.imgViewLoaiSP);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            }
        Loaisp loaisp = (Loaisp) getItem(position);
        viewHolder.textViewTenLoaiSP.setText(loaisp.getTenLoaiSP());
        Picasso.get().load(loaisp.getHinhAnhLoaiSP())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imgViewTenLoaiSP);
        return convertView;
    }
}
