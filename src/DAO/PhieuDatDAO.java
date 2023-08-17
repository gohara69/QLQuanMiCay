/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.util.ArrayList;
import model.PhieuDat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiDung;
import model.TaiKhoan;


/**
 *
 * @author VU HOANG
 */
public class PhieuDatDAO {
    //public static NguoiDung nguoiDung = TaiKhoanDAO.layNguoiDungTheoTaiKhoan(main.main.tkhoan);
//    public static boolean themPhieuNhap(PhieuDat pd){
//        boolean kq = false;
//        String sql = String.format("insert into PHIEUDAT(NV_ID, NCC_ID, PD_DATE, PD_TRANGTHAI)\n" +
//                                   "values('%s',%d,'%s',0)", pd.getNV_ID(), pd.getNCC_ID(), pd.getPD_DATE());
//        DataService ds = new DataService();
//        ds.open();
//        int n = ds.executeUpdate(sql);
//        if(n == 1){
//            kq = true;
//        }
//        ds.close();
//        return kq;
//    }
    
    public static boolean themPhieuNhap(PhieuDat pd){
        boolean kq = false;
        String sql = String.format("insert into PHIEUDAT\n" +
                                   "values('%s',%d,'%s',0, %d)", pd.getNV_ID(), pd.getNCC_ID(), pd.getPD_DATE(), pd.getPD_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
//    public static int layMaPhieuDatTiepTheo(){
//        int id = 0;
//        try {
//            String sql = "Select TOP 1 PD_ID from PHIEUDAT order by PD_ID desc";
//            DataService ds = new DataService();
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()) {
//                id = rs.getInt("PD_ID");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        return id + 1;
//    }
    
    public static int layMaPhieuDatTiepTheo(){
        int id = 0;
        try {
            String sql = "Select TOP 1 PD_ID from PHIEUDAT order by PD_ID desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("PD_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return id + 1;
    }
    
    public static ArrayList<Integer> layDsMaPhieuDatChuaGiaoDu(){
        ArrayList<Integer> dsPhieuDatId = new ArrayList<>();
        try {
            String sql = "SELECT PD_ID FROM PHIEUDAT WHERE PD_TRANGTHAI = 0";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            int id;
            while(rs.next()) {
                id = rs.getInt("PD_ID");
                dsPhieuDatId.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsPhieuDatId;
    }
    
    public static ArrayList<Integer> layDsMaPhieuDatDaCoHoaDonNhan(){
        ArrayList<Integer> dsPhieuDatId = new ArrayList<>();
        try {
            String sql = "select distinct PHIEUDAT.PD_ID \n" +
                        "from PHIEUDAT, HOADONNHAP \n" +
                        "where PHIEUDAT.PD_ID = HOADONNHAP.PD_ID";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            int id;
            while(rs.next()) {
                id = rs.getInt("PD_ID");
                dsPhieuDatId.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsPhieuDatId;
    }
}
