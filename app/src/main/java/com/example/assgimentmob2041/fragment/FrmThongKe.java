package com.example.assgimentmob2041.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assgimentmob2041.DAO.PhieuMuonDAO;
import com.example.assgimentmob2041.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FrmThongKe extends Fragment {

    Button btnTuNgay , btnDenNgay , btnDoanhThu;
    EditText edTuNgay , edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    int mYear , mMonth , mDay;
    public FrmThongKe() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_thong_ke, container, false);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateTuNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateDenNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
                tvDoanhThu.setText("Doanh Thu: "+phieuMuonDAO.getDoanhThu(tuNgay , denNgay));
            }
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edTuNgay.setText(date.format(calendar.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edDenNgay.setText(date.format(calendar.getTime()));

        }
    };
}