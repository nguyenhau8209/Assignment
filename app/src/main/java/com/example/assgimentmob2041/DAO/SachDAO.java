package com.example.assgimentmob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    SQLiteDatabase sqLiteDatabase;

    public SachDAO(Context context) {
        SQLitee sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
    }

    public long insert(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        return sqLiteDatabase.insert("Sach", null, values);

    }

    public long update(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        return sqLiteDatabase.update("Sach", values, "maSach=?", new String[]{String.valueOf(sach.getMaSach())});

    }

    public int delete(String id) {
        return sqLiteDatabase.delete("Sach", "maSach=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Sach sach = new Sach();
            sach.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            sach.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            sach.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            sach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(sach);
        }
        return list;
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    //get id
    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach Where maSach=?";
        List<Sach> list = getData(sql, id);
        if(list.size() == 0){
            return new Sach();
        }else{
            return list.get(0);
        }
//        return list.get(0);

    }
}
