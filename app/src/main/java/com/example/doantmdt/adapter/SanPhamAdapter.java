package com.example.doantmdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doantmdt.R;
import com.example.doantmdt.model.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {

    Context context;
    ArrayList<sanpham> arraysp;

    public SanPhamAdapter(Context context, ArrayList<sanpham> arraysp) {
        this.context = context;
        this.arraysp = arraysp;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            sanpham sp = arraysp.get(position);
            holder.txttensp.setText(sp.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Giá: "+ decimalFormat.format(sp.getGia())+ " Đ");
        Picasso.get().load(sp.getHinh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(holder.imghinhsp);
    }

    @Override
    public int getItemCount() {
        return arraysp.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsp;
        public TextView txttensp,txtgiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsp = itemView.findViewById(R.id.imgViewSP);
            txttensp = itemView.findViewById(R.id.textViewTenSP);
            txtgiasp = itemView.findViewById(R.id.textViewGiaSP);

        }
    }

}
