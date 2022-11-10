package com.example.assgimentmob2041.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assgimentmob2041.Adapter.AdapteerThanhVien;
import com.example.assgimentmob2041.DAO.ThanhVienDAO;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FrgThanhVien extends Fragment {

    ListView listView;
    FloatingActionButton btnAdd;
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> list;
    AdapteerThanhVien adapter;
    ThanhVien item;
    Dialog dialog;
    EditText edMa, edTen, edNamSinh;
    Button btnThem, btnCancle;
    //

    public FrgThanhVien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_thanh_vien, container, false);
        listView = view.findViewById(R.id.lvThanhVien);
        btnAdd = view.findViewById(R.id.btnAddThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDiaLog(getActivity(), 0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDiaLog(getActivity() , 1);
                return false;
            }
        });
        upListV();
        return view;
    }

    void upListV() {
        list = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        adapter = new AdapteerThanhVien(getActivity(), this, list);
        listView.setAdapter(adapter);
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa hay không");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thanhVienDAO.delete(id);
                upListV();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void openDiaLog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_add);
        edMa = dialog.findViewById(R.id.edMaTV);
        edTen = dialog.findViewById(R.id.edTen);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnThem = dialog.findViewById(R.id.btnThem);
        btnCancle = dialog.findViewById(R.id.btnCancle);
        //kiểm tra type 0 or 1
        edMa.setEnabled(false);
        if (type != 0) {
            edMa.setText(String.valueOf(item.getMaThanhVien()));
            edTen.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThanhVien();
                item.setHoTen(edTen.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if (valilate() > 0) {
                    if (type == 0) {
                        if (thanhVienDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thanh Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Them Fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaThanhVien(Integer.parseInt(edMa.getText().toString()));
                        if (thanhVienDAO.update(item) > 0) {
                            Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                    upListV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int valilate() {
        int check = 1;
        if (edTen.getText().length() == 0 || edNamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Dữ liệu trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}