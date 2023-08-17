/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class ChiTietNhap {
    private int NL_ID;
    private int HDN_ID;
    private int CTN_SOLUONG;
    private float CTN_GIA;

    public ChiTietNhap(int NL_ID, int HDN_ID, int CTN_SOLUONG, float CTN_GIA) {
        this.NL_ID = NL_ID;
        this.HDN_ID = HDN_ID;
        this.CTN_SOLUONG = CTN_SOLUONG;
        this.CTN_GIA = CTN_GIA;
    }
    
    public ChiTietNhap(){
        
    }

    public int getNL_ID() {
        return NL_ID;
    }

    public void setNL_ID(int NL_ID) {
        this.NL_ID = NL_ID;
    }

    public int getHDN_ID() {
        return HDN_ID;
    }

    public void setHDN_ID(int HDN_ID) {
        this.HDN_ID = HDN_ID;
    }

    public int getCTN_SOLUONG() {
        return CTN_SOLUONG;
    }

    public void setCTN_SOLUONG(int CTN_SOLUONG) {
        this.CTN_SOLUONG = CTN_SOLUONG;
    }

    public float getCTN_GIA() {
        return CTN_GIA;
    }

    public void setCTN_GIA(float CTN_GIA) {
        this.CTN_GIA = CTN_GIA;
    }
    
    
}
