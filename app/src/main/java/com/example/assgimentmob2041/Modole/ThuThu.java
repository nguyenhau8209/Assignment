package com.example.assgimentmob2041.Modole;

public class ThuThu {
    String maTT;
    String ten;
    String matKhau;

    public ThuThu(String maTT, String ten, String matKhau) {
        this.maTT = maTT;
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public ThuThu() {
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "ThuThu{" +
                "maTT='" + maTT + '\'' +
                ", ten='" + ten + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}
