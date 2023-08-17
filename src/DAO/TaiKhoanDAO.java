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
import model.TaiKhoan;

/**
 *
 * @author VU HOANG
 */
public class TaiKhoanDAO {
    public static TaiKhoan layThongTinTaiKhoan(TaiKhoan tk){
        TaiKhoan tkoan = new TaiKhoan();
        try {
            String sql = String.format("Select * \n" +
                                        "from TAIKHOAN\n" +
                                        "where TK_TEN = '%s' and TK_MATKHAU = '%s'", tk.getTK_TEN(), tk.getTK_MATKHAU());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            int id;
            while(rs.next()) {
                tkoan.setTK_ID(rs.getInt("TK_ID"));
                tkoan.setTK_MATKHAU(rs.getString("TK_MATKHAU"));
                tkoan.setTK_NGUOIDUNG(rs.getString("TK_NGUOIDUNG"));
                tkoan.setTK_NV(rs.getString("TK_NV"));
                tkoan.setTK_TEN(rs.getString("TK_TEN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return tkoan;
    }
    
    public static NguoiDung layNguoiDungTheoTaiKhoan (TaiKhoan tk){
        NguoiDung ng = new NguoiDung();
        try {
            String sql = String.format("select *\n" +
                    "from NGUOIDUNG\n" +
                    "where NGUOIDUNG.NG_ID = '%s'", tk.getTK_NGUOIDUNG());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                ng.setNG_DIACHI(rs.getString("NG_DIACHI"));
                ng.setNG_ID(rs.getString("NG_ID"));
                ng.setNG_MATKHAU(rs.getString("NG_MATKHAU"));
                ng.setNG_PHONGBAN(rs.getString("NG_PHONGBAN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ng;
    }
    public static String layMaNVLapHD(int maTK){
        ArrayList<TaiKhoan> dstk = new ArrayList<>();
        String maNV = "";
        try {
            String sql = "select * from TAIKHOAN where TK_ID = " + maTK;
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                maNV = rs.getString("TK_NV");
            }
        } catch (Exception e) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return maNV;
    }
}
