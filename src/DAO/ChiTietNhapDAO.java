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
import model.ChiTietNhap;
import model.ChiTietPhieuDat;
import model.HoaDonNhap;
import model.Mon;
import model.PhieuDat;

/**
 *
 * @author VU HOANG
 */
public class ChiTietNhapDAO {
    public static boolean themCTPhieuNhap(ChiTietNhap ctn){
        boolean kq = false;
        String sql = String.format("insert into CHITIETNHAP " + 
                                    "values(%d, %d,%d,%f)",ctn.getNL_ID(), ctn.getHDN_ID(), ctn.getCTN_SOLUONG(), ctn.getCTN_GIA());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static ArrayList<ChiTietNhap> layDsChiTietNhapCuaHoaDonNhap(HoaDonNhap hdn){
        ArrayList<ChiTietNhap> dsCTN = new ArrayList<>();
        try {
            String sql = "select * from CHITIETNHAP where HDN_ID = " + hdn.getHDN_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                ChiTietNhap ctn = new ChiTietNhap();
                ctn.setCTN_GIA(rs.getFloat("CTN_GIA"));
                ctn.setCTN_SOLUONG(rs.getInt("CTN_SOLUONG"));
                ctn.setHDN_ID(rs.getInt("HDN_ID"));
                ctn.setNL_ID(rs.getInt("NL_ID"));
                dsCTN.add(ctn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsCTN;
    }
}
