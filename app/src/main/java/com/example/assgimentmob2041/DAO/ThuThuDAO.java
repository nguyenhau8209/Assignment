package com.example.assgimentmob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assgimentmob2041.Modole.ThanhVien;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.SQLite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    SQLiteDatabase sqLiteDatabase;
    SQLitee sqLitee;

    //
    public ThuThuDAO(Context context) {
        sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
    }

    public boolean insert(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTen", thuThu.getTen());
        values.put("matKhau", thuThu.getMatKhau());
        long check = sqLiteDatabase.insert("ThuThu", null, values);
        if (check == -1) {
            return false;
        }
        return true;

    }

    //    public boolean insert(String tenLoai) {
//        ContentValues values = new ContentValues();
//        values.put("tenLoai", tenLoai);
//        long check = sqLiteDatabase.insert("LoaiSach",null ,values);
//        if(check == -1){
//            return false;
//        }return true;
//
//    }
//
    public boolean update(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTen", thuThu.getTen());
        values.put("matKhau", thuThu.getMatKhau());
        long check = sqLiteDatabase.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(thuThu.getMaTT())});
        if (check == -1) {
            return false;
        }
        return true;

    }

    public int delete(String id) {

//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu  Where maTT = ?", new String[]{String.valueOf(id)});
//        Log.d("zzz" , "Xoa Loi1");
//        if(cursor.getCount() != 0){
//            Log.d("zzz" , "Xoa Loi2");
//            return -1;
//        }
        return sqLiteDatabase.delete("ThuThu", "maTT = ?", new String[]{id});

    }

    @SuppressLint("Range")
    public ArrayList<ThuThu> getData(String sql, String... selectionArgs) {
        ArrayList<ThuThu> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(c.getString(c.getColumnIndex("maTT")));
            thuThu.setTen(c.getString(c.getColumnIndex("hoTen")));
            thuThu.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
//            Log.i("thuThu", thuThu.toString());
            list.add(thuThu);
        }
        return list;
    }

    //get all
    public ArrayList<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    //get id
    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu Where maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String pass) {
        String sql = "Select * from ThuThu Where maTT=? and matKhau=?";
        List<ThuThu> list = getData(sql, id, pass);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
