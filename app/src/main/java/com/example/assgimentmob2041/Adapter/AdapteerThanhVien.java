package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.R;
import com.example.assgimentmob2041.fragment.FrgThanhVien;

import java.util.ArrayList;


public class AdapteerThanhVien extends ArrayAdapter<ThanhVien> {
    private Context context;
    FrgThanhVien frgThanhVien;
    private ArrayList<ThanhVien> list;
    //
    TextView tvMATV , tvTenTV , tvNamSinh;
    ImageView imgDel;

    public  void setData(ArrayList<ThanhVien> listTv){
        this.list = listTv;
        notifyDataSetChanged();
    }
    public AdapteerThanhVien( Context context, FrgThanhVien frgThanhVien, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.frgThanhVien = frgThanhVien;
        this.list = list;
    }
    //

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item , null);
        }
        final ThanhVien item = list.get(position);
        if(item != null){
            tvMATV = v.findViewById(R.id.tvMa);
            tvMATV.setText("Mã Thành Viên: " + item.getMaThanhVien());

            tvTenTV = v.findViewById(R.id.tvTen);
            tvTenTV.setText("Tên Thành Viên: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm Sinh: " + item.getNamSinh());
            //
            imgDel = v.findViewById(R.id.imgDel);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call p
                frgThanhVien.xoa(String.valueOf(item.getMaThanhVien()));
            }
        });

        return v;
        //
    }

}
