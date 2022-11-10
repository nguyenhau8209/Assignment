package com.example.assgimentmob2041.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assgimentmob2041.Adapter.LoaiSachSp;
import com.example.assgimentmob2041.Adapter.SachAdapter;
import com.example.assgimentmob2041.DAO.LoaiSachDAO;
import com.example.assgimentmob2041.DAO.SachDAO;
import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class FrmSach extends Fragment {

    ListView listView;
    FloatingActionButton btnAddSach;
    SachDAO sachDAO;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;
    //

    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnLuu, btnCancle;
    //
    LoaiSachSp loaiSachSp;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    public FrmSach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_sach, container, false);
        listView = view.findViewById(R.id.lvSach);
        btnAddSach = view.findViewById(R.id.btnAddSach);
        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        sachDAO = new SachDAO(getContext());
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });


        upLv();
        return view;

    }

    void upLv() {
        list = (ArrayList<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        listView.setAdapter(adapter);

    }

    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không ? ");
        builder.setCancelable(true);
        //
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sachDAO.delete(id);
                upLv();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_sach);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGia);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnLuu = dialog.findViewById(R.id.btnLuuSach);
        btnCancle = dialog.findViewById(R.id.btnHuySach);
        //
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        //
        loaiSachSp = new LoaiSachSp(context, listLoaiSach);
        spinner.setAdapter(loaiSachSp);
        //lấy mã loại
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiSach = listLoaiSach.get(i).getMaLoai();
                Toast.makeText(context, "Chọn" + listLoaiSach.get(i).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaSach.setEnabled(true);
        if (type != 0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "Sach " + position);
            spinner.setSelection(position);


        }
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiSach);
                if (validate() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(context, "Up Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Up fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                    upLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Dữ liệu trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}