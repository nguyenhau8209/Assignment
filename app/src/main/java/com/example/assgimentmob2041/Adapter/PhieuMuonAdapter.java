package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.DAO.SachDAO;
import com.example.assgimentmob2041.DAO.ThanhVienDAO;
import com.example.assgimentmob2041.Modole.PhieuMuon;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.R;
import com.example.assgimentmob2041.fragment.QLPhieuMuonFra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Context context;
    QLPhieuMuonFra qlPhieuMuonFra;
    private ArrayList<PhieuMuon> list;
    TextView tvmaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach, tvGioThue;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df = new SimpleDateFormat("h:mm a");

    public PhieuMuonAdapter(@NonNull Context context, QLPhieuMuonFra qlPhieuMuonFra, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.qlPhieuMuonFra = qlPhieuMuonFra;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.phieu_muon_item, null);

        }
        final PhieuMuon item = list.get(position);
        if (item != null) {
            tvmaPM = view.findViewById(R.id.tvMaPM);
            tvmaPM.setText("Mã phiếu Mượn: " + item.getMaPhieuMuon());
            if(item.getTienThue()<= 50000) {
                tvmaPM.setTextColor(Color.BLUE);
            }else{
                tvmaPM.setTextColor(Color.RED);
            }
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: " + sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaThanhVien()));
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành Viên:" + thanhVien.getHoTen());
            //
            Log.e("zzzz", "Thành Viên: " + thanhVien.getHoTen() + " " + thanhVien.getMaThanhVien());
            //
            tvTienThue = view.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền Thuê: " + sach.getGiaThue() + " ");
            Log.e("zzzz", "Tiền Thuê" + item.getTienThue());
            tvGioThue = view.findViewById(R.id.tvGioThue);
            tvGioThue.setText("Giờ Thuê: "+ df.format(item.getGio()));
            tvNgay = view.findViewById(R.id.tvNgayMuon);
            tvNgay.setText("Ngày Thuê: " + sdf.format(item.getNgay()));
            tvTraSach = view.findViewById(R.id.tvTraSach);
            if (item.getTraSach() == 1) {
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("   Đã Trả sách");
            } else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("   Chưa Trả sách");
            }
            imgDel = view.findViewById(R.id.btnDeletePM);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlPhieuMuonFra.xoa(item.getMaPhieuMuon() + "");
            }
        });
        return view;
    }
}
