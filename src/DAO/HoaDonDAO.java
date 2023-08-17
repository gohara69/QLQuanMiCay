/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import form.frmChonMon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Ban;
import model.HoaDon;
import model.NguyenLieu;
import model.Order;
import model.TableHoaDon;

/**
 *
 * @author PC
 */
public class HoaDonDAO {

    public static boolean addBill(HoaDon hd) {
        boolean kq = false;
        String sql = "INSERT INTO HOADON(HD_ID, NV_ID, B_SOBAN, HD_THANHTIEN, HD_NGAYXUAT, HD_TrangThai) values (" + hd.getMaHD() + ", '" + hd.getMaNV() + "', " + hd.getSoBan() + ", " + hd.getThanhTien() + ", '" + hd.getNgayXuatHD() + "', '" + hd.isTrangThai() + "')";
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static int layThanhTienHoaDon(HoaDon hd){
        int sum = 0;
        try {
            String sql = String.format("select *\n" +
                        "from [ORDER]\n" +
                        "where HD_ID = %d", hd.getMaHD());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                sum+= rs.getInt("O_GIA");
            }
        } catch (Exception e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return sum;
    }
    
    

    public static int layMaHoaDonTiepTheo() {
        ArrayList<HoaDon> dshd = new ArrayList<>();
        int id = 0;
        try {
            String sql = "Select TOP 1 HD_ID from HOADON order by HD_ID desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("HD_ID");
            }
        } catch (Exception e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id + 1;
    }

    public static int layDonGiaHoaDon(int soBan) {
        int donGia = 0;
        try {
            String sql = "select HD_THANHTIEN from HOADON where B_SOBAN = " + soBan + " and HD_TrangThai = 'False'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                donGia = rs.getInt("HD_THANHTIEN");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return donGia;
    }

    public static int layMaHoaDonTheoBan(int soBan) {
        int maHoaDon = 0;
        try {
            String sql = "select HD_ID from HOADON where B_SOBAN = " + soBan + " and HD_TrangThai = 'False'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                maHoaDon = rs.getInt("HD_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maHoaDon;
    }

    public static boolean thanhToanHoaDon(int soBan) {
        boolean kq = false;
        String sql = "update HOADON set HD_TrangThai = 'True', HD_NGAYXUAT = GETDATE() where B_SOBAN = "+soBan+" and HD_TrangThai = 'False'";
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean capNhatThanhTienHoaDon(HoaDon hd) {
        boolean kq = false;
        int thanhTien = layThanhTienHoaDon(hd);
        String sql = String.format("update HOADON set HD_THANHTIEN = %d where HD_ID = %d", thanhTien,hd.getMaHD());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static ArrayList<TableHoaDon> getListBill(){
        ArrayList<TableHoaDon> dshd = new ArrayList<>();
   
        try {
            String sql = "select * from HOADON hd, NHANVIEN nv where nv.NV_ID = hd.NV_ID";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                TableHoaDon hd = new TableHoaDon();
                String trangThai = rs.getString("HD_TrangThai");
                hd.setMaHoaDon(rs.getInt("HD_ID"));
                hd.setTenNhanVien(rs.getString("NV_TEN"));
                hd.setSoBan(rs.getInt("B_SOBAN"));
                hd.setThanhTien(rs.getInt("HD_THANHTIEN"));
                hd.setNgayXuatHD(rs.getString("HD_NGAYXUAT"));
                if(trangThai.equals("0"))
                    hd.setTrangThai("Chưa thanh toán");
                else
                    hd.setTrangThai("Đã thanh toán");               
                dshd.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dshd;
    }
    
    public static boolean themMoiHoaDon(HoaDon hd) {
        boolean kq = false;
        String sql = String.format("INSERT INTO HOADON(HD_ID, NV_ID, B_SOBAN, HD_TrangThai)" +
                "values(%d,'%s',%d,'%s')", hd.getMaHD(), hd.getMaNV(), hd.getSoBan(), hd.isTrangThai());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static int layHoaDonChuaThanhToanTheoBan(Ban ban) {
        int maHoaDon = 0;
        try {
            String sql = String.format("select *\n" +
                        "from HOADON \n" +
                        "where B_SOBAN = %d and HD_TrangThai = 'False'", ban.getB_SOBAN());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                maHoaDon = rs.getInt("HD_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maHoaDon;
    }
    
      public static boolean kiemTraTatCaHoaDonChuaHoanThanh(HoaDon hd) {
        boolean kq = false;
        String sql = String.format("select * \n" +
                        "from [ORDER]\n" +
                        "where HD_ID = %d and TT_MON != 3", hd.getMaHD());
        DataService ds = new DataService();
        ds.open();
        ResultSet rs = ds.executeQuery(sql);
        try {
            while (rs.next()) {
                kq = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ds.close();
        return kq;
    }
}
