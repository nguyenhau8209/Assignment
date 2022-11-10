package com.example.assgimentmob2041.Demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assgimentmob2041.DAO.ThanhVienDAO;
import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    private SQLiteDatabase sqLiteDatabase;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;

    public Demo01(Context context) {
        SQLitee sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
    }

    public void thanhVien() {
//        List<ThanhVien> list = new ArrayList<>();
        ThanhVien thanhVien = new ThanhVien(1, "Hello", "2000");
//        if (thanhVienDAO.insert(thanhVien) > 0) {
//            Log.i("ssss", "Them Thanh COng");
//        } else {
//            Log.i("ssss", "Them Fail");
//        }
//        Log.i("ssss", "=============");
        Log.i("ssss", "Tong so thanh vien" + thanhVienDAO.getAll().size());
        thanhVien = new ThanhVien(1, "Xin Chào Các con vợ", "2000");
        thanhVienDAO.update(thanhVien);
        thanhVienDAO.delete("1");
        Log.i("ssss", "Sau khi xóa");
        Log.i("ssss", "Tong so thanh vien" + thanhVienDAO.getAll().size());
    }

    public void thuThu() {
        ThuThu thuThu = new ThuThu("TT02", "Thư", "123");
//        if (thuThuDAO.insert(thuThu) > 0) {
//            Log.i("thuThu", "Them Thanh COng");
//        } else {
//            Log.i("thuThu", "Them Fail");
//        }
//        Log.i("thuThu", "Tong so thanh vien" + thuThuDAO.getAll().size());
//        thuThuDAO.delete("TT01");
//        Log.i("thuThu", "Tong so thanh vien" + thuThuDAO.getAll().size());
        if(thuThuDAO.checkLogin("TT2","123") > 0){
            Log.i("thuThu", "Them Thanh COng");
        }else{
            Log.i("thuThu", "Them Fail");
        }
    }

}
