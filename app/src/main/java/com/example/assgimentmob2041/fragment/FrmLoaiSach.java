package com.example.assgimentmob2041.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assgimentmob2041.Adapter.LoaiSachAdapter;
import com.example.assgimentmob2041.DAO.LoaiSachDAO;
import com.example.assgimentmob2041.Modole.ItemClick;
import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;
import java.util.List;


public class FrmLoaiSach extends Fragment {

    RecyclerView recyclerView;
    LoaiSachDAO dao;
    EditText edThemLoaiSach;
    int maLoai;

    public FrmLoaiSach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_loai_sach, container, false);

        recyclerView = view.findViewById(R.id.lvLoaiSach);
        edThemLoaiSach = view.findViewById(R.id.edThemLoaiSach);
        Button btnAddLoaiSach = view.findViewById(R.id.btnAddLoaiSach);
        Button btnSua = view.findViewById(R.id.btnUpSachLoai);
        dao = new LoaiSachDAO(getContext());
        LoadData();
        btnAddLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edThemLoaiSach.getText().toString();

                if (dao.insert(ten)) {
                    //Thông báo + loadlis
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    //
                    LoadData();
                } else {
                    Toast.makeText(getContext(), "Thêm Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edThemLoaiSach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maLoai, ten);
                if(dao.update(loaiSach)){
                    LoadData();
                    edThemLoaiSach.setText("");
                    Toast.makeText(getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void LoadData() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        ArrayList<LoaiSach> list = dao.getAll();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSach loaiSach) {
                edThemLoaiSach.setText(loaiSach.getTenLoai());
                maLoai = loaiSach.getMaLoai();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}