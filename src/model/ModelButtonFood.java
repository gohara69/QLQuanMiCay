/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class ModelButtonFood {
    String tenMon, srcAnh;
    int gia, soLuong;

    public ModelButtonFood(){};
    
    public ModelButtonFood(String tenMon, String srcAnh, int gia, int soLuong) {
        this.tenMon = tenMon;
        this.srcAnh = srcAnh;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getTenMon() {
        return tenMon;
    }

    public String getSrcAnh() {
        return srcAnh;
    }

    public int getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public void setSrcAnh(String srcAnh) {
        this.srcAnh = srcAnh;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
