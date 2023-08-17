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

/**
 *
 * @author VU HOANG
 */
public class BanDAO {

    public static ArrayList<Ban> layDanhSachBan() {
        ArrayList<Ban> dsb = new ArrayList<Ban>();
        try {
            String sql = "Select * from BAN";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Ban ban = new Ban();
                ban.setB_SOBAN(rs.getInt("B_SOBAN"));
                ban.setB_VITRI(rs.getString("B_VITRI"));

                dsb.add(ban);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsb;
    }

    public static ArrayList<Ban> timKiemDanhMucTheoTen(Ban ban) {
        ArrayList<Ban> dsb = new ArrayList<Ban>();
        try {
            String sql = "select * from BAN where B_VITRI like N'%" + ban.getB_VITRI() + "%'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Ban b = new Ban();
                b.setB_SOBAN(rs.getInt("B_SOBAN"));
                b.setB_VITRI(rs.getString("B_VITRI"));

                dsb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsb;
    }

    public static boolean themBan(Ban ban) {
        boolean kq = false;
        String sql = String.format("insert into BAN "
                + "values(%d,N'%s')", ban.getB_SOBAN(), ban.getB_VITRI());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean xoaBan(Ban ban) {
        boolean kq = false;
        String sql = String.format("Delete from BAN where B_SOBAN = %d", ban.getB_SOBAN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean capNhatBan(Ban ban) {
        boolean kq = false;
        String sql = String.format("update BAN set B_VITRI = N'%s' where B_SOBAN = %d", ban.getB_VITRI(), ban.getB_SOBAN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static ArrayList<Integer> getListTableEmpty() {
        ArrayList<Integer> dsb = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT B_SOBAN FROM BAN WHERE not exists (Select B_SOBAN from HOADON WHERE HOADON.B_SOBAN = BAN.B_SOBAN AND HD_TrangThai = 0)";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                int b = rs.getInt("B_SOBAN");
                dsb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsb;
    }
    
    public static ArrayList<Integer> getListTableNotEmpty() {
        ArrayList<Integer> dsb = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT B_SOBAN FROM HOADON WHERE HD_TrangThai = 0 and exists (Select B_SOBAN from BAN) Order by B_SOBAN asc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                int b = rs.getInt("B_SOBAN");
                dsb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsb;
    }

    public static int getQuantityTable() {
        int kq = 0;
        String sql = "select count(*) from BAN";
        DataService ds = new DataService();
        try {
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            if (rs.next())
                kq = rs.getInt(1);
            ds.close();
        } catch (Exception ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
}
