/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class NhaCungCap {
    private int NCC_ID;
    private String NCC_TEN;
    private String NCC_SDT;
    private String NCC_DIACHI;

    public NhaCungCap() {
    }
    
    public NhaCungCap(int NCC_ID){
        this.NCC_ID = NCC_ID;
    }

    public NhaCungCap(int NCC_ID, String NCC_TEN, String NCC_SDT, String NCC_DIACHI) {
        this.NCC_ID = NCC_ID;
        this.NCC_TEN = NCC_TEN;
        this.NCC_SDT = NCC_SDT;
        this.NCC_DIACHI = NCC_DIACHI;
    }

    public int getNCC_ID() {
        return NCC_ID;
    }

    public void setNCC_ID(int NCC_ID) {
        this.NCC_ID = NCC_ID;
    }

    public String getNCC_TEN() {
        return NCC_TEN;
    }

    public void setNCC_TEN(String NCC_TEN) {
        this.NCC_TEN = NCC_TEN;
    }

    public String getNCC_SDT() {
        return NCC_SDT;
    }

    public void setNCC_SDT(String NCC_SDT) {
        this.NCC_SDT = NCC_SDT;
    }

    public String getNCC_DIACHI() {
        return NCC_DIACHI;
    }

    public void setNCC_DIACHI(String NCC_DIACHI) {
        this.NCC_DIACHI = NCC_DIACHI;
    }
    
    
}
