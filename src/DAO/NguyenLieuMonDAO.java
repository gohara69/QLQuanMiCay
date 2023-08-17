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
import model.Ban;
import model.HoaDon;
import model.Mon;
import model.NguyenLieu;

/**
 *
 * @author MINH HOANG
 */
public class NguyenLieuMonDAO {
    public static ArrayList<NguyenLieu> layDanhSachNguyenLieuCuaMon(Mon m) {
        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM M_CO_NL\n" +
                        "WHERE M_TEN = N'%s'", m.getTenMon());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_DONVITINH(rs.getString("MNL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("MNL_SOLUONG"));
               
                dsnl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
    public static boolean kiemTraTonTaiNguyenLieuTuongDong(NguyenLieu nl) {
        boolean kq = false;
        try {
            String sql = String.format("select *\n" +
                        "from NL_TUONGDONG\n" +
                        "where NL_ID = %d", nl.getNL_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                kq = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static int layMaHoaDonTiepTheo() {
        int id = 0;
        try {
            String sql = "Select TOP 1 STT from NL_TUONGDONG order by STT desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("STT");
            }
        } catch (Exception e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id + 1;
    }
    
    public static boolean themVaoNguyenLieuTuongDong(NguyenLieu nl) {
        boolean kq = false;
            String sql = String.format("insert into NL_TUONGDONG\n" +
                        "values(%d, %d, N'%s', %d, null)", layMaHoaDonTiepTheo(),nl.getNL_ID(), nl.getNL_DONVITINH(), nl.getNL_SOLUONG());
            DataService ds = new DataService();
            ds.open();
            int n = ds.executeUpdate(sql);
            if (n == 1) {
                kq = true;
            }
            ds.close();
        return kq;
    }
    
    public static ArrayList<NguyenLieu> layDanhSachNguyenLieuTuongDong(NguyenLieu nlieu) {
        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
        try {
            String sql = String.format("select * from NL_TUONGDONG\n" +
                        "where NL_ID = %d or \n" +
                        "STT_THAYCHO = (SELECT STT FROM NL_TUONGDONG WHERE NL_ID = %d)", nlieu.getNL_ID(), nlieu.getNL_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(NguyenLieuDAO.searchIngredientById(nl).getNL_TEN());
                nl.setNL_ID(rs.getInt("STT"));
                nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
               
                dsnl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
    public static NguyenLieu layNguyenLieuTuongDongTheoSTT(int stt) {
        NguyenLieu nl = new NguyenLieu();
        try {
            String sql = String.format("select * from NL_TUONGDONG\n" +
                        "where STT = %d", stt);
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(NguyenLieuDAO.searchIngredientById(nl).getNL_TEN());
                nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nl;
    }
}
