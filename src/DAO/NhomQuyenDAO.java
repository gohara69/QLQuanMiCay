/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiDung;
import model.NhaCungCap;
import model.NhomQuyen;
import model.Quyen;

/**
 *
 * @author VU HOANG
 */
public class NhomQuyenDAO {
     public static ArrayList<NhomQuyen> layDanhSachNhomQuyen() {
        ArrayList<NhomQuyen> dsNQ = new ArrayList<>();
        try {
            String sql = "select * from NHOMQUYEN";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NhomQuyen nquyen = new NhomQuyen();
                nquyen.setNQ_HIENTHI(rs.getString("NQ_HIENTHI"));
                nquyen.setNQ_ID(rs.getInt("NQ_ID"));
                nquyen.setNQ_TEN(rs.getString("NQ_TEN"));

                dsNQ.add(nquyen);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsNQ;
    }
     
     public static boolean kiemTraNguoiDungCoNhomQuyen(NhomQuyen nq, NguoiDung ng){
         boolean kq = false;
         try {
            String sql = String.format("select *\n" +
                                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN, NGUOIDUNG\n" +
                                    "where NHOMQUYEN.NQ_HIENTHI = N'%s' and NGUOIDUNG.NG_PHONGBAN = N'%s' \n" +
                                    "and NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NGUOIDUNG.NG_ID = NHOMQUYEN_NGUOIDUNG.NG_ID", nq.getNQ_HIENTHI(), ng.getNG_PHONGBAN());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                kq = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return kq;
     }
     
     public static NhomQuyen layNhomQuyenTheoHienThi(NhomQuyen nquyen) {
        NhomQuyen nq = new NhomQuyen();
        try {
            String sql = "select * from NHOMQUYEN where NQ_HIENTHI = N'" + nquyen.getNQ_HIENTHI() + "'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                nq.setNQ_HIENTHI(rs.getString("NQ_HIENTHI"));
                nq.setNQ_ID(rs.getInt("NQ_ID"));
                nq.setNQ_TEN(rs.getString("NQ_TEN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return nq;
    }
     
    public static ArrayList<String> layDanhSachCacBang(){
        ArrayList<String> dsBang = new ArrayList<>();
         try {
             String sql = "select name from sys.tables where name != 'sysdiagrams'";
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 String bang = rs.getString("name");
                 dsBang.add(bang);
             }
         } catch (SQLException ex) {
             Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return dsBang;
    }
    
    public static Integer layMaTiepTheoCuaNhomQuyen(){
        int id = 0;
        try {
             String sql = "Select Top 1 * from NHOMQUYEN order by NQ_ID desc";
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 id = rs.getInt("NQ_ID");
             }} catch (SQLException ex) {
             Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return id + 1;
    }
    
    public static Boolean kiemTraTrungTenNhomQuyen(NhomQuyen nq){
        Boolean kq = false; 
        try {
             String sql = String.format("Select * from NHOMQUYEN where NQ_TEN = '%s'", nq.getNQ_TEN());
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 kq = true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return kq;
    }
    
    public static Boolean kiemTraTrungHienThi(NhomQuyen nq){
        Boolean kq = false; 
        try {
             String sql = String.format("Select * from NHOMQUYEN where NQ_HIENTHI = N'%s'", nq.getNQ_HIENTHI());
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 kq = true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return kq;
    }
    
    public static Boolean themNhomQuyen(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("insert into NhomQuyen values(%d, '%s', N'%s')", nq.getNQ_ID(), nq.getNQ_TEN(),nq.getNQ_HIENTHI());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean themNhomQuyenDatabase(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("sp_addrole '%s'", nq.getNQ_TEN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean xoaNhomQuyenDatabase(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("sp_droprole '%s'", nq.getNQ_TEN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean xoaCapQuyenTheoNhomQuyen(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("delete CAPQUYEN_NHOM where MANHOM = %d", nq.getNQ_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean xoaNhomQuyen(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("delete NHOMQUYEN where NQ_ID = %d", nq.getNQ_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean capQuyenChoNhom(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        if(!QuyenDAO.kiemTraDaTonTaiQuyen(q)){
            QuyenDAO.themQuyen(q);
        }
        Quyen quyen = QuyenDAO.layQuyenTheoDienGiai(q);
        String sql = String.format("insert into CAPQUYEN_NHOM values(%d, %d)", nq.getNQ_ID(), quyen.getQUYEN_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean capQuyenChoNhomDatabase(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("grant %s to %s", q.getDIENGIAI(), nq.getNQ_TEN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean xoaQuyenCuaNhom(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        if(!QuyenDAO.kiemTraDaTonTaiQuyen(q)){
            QuyenDAO.themQuyen(q);
        }
        Quyen quyen = QuyenDAO.layQuyenTheoDienGiai(q);
        String sql = String.format("delete CAPQUYEN_NHOM"
                + " where MANHOM = %d and MAQUYEN = %d", nq.getNQ_ID(), quyen.getQUYEN_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean xoaQuyenCuaNhomDatabase(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("revoke %s from %s", q.getDIENGIAI(), nq.getNQ_TEN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n >= 1){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean kiemTraNhomDaCoQuyen(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        try {
             String sql = String.format("Select *\n" +
                     "from CAPQUYEN_NHOM, QUYEN\n" +
                     "where CAPQUYEN_NHOM.MAQUYEN = QUYEN.QUYEN_ID and DIENGIAI = '%s' and MANHOM = %d", q.getDIENGIAI(), nq.getNQ_ID());
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 kq = true;
             }
         } catch (SQLException ex) {
             Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return kq;
    }
}
