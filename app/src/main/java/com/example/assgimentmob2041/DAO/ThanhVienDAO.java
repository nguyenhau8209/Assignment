package com.example.assgimentmob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assgimentmob2041.Modole.Sach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    SQLiteDatabase sqLiteDatabase;

    public ThanhVienDAO(Context context) {
        SQLitee sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
    }

    public long insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.getHoTen());
        values.put("namSinh", thanhVien.getNamSinh());
        return sqLiteDatabase.insert("ThanhVien", null, values);

    }

    public long update(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.getHoTen());
        values.put("namSinh", thanhVien.getNamSinh());
        return sqLiteDatabase.update("ThanhVien", values, "maTV=?",
                new String[]{String.valueOf(thanhVien.getMaThanhVien())});

    }

    public int delete(String id) {
        return sqLiteDatabase.delete("ThanhVien", "maTV=?", new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql , String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql , selectionArgs);
        while (c.moveToNext()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaThanhVien(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            thanhVien.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            thanhVien.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            Log.i("ssss",thanhVien.toString());
            list.add(thanhVien);
        }
        return list;
    }
    //get all
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }
    //get id
    public ThanhVien  getID(String id){
        String sql = "SELECT * FROM ThanhVien Where maTV=?";
        List<ThanhVien> list = getData(sql , id);
        if(list.size() == 0){
            return new ThanhVien();
        }else{
            return list.get(0);
        }
    }

}
