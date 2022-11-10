package com.example.assgimentmob2041.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assgimentmob2041.Adapter.AdapteerThanhVien;
import com.example.assgimentmob2041.Adapter.PhieuMuonAdapter;
import com.example.assgimentmob2041.Adapter.SachSPAdapter;
import com.example.assgimentmob2041.Adapter.ThanhVienSPAdapter;
import com.example.assgimentmob2041.DAO.PhieuMuonDAO;
import com.example.assgimentmob2041.DAO.SachDAO;
import com.example.assgimentmob2041.DAO.ThanhVienDAO;
import com.example.assgimentmob2041.Modole.PhieuMuon;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class QLPhieuMuonFra extends Fragment {
    ListView lvPhieuMuon;
    FloatingActionButton btnAdd;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    //
    Dialog dialog;
    EditText edMaPM;
    Spinner spSach, spTV;
    TextView tvNgay, tvTienThue, tvGio;
    CheckBox chkTraSach;
    Button btnSave, btnCancle;

    //sp Thành Viên
    ThanhVienSPAdapter thanhVienSPAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    //sp Sách
    SachSPAdapter sachSPAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    //
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df = new SimpleDateFormat("h:mm a");
    SearchView searchview;

    TextInputEditText edtsearch;
    AdapteerThanhVien adaptertV;
    public QLPhieuMuonFra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_phieu_muon, container, false);
        btnAdd = view.findViewById(R.id.btnAddPhieuMuon);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDiaLog(getActivity(), 0);
            }
        });
        lvPhieuMuon = view.findViewById(R.id.lvPhieuMuon);
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDiaLog(getActivity(), 1);
                return false;
            }
        });
        phieuMuonDAO = new PhieuMuonDAO(getContext());
//        edtsearch = view.findViewById(R.id.edt_search);
//        edtsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                QLPhieuMuonFra.this.adapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        searchview = view.findViewById(R.id.search_view);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText(newText);
                return true;
            }
        });
        capNhatLV();
        return view;
    }

    private void searchText(String newText) {
        ArrayList<ThanhVien> listTV = new ArrayList<>();
        for (ThanhVien thanhVien : listThanhVien
             ) {
            if (thanhVien.getHoTen().toLowerCase().toLowerCase().contains(newText.toLowerCase())) {
                listTV.add(thanhVien);
            }

        }
        if(listTV.isEmpty()){
            Toast.makeText(getActivity(), "Nhập mã để tìm kiếm", Toast.LENGTH_SHORT).show();
        }else{
            adaptertV.setData(listTV);
        }
    }

    void capNhatLV() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDiaLog(Context context, int type) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_add);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        edMaPM.setEnabled(false);
        spSach = dialog.findViewById(R.id.spMaSach);
        spTV = dialog.findViewById(R.id.spMaTV);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvGio = dialog.findViewById(R.id.tvGio);
        tvTienThue = dialog.findViewById(R.id.tvTienThue1);
        chkTraSach = dialog.findViewById(R.id.chkTraSach1);
        btnCancle = dialog.findViewById(R.id.btnHuyPM);
        btnSave = dialog.findViewById(R.id.btnLuuPM);
        //set Ngày Thue , mặc định ngày hiện hành
        tvNgay.setText("Ngày Thuê: " + sdf.format(new Date()));
        tvGio.setText("Giờ Thuê: " + df.format(new Date()));
        //sự kiện thành viên sp
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSPAdapter = new ThanhVienSPAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSPAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = listThanhVien.get(i).getMaThanhVien();
                Toast.makeText(context, "Chọn: " + listThanhVien.get(i).getHoTen(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //sự kiện sách sp
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSPAdapter = new SachSPAdapter(context, listSach);
        spSach.setAdapter(sachSPAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listSach.get(i).getMaSach();
                tienThue = listSach.get(i).getGiaThue();
                Toast.makeText(context, "Chọn: " + listSach.get(i).getTenSach(), Toast.LENGTH_SHORT).show();
//                tvTienThue.setText("Tiền Thuê: " + tienThue);
//                Toast.makeText(context, "Tiền Thuê: " + tienThue, Toast.LENGTH_SHORT).show();
                tvTienThue.setText("Tiền Thuê: " + tienThue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //edit set data lên from
        if (type !=0)  {
            edMaPM.setText(item.getMaPhieuMuon() + "");
            for (int i = 0; i < listThanhVien.size(); i++)
                if (item.getMaThanhVien() == (listThanhVien.get(i).getMaThanhVien())) {
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaSach() == (listSach.get(i).getMaSach())) {
                    positionSach = i;
                }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày Thuê: " + sdf.format(item.getNgay()));
            tvGio.setText("Giờ Thuê: " + df.format(item.getGio()) );
            tvTienThue.setText("Tiền Thuê: " + item.getTienThue());
//            Log.e("cccc" , "Tiền Thuê" + item.getTienThue());

            if (item.getTraSach() == 0) {
                chkTraSach.setChecked(false);
            } else {
                chkTraSach.setChecked(true);
            }

        }
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaThanhVien(maThanhVien);
                item.setNgay(new Date());
                item.setGio(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()) {
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }
                if (type == 0) {
                    if (phieuMuonDAO.insert(item) > 0) {
                        Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.setMaPhieuMuon(Integer.parseInt(edMaPM.getText().toString()));
                    if (phieuMuonDAO.update(item) > 0) {
                        Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLV();
                dialog.dismiss();
            }
        });
        //
        dialog.show();
    }
    public void xoa(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa hay không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                phieuMuonDAO.delete(id);
                capNhatLV();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}