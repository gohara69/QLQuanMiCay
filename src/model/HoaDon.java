/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class HoaDon {
    int maHD, soBan, thanhTien;
    String maNV, ngayXuatHD;
    boolean trangThai;

    public HoaDon(){}

    public HoaDon(int maHD) {
        this.maHD = maHD;
    }
    
    
    
    public HoaDon(int maHD, int soBan, int thanhTien, String maNV, String ngayXuatHD, boolean trangThai) {
        this.maHD = maHD;
        this.soBan = soBan;
        this.thanhTien = thanhTien;
        this.maNV = maNV;
        this.ngayXuatHD = ngayXuatHD;
        this.trangThai = trangThai;
    }

    public int getMaHD() {
        return maHD;
    }

    public int getSoBan() {
        return soBan;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getNgayXuatHD() {
        return ngayXuatHD;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setNgayXuatHD(String ngayXuatHD) {
        this.ngayXuatHD = ngayXuatHD;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
