package com.example.dichvugiacsay.Model;

public class LichSuDonHang {
    private String maDH, name, trangthai, gia;
    private int img;

    public LichSuDonHang(String maDH, String name, String trangthai, String gia, int img) {
        this.maDH = maDH;
        this.name = name;
        this.trangthai = trangthai;
        this.gia = gia;
        this.img = img;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
