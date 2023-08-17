/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Order {
    private String tenMon;
    private int idHoaDon, soLuong, giaTien;
    private String O_GHICHU;
    private String NV_ID;
    private int TT_MON;
    private int TT_THUCHIEN;
    private String O_THOIGIAN;

    public Order() {
    }
    
    public Order(Order o) {
        this.setGiaTien(o.getGiaTien());
        this.setIdHoaDon(o.getIdHoaDon());
        this.setNV_ID(o.getNV_ID());
        this.setO_GHICHU(o.getO_GHICHU());
        this.setO_THOIGIAN(o.getO_THOIGIAN());
        this.setSoLuong(o.getSoLuong());
        this.setTT_MON(o.getTT_MON());
        this.setTT_THUCHIEN(o.getTT_THUCHIEN());
        this.setTenMon(o.getTenMon());
    }

    public String getO_GHICHU() {
        return O_GHICHU;
    }

    public void setO_GHICHU(String O_GHICHU) {
        this.O_GHICHU = O_GHICHU;
    }

    public String getNV_ID() {
        return NV_ID;
    }

    public void setNV_ID(String NV_ID) {
        this.NV_ID = NV_ID;
    }

    public int getTT_MON() {
        return TT_MON;
    }

    public void setTT_MON(int TT_MON) {
        this.TT_MON = TT_MON;
    }

    public int getTT_THUCHIEN() {
        return TT_THUCHIEN;
    }

    public void setTT_THUCHIEN(int TT_THUCHIEN) {
        this.TT_THUCHIEN = TT_THUCHIEN;
    }

    public String getO_THOIGIAN() {
        return O_THOIGIAN;
    }

    public void setO_THOIGIAN(String O_THOIGIAN) {
        this.O_THOIGIAN = O_THOIGIAN;
    }
    
    

    public Order(String tenMon, int idHoaDon, int soLuong, int giaTien) {
        this.tenMon = tenMon;
        this.idHoaDon = idHoaDon;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
    
}
