package com.example.assgimentmob2041.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.assgimentmob2041.Adapter.AdapterAddUser;
import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.example.assgimentmob2041.Modole.ItemClickThuTHu;
import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;
import java.util.List;


public class FrmAddUser extends Fragment {
    RecyclerView recyclerView;
    EditText edUser , edTen , edPass;
    Button btnThem , btnHuy;
    ThuThuDAO thuThuDAO;

    public FrmAddUser() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_add_user, container, false);
        edUser = view.findViewById(R.id.edUser);
        recyclerView = view.findViewById(R.id.lvAddUser);
        edTen = view.findViewById(R.id.edName);
        edPass = view.findViewById(R.id.edPass);
        btnHuy = view.findViewById(R.id.btnHuyUser);
        btnThem = view.findViewById(R.id.btnThemUser);
        thuThuDAO = new ThuThuDAO(getContext());
        LoadData();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPass.setText("");
                edUser.setText("");
                edTen.setText("");
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edTen.getText().toString();
                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();
                ThuThu thuThu = new ThuThu(ten, user , pass);
                if(thuThuDAO.insert(thuThu)){
                    Toast.makeText(getContext(), "Thêm Thủ thư Thanh Công", Toast.LENGTH_SHORT).show();
                    LoadData();
                }else{
                    Toast.makeText(getContext(), "Thêm Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
   private void LoadData(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        ArrayList<ThuThu> list = thuThuDAO.getAll();
        AdapterAddUser addUser = new AdapterAddUser(getContext(), list, new ItemClickThuTHu() {
            @Override
            public void onClickThu(ThuThu thuThu) {

            }
        });
        recyclerView.setAdapter(addUser);
    }
    public void delete(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Dữ Liệu Tài Khoản Người Dùng");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thuThuDAO.delete(id);
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog =builder.create();
        dialog.show();
    }
}