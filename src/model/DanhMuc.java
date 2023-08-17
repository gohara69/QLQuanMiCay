/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class DanhMuc {
    private int DM_ID;
    private String QA_DIACHI;
    private String DM_TEN;

    public DanhMuc() {
    }

    public DanhMuc(int DM_ID, String QA_DIACHI, String DM_TEN) {
        this.DM_ID = DM_ID;
        this.QA_DIACHI = QA_DIACHI;
        this.DM_TEN = DM_TEN;
    }

    public int getDM_ID() {
        return DM_ID;
    }

    public void setDM_ID(int DM_ID) {
        this.DM_ID = DM_ID;
    }

    public String getQA_DIACHI() {
        return QA_DIACHI;
    }

    public void setQA_DIACHI(String QA_DIACHI) {
        this.QA_DIACHI = QA_DIACHI;
    }

    public String getDM_TEN() {
        return DM_TEN;
    }

    public void setDM_TEN(String DM_TEN) {
        this.DM_TEN = DM_TEN;
    }
}
