package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;

public class ThanhVienSPAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;

    public ThanhVienSPAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sp_thanhvien_item, null);

        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = view.findViewById(R.id.tvMaSPThanhVien);
            tvMaTV.setText(item.getMaThanhVien() + ". ");
            tvTenTV = view.findViewById(R.id.tvTenSPThanhVien);
            tvTenTV.setText(item.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sp_thanhvien_item, null);

        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = view.findViewById(R.id.tvMaSPThanhVien);
            tvMaTV.setText(item.getMaThanhVien() + ". ");
            tvTenTV = view.findViewById(R.id.tvTenSPThanhVien);
            tvTenTV.setText(item.getHoTen());
        }
        return view;
    }
}
