/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class NhomQuyen_NguoiDung {
    private int NQ_ID;
    private String NG_ID;

    public NhomQuyen_NguoiDung() {
    }

    public NhomQuyen_NguoiDung(int NQ_ID, String NG_ID) {
        this.NQ_ID = NQ_ID;
        this.NG_ID = NG_ID;
    }

    public int getNQ_ID() {
        return NQ_ID;
    }

    public void setNQ_ID(int NQ_ID) {
        this.NQ_ID = NQ_ID;
    }

    public String getNG_ID() {
        return NG_ID;
    }

    public void setNG_ID(String NG_ID) {
        this.NG_ID = NG_ID;
    }
    
    
}
