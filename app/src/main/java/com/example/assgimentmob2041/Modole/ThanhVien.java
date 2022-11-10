package com.example.assgimentmob2041.Modole;

public class ThanhVien {
    private int maThanhVien;
    private String hoTen;
    private String namSinh;

    public ThanhVien(int maThanhVien, String hoTen, String namSinh) {
        this.maThanhVien = maThanhVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maThanhVien=" + maThanhVien +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                '}';
    }
}
