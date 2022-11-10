package com.example.assgimentmob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assgimentmob2041.Modole.PhieuMuon;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.Top;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhieuMuonDAO {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat date1 = new SimpleDateFormat("h:mm a");
//    String date1 = df.format(Calendar.getInstance().getTime());

    public PhieuMuonDAO(Context context) {
        this.context = context;
        SQLitee sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
    }

    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaThanhVien());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("giaThue", phieuMuon.getTienThue());
        values.put("gio", date1.format(phieuMuon.getGio()));
        values.put("ngay", date.format(phieuMuon.getNgay()));
        values.put("traSach", phieuMuon.getTraSach());
        return sqLiteDatabase.insert("PhieuMuon", null, values);

    }

    public long update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaThanhVien());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("giaThue", phieuMuon.getTienThue());
        values.put("gio", date1.format(phieuMuon.getGio()));
        values.put("ngay", date.format(phieuMuon.getNgay()));
        values.put("traSach", phieuMuon.getTraSach());
        return sqLiteDatabase.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPhieuMuon())});

    }

    public int delete(String id) {
        return sqLiteDatabase.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPhieuMuon(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            phieuMuon.setMaTT(c.getString(c.getColumnIndex("maTT")));
            phieuMuon.setMaThanhVien(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            phieuMuon.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            phieuMuon.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            try {
                phieuMuon.setGio(date1.parse(c.getString(c.getColumnIndex("gio"))));
                phieuMuon.setNgay(date.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            phieuMuon.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(phieuMuon);
        }
        return list;
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    //get id
    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon Where maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    //thống kê top 10
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "Select maSach ,count(maSach) as soLuong From PhieuMuon Group By maSach Order By soLuong DESC Limit 10";
        List<Top> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = sqLiteDatabase.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            Sach sach = sachDAO.getID(cursor.getString(cursor.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String min, String max) {
        String sqlDoanhThu = "Select Sum(giaThue) as doanhThu from PhieuMuon Where ngay between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{min, max});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }

}
