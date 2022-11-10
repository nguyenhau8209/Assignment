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

import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;

public class SachSPAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMa , tvTen;
    public SachSPAdapter(@NonNull Context context,ArrayList<Sach> list ) {
        super(context, 0 , list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sp_sach_item , null);

        }
        Sach item = list.get(position);
        if(item != null){
            tvMa = view.findViewById(R.id.tvMaSachSP);
            tvMa.setText(item.getMaSach() + ". ");
            tvTen = view.findViewById(R.id.tvTenSachSP);
            tvTen.setText(item.getTenSach());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sp_sach_item , null);

        }
        Sach item = list.get(position);
        if(item != null){
            tvMa = view.findViewById(R.id.tvMaSachSP);
            tvMa.setText(item.getMaSach() + ". ");
            tvTen = view.findViewById(R.id.tvTenSachSP);
            tvTen.setText(item.getTenSach());
        }
        return view;
    }
}
