/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class ChiTietPhieuDat {
    private int PD_ID;
    private int NL_ID;
    private int SOLUONG;
    private int DAGIAO;

    public ChiTietPhieuDat() {
    }

    public ChiTietPhieuDat(int PD_ID, int NL_ID, int SOLUONG, int DAGIAO) {
        this.PD_ID = PD_ID;
        this.NL_ID = NL_ID;
        this.SOLUONG = SOLUONG;
        this.DAGIAO = DAGIAO;
    }

    public int getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(int PD_ID) {
        this.PD_ID = PD_ID;
    }

    public int getNL_ID() {
        return NL_ID;
    }

    public void setNL_ID(int NL_ID) {
        this.NL_ID = NL_ID;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getDAGIAO() {
        return DAGIAO;
    }

    public void setDAGIAO(int DAGIAO) {
        this.DAGIAO = DAGIAO;
    }
    
    
}
