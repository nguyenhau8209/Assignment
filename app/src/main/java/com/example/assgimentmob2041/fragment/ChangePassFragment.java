package com.example.assgimentmob2041.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.R;
import com.google.android.material.textfield.TextInputEditText;


public class ChangePassFragment extends Fragment {

    TextInputEditText edPassOld, edPassTiep, edNewPass;
    Button btnSave, btnCance;
    ThuThuDAO thuThuDAO;

    public ChangePassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        thuThuDAO = new ThuThuDAO(getActivity());
        edPassOld = view.findViewById(R.id.edPassOld);
        edPassTiep = view.findViewById(R.id.edPassTiep);
        edNewPass = view.findViewById(R.id.edPassNew);
        btnSave = view.findViewById(R.id.btnSave);
        btnCance = view.findViewById(R.id.btnCancelPass);
        btnCance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edNewPass.setText("");
                edPassOld.setText("");
                edPassTiep.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME", "");
                if (validate() > 0) {
                    ThuThu thuThu;
                    thuThu = thuThuDAO.getID(user);
                    thuThu.setMatKhau(edNewPass.getText().toString());
                    thuThuDAO.update(thuThu);
                    if (thuThuDAO.update(thuThu)) {
                        Toast.makeText(getContext(), "Thay Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
                        edNewPass.setText("");
                        edPassOld.setText("");
                        edPassTiep.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Không thay đổi được mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length() == 0 || edNewPass.getText().length() == 0 || edPassTiep.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("PASSWORD", "");
            String pass = edNewPass.getText().toString();
            String rePass = edPassTiep.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật Khẩu Cũ Sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật Khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }


        return check;
    }
}