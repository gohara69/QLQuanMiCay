/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class NhomQuyen {
    private int NQ_ID;
    private String NQ_TEN;
    private String NQ_HIENTHI;

    public NhomQuyen() {
    }

    public NhomQuyen(int NQ_ID, String NQ_TEN, String NQ_HIENTHI) {
        this.NQ_ID = NQ_ID;
        this.NQ_TEN = NQ_TEN;
        this.NQ_HIENTHI = NQ_HIENTHI;
    }

    public int getNQ_ID() {
        return NQ_ID;
    }

    public void setNQ_ID(int NQ_ID) {
        this.NQ_ID = NQ_ID;
    }

    public String getNQ_TEN() {
        return NQ_TEN;
    }

    public void setNQ_TEN(String NQ_TEN) {
        this.NQ_TEN = NQ_TEN;
    }

    public String getNQ_HIENTHI() {
        return NQ_HIENTHI;
    }

    public void setNQ_HIENTHI(String NQ_HIENTHI) {
        this.NQ_HIENTHI = NQ_HIENTHI;
    }
    
    
}
