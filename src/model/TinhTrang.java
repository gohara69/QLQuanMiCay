/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class TinhTrang {
    private int MA_TT;
    private String MA_DIENGIAI;

    public TinhTrang(int MA_TT) {
        this.MA_TT = MA_TT;
    }

    public TinhTrang() {
    }

    public int getMA_TT() {
        return MA_TT;
    }

    public void setMA_TT(int MA_TT) {
        this.MA_TT = MA_TT;
    }

    public String getMA_DIENGIAI() {
        return MA_DIENGIAI;
    }

    public void setMA_DIENGIAI(String MA_DIENGIAI) {
        this.MA_DIENGIAI = MA_DIENGIAI;
    }
    
    
}
