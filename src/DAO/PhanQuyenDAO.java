/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import model.NguoiDung;
import model.NhomQuyen;
import model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quyen;

/**
 *
 * @author VU HOANG
 */
public class PhanQuyenDAO {
    public static boolean phanQuyen(NhomQuyen nq, NguoiDung ng){
        boolean kq = false;
        String sql = String.format("sp_addrolemember '%s', '%s'", nq.getNQ_TEN(), ng.getNG_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean huyPhanQuyen(NhomQuyen nq, NguoiDung ng){
        boolean kq = false;
        String sql = String.format("sp_droprolemember '%s', '%s'", nq.getNQ_TEN(), ng.getNG_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean kiemTraCoQuyenDatNguyenLieu(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Đặt nguyên liệu'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenNhapNguyenLieu(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Nhập nguyên liệu'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemHoaDonNhap(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem hóa đơn nhập'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static Boolean kiemTraPhanQuyenChoNhom(Quyen q, NhomQuyen nq){
        Boolean kq = false;
        try {
            String sql = String.format("Select * \n" +
                    "from CAPQUYEN_NHOM, QUYEN\n" +
                    "where MANHOM = %d and CAPQUYEN_NHOM.MAQUYEN = QUYEN.QUYEN_ID \n" +
                    "and DIENGIAI = '%s'", nq.getNQ_ID(), q.getDIENGIAI());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemDanhMuc(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem danh mục'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenThaoTacDanhMuc(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Thao tác danh mục'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemDoanhThu(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem doanh thu'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemNhaCungCap(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem nhà cung cấp'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenThaoTacNhaCungCap(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Thao tác nhà cung cấp'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemNhanVien(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem nhân viên'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenThaoTacNhanVien(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Thao tác nhân viên'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemNguyenLieu(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem nguyên liệu'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenThaoTacNguyenLieu(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Thao tác nguyên liệu'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenXemOrder(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Xem order'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenGoiMon(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Gọi món'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static boolean kiemTraCoQuyenPhanCongMon(TaiKhoan tk){
        boolean kq = false;
        try {
            String sql = String.format("select * \n" +
                    "from NHOMQUYEN_NGUOIDUNG, NHOMQUYEN\n" +
                    "where NHOMQUYEN_NGUOIDUNG.NQ_ID = NHOMQUYEN.NQ_ID and NHOMQUYEN_NGUOIDUNG.NG_ID = '%s'\n" +
                    "and NHOMQUYEN.NQ_HIENTHI = N'Phân công món'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
            }
            return kq;
        } catch (SQLException ex) {
            Logger.getLogger(PhanQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
}
