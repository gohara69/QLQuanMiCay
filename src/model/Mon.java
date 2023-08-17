/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Mon {
    private String tenMon, srcAnh;
    private int danhMuc, giaMon;
    
    public Mon(){
        
    }

    public Mon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Mon(String tenMo, String srcAnh, int danhMuc, int giaMon) {
        this.tenMon = tenMo;
        this.srcAnh = srcAnh;
        this.danhMuc = danhMuc;
        this.giaMon = giaMon;
    }
    
    public String getTenMon() {
        return tenMon;
    }

    public String getSrcAnh() {
        return srcAnh;
    }

    public int getDanhMuc() {
        return danhMuc;
    }

    public int getGiaMon() {
        return giaMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public void setSrcAnh(String srcAnh) {
        this.srcAnh = srcAnh;
    }

    public void setDanhMuc(int danhMuc) {
        this.danhMuc = danhMuc;
    }

    public void setGiaMon(int giaMon) {
        this.giaMon = giaMon;
    }

    
}
