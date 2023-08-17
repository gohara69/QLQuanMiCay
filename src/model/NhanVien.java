/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Ngoc Tuyen
 */
public class NhanVien {
    private String NV_ID;
    private String QA_DIACHI;
    private String NV_TEN;
    private Boolean NV_GIOITINH;
    private String NV_NGAYSINH;
    private String NV_DIACHI;
    private String NV_SDT;
    private String NV_IMG;
    private String NV_TINHTRANG;
    public NhanVien(){
    }
    
    
    
    public NhanVien(String NV_ID, String QA_DIACHI, String NV_TEN,Boolean NV_GIOITINH,String NV_NGAYSINH, String NV_DIACHI,String NV_SDT,String NV_IMG,String NV_TINHTRANG) {
        this.NV_ID= NV_ID;
        this.QA_DIACHI = QA_DIACHI;
        this.NV_TEN = NV_TEN;
        this.NV_GIOITINH = NV_GIOITINH;
        this.NV_NGAYSINH = NV_NGAYSINH;
        this.NV_DIACHI = NV_DIACHI;
        this.NV_SDT = NV_SDT;
        this.NV_IMG = NV_IMG;
        this.NV_TINHTRANG = NV_TINHTRANG;
    }

    public NhanVien(String NV_ID) {
        this.NV_ID = NV_ID;
    }

    public String getNV_ID() {
        return NV_ID;
    }

    public void setNV_ID(String NV_ID) {
        this.NV_ID = NV_ID;
    }

    public String getQA_DIACHI() {
        return QA_DIACHI;
    }

    public void setQA_DIACHI(String QA_DIACHI) {
        this.QA_DIACHI = QA_DIACHI;
    }

    public String getNV_TEN() {
        return NV_TEN;
    }

    public void setNV_TEN(String NV_TEN) {
        this.NV_TEN = NV_TEN;
    }

    public Boolean getNV_GIOITINH() {
        return NV_GIOITINH;
    }

    public void setNV_GIOITINH(Boolean NV_GIOITINH) {
        this.NV_GIOITINH = NV_GIOITINH;
    }

    public String getNV_NGAYSINH() {
        return NV_NGAYSINH;
    }

    public void setNV_NGAYSINH(String NV_NGAYSINH) {
        this.NV_NGAYSINH = NV_NGAYSINH;
    }

    public String getNV_DIACHI() {
        return NV_DIACHI;
    }

    public void setNV_DIACHI(String NV_DIACHI) {
        this.NV_DIACHI = NV_DIACHI;
    }

    public String getNV_SDT() {
        return NV_SDT;
    }

    public void setNV_SDT(String NV_SDT) {
        this.NV_SDT = NV_SDT;
    }

    public String getNV_IMG() {
        return NV_IMG;
    }

    public void setNV_IMG(String NV_IMG) {
        this.NV_IMG = NV_IMG;
    }

    public String getNV_TINHTRANG() {
        return NV_TINHTRANG;
    }

    public void setNV_TINHTRANG(String NV_TINHTRANG) {
        this.NV_TINHTRANG = NV_TINHTRANG;
    }

    public void setNV_ID(int selectedID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    }
