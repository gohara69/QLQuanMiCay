/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class PhieuDat {
    private int PD_ID;
    private String NV_ID;
    private int NCC_ID;
    private String PD_DATE;
    private boolean PD_TRANGTHAI;

    public PhieuDat() {
    }

    public PhieuDat(int PD_ID, String NV_ID, int NCC_ID, String PD_DATE, boolean PD_TRANGTHAI) {
        this.PD_ID = PD_ID;
        this.NV_ID = NV_ID;
        this.NCC_ID = NCC_ID;
        this.PD_DATE = PD_DATE;
        this.PD_TRANGTHAI = PD_TRANGTHAI;
    }

    public int getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(int PD_ID) {
        this.PD_ID = PD_ID;
    }

    public String getNV_ID() {
        return NV_ID;
    }

    public void setNV_ID(String NV_ID) {
        this.NV_ID = NV_ID;
    }

    public int getNCC_ID() {
        return NCC_ID;
    }

    public void setNCC_ID(int NCC_ID) {
        this.NCC_ID = NCC_ID;
    }

    public String getPD_DATE() {
        return PD_DATE;
    }

    public void setPD_DATE(String PD_DATE) {
        this.PD_DATE = PD_DATE;
    }

    public boolean isPD_TRANGTHAI() {
        return PD_TRANGTHAI;
    }

    public void setPD_TRANGTHAI(boolean PD_TRANGTHAI) {
        this.PD_TRANGTHAI = PD_TRANGTHAI;
    }
}