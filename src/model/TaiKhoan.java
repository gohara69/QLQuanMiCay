/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class TaiKhoan {
    private int TK_ID;
    private String TK_TEN;
    private String TK_MATKHAU;
    private String TK_NV;
    private String TK_NGUOIDUNG;

    public TaiKhoan(int TK_ID, String TK_TEN, String TK_MATKHAU, String TK_NV, String TK_NGUOIDUNG) {
        this.TK_ID = TK_ID;
        this.TK_TEN = TK_TEN;
        this.TK_MATKHAU = TK_MATKHAU;
        this.TK_NV = TK_NV;
        this.TK_NGUOIDUNG = TK_NGUOIDUNG;
    }

    public TaiKhoan() {
    }

    public int getTK_ID() {
        return TK_ID;
    }

    public void setTK_ID(int TK_ID) {
        this.TK_ID = TK_ID;
    }

    public String getTK_TEN() {
        return TK_TEN;
    }

    public void setTK_TEN(String TK_TEN) {
        this.TK_TEN = TK_TEN;
    }

    public String getTK_MATKHAU() {
        return TK_MATKHAU;
    }

    public void setTK_MATKHAU(String TK_MATKHAU) {
        this.TK_MATKHAU = TK_MATKHAU;
    }

    public String getTK_NV() {
        return TK_NV;
    }

    public void setTK_NV(String TK_NV) {
        this.TK_NV = TK_NV;
    }

    public String getTK_NGUOIDUNG() {
        return TK_NGUOIDUNG;
    }

    public void setTK_NGUOIDUNG(String TK_NGUOIDUNG) {
        this.TK_NGUOIDUNG = TK_NGUOIDUNG;
    }
    
    
}
