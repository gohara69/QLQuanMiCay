/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class HoaDonNhap {
    private int HDN_ID;
    private String NV_ID;
    private String HDN_NGAYNHAP;
    private float HDN_THANHTIEN;
    private int PD_ID;
    
    public HoaDonNhap(){
        
    }

    public HoaDonNhap(int HDN_ID, String NV_ID, String HDN_NGAYNHAP, float HDN_THANHTIEN, int PD_ID) {
        this.HDN_ID = HDN_ID;
        this.NV_ID = NV_ID;
        this.HDN_NGAYNHAP = HDN_NGAYNHAP;
        this.HDN_THANHTIEN = HDN_THANHTIEN;
        this.PD_ID = PD_ID;
    }

    public int getHDN_ID() {
        return HDN_ID;
    }

    public void setHDN_ID(int HDN_ID) {
        this.HDN_ID = HDN_ID;
    }

    public String getNV_ID() {
        return NV_ID;
    }

    public void setNV_ID(String NV_ID) {
        this.NV_ID = NV_ID;
    }

    public String getHDN_NGAYNHAP() {
        return HDN_NGAYNHAP;
    }

    public void setHDN_NGAYNHAP(String HDN_NGAYNHAP) {
        this.HDN_NGAYNHAP = HDN_NGAYNHAP;
    }

    public float getHDN_THANHTIEN() {
        return HDN_THANHTIEN;
    }

    public void setHDN_THANHTIEN(float HDN_THANHTIEN) {
        this.HDN_THANHTIEN = HDN_THANHTIEN;
    }

    public int getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(int PD_ID) {
        this.PD_ID = PD_ID;
    }
    
    
}
