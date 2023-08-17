/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.DanhMuc;

/**
 *
 * @author VU HOANG
 */
public class DanhMucDAO {
    public static ArrayList<DanhMuc> layDanhSachDanhMuc() {
        ArrayList<DanhMuc> dsdm = new ArrayList<DanhMuc>();
        try {
            String sql = "Select * from DANHMUC";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setDM_ID(rs.getInt("DM_ID"));
                dm.setQA_DIACHI(rs.getString("DM_ID"));
                dm.setDM_TEN(rs.getString("DM_TEN"));
                
                dsdm.add(dm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsdm;
    }
    
    public static ArrayList<DanhMuc> timKiemDanhMucTheoTen (DanhMuc dm) {
        ArrayList<DanhMuc> dsdm = new ArrayList<DanhMuc>();
        try {
            String sql = "select * from DanhMuc where DM_TEN like N'%" + dm.getDM_TEN() + "%'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                DanhMuc dmuc = new DanhMuc();
                dmuc.setDM_ID(rs.getInt("DM_ID"));
                dmuc.setQA_DIACHI(rs.getString("DM_ID"));
                dmuc.setDM_TEN(rs.getString("DM_TEN"));
                
                dsdm.add(dmuc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsdm;
    }
    
    public static boolean themDanhMuc(DanhMuc dm){
        boolean kq = false;
        String sql = String.format("insert into DANHMUC (DM_ID, DM_TEN)" + 
                                    "values(%d,N'%s')", dm.getDM_ID(), dm.getDM_TEN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean xoaDanhMuc(DanhMuc dm){
        boolean kq = false;
        String sql = String.format("Delete from DANHMUC where DM_ID = %d", dm.getDM_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean capNhatDanhMuc(DanhMuc dm){
        boolean kq = false;
        String sql = String.format("update DANHMUC set DM_TEN = N'%s' where DM_ID = %d", dm.getDM_TEN(), dm.getDM_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
}
