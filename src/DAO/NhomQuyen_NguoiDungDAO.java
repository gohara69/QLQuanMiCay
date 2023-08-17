/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import model.NguoiDung;
import model.NhomQuyen;
import model.PhieuDat;
import model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VU HOANG
 */
public class NhomQuyen_NguoiDungDAO {
    public static boolean themNhomQuyenNguoiDung(NhomQuyen nq, NguoiDung ng){
        boolean kq = false;
        String sql = String.format("insert into NHOMQUYEN_NGUOIDUNG\n" +
                                   "values(%d,'%s')", nq.getNQ_ID(), ng.getNG_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean xoaNguoiDungKhoiNhomQuyen(NhomQuyen nq, NguoiDung ng){
        boolean kq = false;
        String sql = String.format("delete NHOMQUYEN_NGUOIDUNG\n" +
                                   "where NQ_ID = %d and NG_ID = '%s'", nq.getNQ_ID(), ng.getNG_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    } 
    
    public static boolean kiemTraNhomQuyenDaPhanChoNguoiDung(NhomQuyen nq){
        Boolean kq = false;
        try {
            String sql = String.format("select *\n" +
                    "from NHOMQUYEN_NGUOIDUNG\n" +
                    "where NQ_ID = %d", nq.getNQ_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhomQuyen_NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static Boolean xoaNhomQuyenNguoiDungTheoNhomQuyen(NhomQuyen nq){
        Boolean kq = false;
        String sql = String.format("delete NHOMQUYEN_NGUOIDUNG\n" +
                    "where NQ_ID = %d", nq.getNQ_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n > 0){
            kq = true;
        }
        return kq;
    }
    
    public static Boolean kiemTraNhomQuyenPhanChoNguoiDung(NhomQuyen nq, NguoiDung nd){
        Boolean kq = false;
        try {
            String sql = String.format("select *\n" +
                    "from NHOMQUYEN_NGUOIDUNG\n" +
                    "where NQ_ID = %d and NG_ID = '%s'", nq.getNQ_ID(), nd.getNG_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhomQuyen_NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    
}
