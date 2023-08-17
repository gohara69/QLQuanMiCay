/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class TableHoaDon {
    int maHoaDon, soBan, thanhTien;
    String tenNhanVien, ngayXuatHD, trangThai;

    public TableHoaDon(){}
    public TableHoaDon(int maHoaDon, int soBan, int thanhTien, String tenNhanVien, String ngayXuatHD, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.soBan = soBan;
        this.thanhTien = thanhTien;
        this.tenNhanVien = tenNhanVien;
        this.ngayXuatHD = ngayXuatHD;
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getSoBan() {
        return soBan;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgayXuatHD() {
        return ngayXuatHD;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setNgayXuatHD(String ngayXuatHD) {
        this.ngayXuatHD = ngayXuatHD;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
