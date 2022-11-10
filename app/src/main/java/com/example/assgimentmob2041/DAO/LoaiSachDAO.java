package com.example.assgimentmob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    SQLiteDatabase sqLiteDatabase;
    SQLitee sqLitee;
    public LoaiSachDAO(Context context) {
        sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getReadableDatabase();
    }

    public boolean insert(String tenLoai) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.insert("LoaiSach",null ,values);
        if(check == -1){
            return false;
        }return true;

    }

    public boolean update(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());
        long check = sqLiteDatabase.update("LoaiSach",values ,
                "maLoai = ?" , new String[]{String.valueOf(loaiSach.getMaLoai())});
        if(check == -1){
            return false;
        }return true;

    }

//    public int delete(String id) {
//        return sqLiteDatabase.delete("LoaiSach", "maLoai=?", new String[]{id});
//    }
    //Xóa loại sách
    //1 xóa thành công ; 0 xóa thất bại ; -1  k được phép xóa
    public int delete(int id){
        sqLiteDatabase = sqLitee.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maLoai = ?" ,
                new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LoaiSach" , "maLoai = ?"
                ,new String[]{String.valueOf(id)});
        if(check == -1){
            return 0;
        }return 1;
    }


    @SuppressLint("Range")
    public ArrayList<LoaiSach> getData(String sql, String... selectionArgs) {
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            loaiSach.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(loaiSach);
        }
        return list;
    }
    public ArrayList<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    //get id
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach Where maLoai=?";
        List<LoaiSach> list = getData(sql , id);
        if(list.size() == 0){
            return new LoaiSach();
        }else{
            return list.get(0);
        }
    }
}
