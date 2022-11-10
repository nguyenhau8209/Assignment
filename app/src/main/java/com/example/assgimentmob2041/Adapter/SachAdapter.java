package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.DAO.LoaiSachDAO;
import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.R;
import com.example.assgimentmob2041.fragment.FrmSach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    FrmSach frmSach;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, FrmSach frmSach, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.frmSach = frmSach;
        this.list = list;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sach, null);
        }

        final Sach item = list.get(position);
        if (item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(item.getMaLoai() + "");
//            Log.e("zzzz","Mã Loại: " + item.getMaLoai());
            Log.e("zzzz","Mã Loại: " + loaiSach.getTenLoai() + " " + loaiSach.getMaLoai());
//            LoaiSach loaiSach = (LoaiSach) loaiSachDAO.getAll();
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: " + item.getMaSach());

            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: " + item.getTenSach());
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Gía Thuê: " + item.getGiaThue());
            tvLoai = view.findViewById(R.id.tvLoaiSach);
            tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());

            imgDel = view.findViewById(R.id.btnXoaSach);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frmSach.delete(String.valueOf(item.getMaSach()));

            }
        });


        return view;
    }
}
