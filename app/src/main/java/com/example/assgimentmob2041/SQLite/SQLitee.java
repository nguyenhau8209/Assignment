package com.example.assgimentmob2041.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLitee extends SQLiteOpenHelper {
    public SQLitee(Context context) {
        super(context, "PNL", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ThuThu = "CREATE TABLE ThuThu (maTT TEXT PRIMARY KEY ," +
                " hoTen Text Not null , " +
                "matKhau text not null)";
        sqLiteDatabase.execSQL(ThuThu);
        //
        String ThanhVien = "CREATE TABLE ThanhVien (maTV Integer Primary key Autoincrement " +
                ", hoTen Text not null " +
                ", namSinh Text)";
        sqLiteDatabase.execSQL(ThanhVien);
        //
        String LoaiSach = "Create table LoaiSach (maLoai Integer primary key autoincrement" +
                ", tenLoai text not null)";
        sqLiteDatabase.execSQL(LoaiSach);
        //
        String Sach = "Create table Sach(maSach Integer primary key autoincrement" +
                ", tenSach text not null" +
                ", giaThue Integer not null" +
                ", maLoai integer references LoaiSach(maLoai))";
        sqLiteDatabase.execSQL(Sach);
        String PhieuMuon = "Create table PhieuMuon(maPM Integer primary key autoincrement" +
                ", maTT text references ThuThu(maTT)" +
                ", maTV Integer references ThanhVien(maTV)" +
                ", maSach integer references Sach(maSach)" +
                ", giaThue integer not null " +
                ", ngay Date not null" +
                ", gio Date not null" +
                ",traSach Integer not null)";
        sqLiteDatabase.execSQL(PhieuMuon);
        //data mẫu
        sqLiteDatabase.execSQL("INSERT INTO ThuThu VALUES('thuthu01','Nguyễn Văn A','123a') , ('admin' , 'Nguyễn Hoàng Quân' , 'admin')");
        sqLiteDatabase.execSQL("INSERT INTO LoaiSach (tenLoai) VALUES(  'Giaso trình B1') , ('Lập trình máy tính') , ('Ứng dụng phần mềm')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ThuThu");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ThanhVien");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LoaiSach");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Sach");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PhieuMuon");
        onCreate(sqLiteDatabase);
    }
}
