package com.example.assgimentmob2041.Modole;

import java.sql.Time;
import java.util.Date;

public class PhieuMuon {
    int maPhieuMuon;
    String maTT;
    int maThanhVien;
    int maSach;
    Date ngay;
    Date gio;
    int tienThue;
    int traSach;

    public PhieuMuon(int maPhieuMuon, String maTT, int maThanhVien, int maSach, Date ngay, Date gio, int tienThue, int traSach) {
        this.maPhieuMuon = maPhieuMuon;
        this.maTT = maTT;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngay = ngay;
        this.gio = gio;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }

    public PhieuMuon() {
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getGio() {
        return gio;
    }

    public void setGio(Date gio) {
        this.gio = gio;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }
}
