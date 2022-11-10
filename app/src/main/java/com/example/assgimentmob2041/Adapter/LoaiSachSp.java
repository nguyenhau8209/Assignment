package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;

public class LoaiSachSp extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView tvMaLoai, tvTenLoai;

    public LoaiSachSp(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spiner, null);
        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = view.findViewById(R.id.maLoaiSp);
            tvMaLoai.setText(String.valueOf(item.getMaLoai()));
            //
            tvTenLoai = view.findViewById(R.id.tenLoaiSp);
            tvTenLoai.setText(item.getTenLoai());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spiner, null);
        }
        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = view.findViewById(R.id.maLoaiSp);
            tvMaLoai.setText(String.valueOf(item.getMaLoai()));
            //
            tvTenLoai = view.findViewById(R.id.tenLoaiSp);
            tvTenLoai.setText(item.getTenLoai());
        }
        return view;
    }
}
