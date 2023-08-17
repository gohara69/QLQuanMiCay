/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.util.ArrayList;
import model.ChiTietPhieuDat;
import model.PhieuDat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VU HOANG
 */
public class ChiTietPhieuDatDAO {
//    public static boolean themCTPhieuNhap(ChiTietPhieuDat ctpd){
//        boolean kq = false;
//        String sql = String.format("insert into CTPHIEUDAT " + 
//                                    "values(%d, %d,%d,0)",ctpd.getPD_ID(), ctpd.getNL_ID(), ctpd.getSOLUONG());
//        DataService ds = new DataService();
//        ds.open();
//        int n = ds.executeUpdate(sql);
//        if(n == 1){
//            kq = true;
//        }
//        ds.close();
//        return kq;
//    }
    
    public static boolean themCTPhieuNhap(ChiTietPhieuDat ctpd){
        boolean kq = false;
        String sql = String.format("insert into CTPHIEUDAT " + 
                                    "values(%d, %d,%d,0)",ctpd.getPD_ID(), ctpd.getNL_ID(), ctpd.getSOLUONG());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static ArrayList<ChiTietPhieuDat> layDsChiTietPDCuaPhieuDat(PhieuDat pd){
        ArrayList<ChiTietPhieuDat> dsCTPD = new ArrayList<>();
        try {
            String sql = String.format("select *\n" +
                        "from CTPHIEUDAT\n" +
                        "where PD_ID = %d and SOLUONG != DAGIAO", pd.getPD_ID());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                ChiTietPhieuDat ctpd = new ChiTietPhieuDat();
                ctpd.setNL_ID(rs.getInt("NL_ID"));
                ctpd.setPD_ID(rs.getInt("PD_ID"));
                ctpd.setSOLUONG(rs.getInt("SOLUONG"));
                ctpd.setDAGIAO(rs.getInt("DAGIAO"));

                dsCTPD.add(ctpd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsCTPD;
    }
}
