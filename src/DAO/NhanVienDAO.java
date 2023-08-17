/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DataService.DataService;
import java.util.ArrayList;
import model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;
import model.PhieuDat;
import model.TaiKhoan;
/**
 *
 * @author Ngoc Tuyen
 */
public class NhanVienDAO {
    public static ArrayList<NhanVien> layDanhSachNhanVien() {
        ArrayList<NhanVien> dsNv = new ArrayList<>();
   
        try {
            String sql = "SELECT * FROM NHANVIEN";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setNV_ID(rs.getString("NV_ID"));
                nv.setQA_DIACHI(rs.getString("QA_DIACHI"));
                nv.setNV_TEN(rs.getString("NV_TEN"));
                nv.setNV_GIOITINH(rs.getBoolean("NV_GIOITINH"));
                nv.setNV_NGAYSINH(rs.getString("NV_NGAYSINH"));
                nv.setNV_DIACHI(rs.getString("NV_DIACHI"));
                nv.setNV_SDT(rs.getString("NV_SDT"));
                nv.setNV_IMG(rs.getString("NV_IMG"));
                nv.setNV_TINHTRANG(rs.getString("NV_TINHTRANG"));
             
                dsNv.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsNv;
    }
    public static NhanVien  layNhanVien(NhanVien nv){
        try{
            String sql = String.format("SELECT * FROM NHANVIEN WHERE NV_ID = '%s'",nv.getNV_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                    nv.setNV_ID(rs.getString("NV_ID"));
                    nv.setQA_DIACHI(rs.getString("QA_DIACHI"));
                    nv.setNV_TEN(rs.getString("NV_TEN"));
                    nv.setNV_GIOITINH(rs.getBoolean("NV_GIOITINH"));
                    nv.setNV_NGAYSINH(rs.getString("NV_NGAYSINH"));
                    nv.setNV_DIACHI(rs.getString("NV_DIACHI"));
                    nv.setNV_SDT(rs.getString("NV_SDT"));
                    nv.setNV_IMG(rs.getString("NV_IMG"));
                    nv.setNV_TINHTRANG(rs.getString("NV_TINHTRANG"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return nv;
    }
    public static ArrayList<NhanVien> timKiemTen (NhanVien nv) {
        ArrayList<NhanVien> dsNv = new ArrayList<NhanVien>();
        try {
            String sql = "select * from NHANVIEN where NV_TEN like N'%" + nv.getNV_TEN() + "%'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NhanVien Nv = new NhanVien();
                Nv.setNV_ID(rs.getString("NV_ID"));
                Nv.setQA_DIACHI(rs.getString("QA_DIACHI"));
                Nv.setNV_TEN(rs.getString("NV_TEN"));
                Nv.setNV_GIOITINH(rs.getBoolean("NV_GIOITINH"));
                Nv.setNV_NGAYSINH(rs.getString("NV_NGAYSINH"));
                Nv.setNV_DIACHI(rs.getString("NV_DIACHI"));
                Nv.setNV_SDT(rs.getString("NV_SDT"));
                Nv.setNV_IMG(rs.getString("NV_IMG"));
                Nv.setNV_TINHTRANG(rs.getString("NV_TINHTRANG"));
                dsNv.add(Nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNv;
    }
     public static boolean xoaNhanVien(NhanVien nv) {
        boolean kq = false;
        String sql = String.format("Delete from NHANVIEN where NV_ID = '%s'", nv.getNV_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean capNhatNhanVien(NhanVien nv) {
        boolean kq = false;
        int sex = nv.getNV_GIOITINH() == true ? 1 : 0;
        String sql = String.format("UPDATE NHANVIEN SET NV_TEN = N'"+nv.getNV_TEN()+"',  NV_GIOITINH = " + sex + ",NV_NGAYSINH = '"+nv.getNV_NGAYSINH()+"', NV_DIACHI = N'"+nv.getNV_DIACHI()+"',NV_SDT = '"+nv.getNV_SDT()+"', NV_TINHTRANG = N'"+nv.getNV_TINHTRANG()+"' WHERE NV_ID = '"+nv.getNV_ID()+"'");
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean themNhanVien(NhanVien nv) {
         boolean kq = false;
        String sql = "INSERT INTO NHANVIEN (NV_ID, NV_TEN,NV_GIOITINH,NV_NGAYSINH, NV_DIACHI, NV_SDT, NV_TINHTRANG) VALUES ('"+nv.getNV_ID()+"', N'"+nv.getNV_TEN()+"','"+nv.getNV_GIOITINH()+"','"+nv.getNV_NGAYSINH()+"', N'"+nv.getNV_DIACHI()+"',  '"+nv.getNV_SDT()+"',N'"+nv.getNV_TINHTRANG()+"')";
        DataService ds = new DataService();
        ds.open();
            int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
     public static NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk){
        NhanVien nvien = new NhanVien();
        try {
            String sql = String.format("select * \n" +
                                    "from NHANVIEN, TAIKHOAN\n" +
                                    "where NHANVIEN.NV_ID = TAIKHOAN.TK_NV and TK_ID = %d", tk.getTK_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                nvien.setNV_DIACHI(rs.getString("NV_DIACHI"));
                nvien.setNV_GIOITINH(rs.getBoolean("NV_GIOITINH"));
                nvien.setNV_ID(rs.getString("NV_ID"));
                nvien.setNV_IMG(rs.getString("NV_IMG"));
                nvien.setNV_NGAYSINH(rs.getString("NV_NGAYSINH"));
                nvien.setNV_SDT(rs.getString("NV_SDT"));
                nvien.setNV_TEN(rs.getString("NV_TEN"));
                nvien.setNV_TINHTRANG(rs.getString("NV_TINHTRANG"));
                nvien.setQA_DIACHI(rs.getString("QA_DIACHI"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return nvien;
    }
     
    public static ArrayList<NhanVien> layDanhSachNhanVienDauBep() {
        ArrayList<NhanVien> dsNv = new ArrayList<>();
   
        try {
            String sql = "select * \n" +
                        "from TAIKHOAN, NHANVIEN\n" +
                        "WHERE TK_NGUOIDUNG = (Select NG_ID from NGUOIDUNG where NG_PHONGBAN = N'Đầu bếp')\n" +
                        "and TAIKHOAN.TK_NV = NHANVIEN.NV_ID";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setNV_ID(rs.getString("NV_ID"));
                nv.setNV_TEN(rs.getString("NV_TEN"));
             
                dsNv.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsNv;
    }
}
