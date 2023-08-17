/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.util.ArrayList;
import model.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiDung;
import model.NguyenLieu;
import model.PhieuDat;

/**
 *
 * @author VU HOANG
 */
public class NhaCungCapDAO {
     public static ArrayList<NhaCungCap> layDanhSachNhaCungCap() {
        ArrayList<NhaCungCap> dsNcc = new ArrayList<>();
        try {
            String sql = "Select * from NHACUNGCAP";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setNCC_ID(rs.getInt("NCC_ID"));
                ncc.setNCC_TEN(rs.getString("NCC_TEN"));
                ncc.setNCC_SDT(rs.getString("NCC_SDT"));
                ncc.setNCC_DIACHI(rs.getString("NCC_DIACHI"));

                dsNcc.add(ncc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsNcc;
    }
     
     
//     public static ArrayList<NhaCungCap> layDanhSachNhaCungCap() {
//        ArrayList<NhaCungCap> dsNcc = new ArrayList<>();
//        try {
//            String sql = "Select * from NHACUNGCAP";
//            DataService ds = new DataService(main.main.nguoiDung);
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()) {
//                NhaCungCap ncc = new NhaCungCap();
//                ncc.setNCC_ID(rs.getInt("NCC_ID"));
//                ncc.setNCC_TEN(rs.getString("NCC_TEN"));
//                ncc.setNCC_SDT(rs.getString("NCC_SDT"));
//                ncc.setNCC_DIACHI(rs.getString("NCC_DIACHI"));
//
//                dsNcc.add(ncc);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        return dsNcc;
//    }
     
     public static String layTenNhaCungCapTheoMaPhieuDat(PhieuDat pd){
         String kq = "";
         try {
            String sql = "select NCC_TEN\n" +
                        " from NHACUNGCAP ncc, PHIEUDAT pd\n" +
                        " where ncc.NCC_ID = pd.NCC_ID and PD_ID = " + pd.getPD_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                kq = rs.getString("NCC_TEN");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return kq;
     }
     
      public static ArrayList<NhaCungCap> timKiemTheoTen (NhaCungCap ncc) {
        ArrayList<NhaCungCap> dsNcc = new ArrayList<NhaCungCap>();
        try {
            String sql = "select * from NhaCungCap where NCC_TEN like N'%" + ncc.getNCC_TEN() + "%'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NhaCungCap NCc = new NhaCungCap();
                NCc.setNCC_ID(rs.getInt("NCC_ID"));
                NCc.setNCC_DIACHI(rs.getString("NCC_DIACHI"));
                NCc.setNCC_TEN(rs.getString("NCC_TEN"));
                NCc.setNCC_SDT(rs.getString("NCC_SDT"));
                dsNcc.add(NCc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNcc;
    }

    public static boolean xoaNhaCungCap(NhaCungCap ncc) {
        boolean kq = false;
        String sql = String.format("Delete from NHACUNGCAP where NCC_ID = %d", ncc.getNCC_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean themNhaCungCap(NhaCungCap ncc) {
       boolean kq = false;
        String sql = "INSERT INTO NHACUNGCAP (NCC_ID, NCC_TEN, NCC_DIACHI, NCC_SDT) VALUES ("+ncc.getNCC_ID()+", N'"+ncc.getNCC_TEN()+"', N'"+ncc.getNCC_DIACHI()+"', '"+ncc.getNCC_SDT()+"')";
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }

    public static boolean capNhatNhaCungCap(NhaCungCap ncc) {
        boolean kq = false;
        String sql = String.format("UPDATE NHACUNGCAP SET NCC_TEN = N'"+ncc.getNCC_TEN()+"', NCC_SDT = '"+ncc.getNCC_SDT()+"', NCC_DIACHI = N'"+ncc.getNCC_DIACHI()+"' WHERE NCC_ID = "+ncc.getNCC_ID()+"");
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static Integer layMaNhaCungCapTiepTheo(){
        Integer id = 0;
        try {
             String sql = "select TOP 1 NCC_ID\n" +
                     "from NHACUNGCAP\n" +
                     "order by NCC_ID desc";
             DataService ds = new DataService();
             ds.open();
             ResultSet rs = ds.executeQuery(sql);
             while(rs.next()){
                 id = rs.getInt("NCC_ID");
             }
         } catch (SQLException ex) {
             Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        return id + 1;
    }
    
    public static NhaCungCap timKiemTheoNguyenLieu (NguyenLieu nl) {
        NhaCungCap ncc = new NhaCungCap();
        try {
            String sql = "select ncc.NCC_ID, NCC_TEN\n" +
                        "from NHACUNGCAP ncc, NGUYENLIEU nl\n" +
                        "where ncc.NCC_ID = nl.NCC_ID and nl.NL_ID = " + nl.getNL_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                ncc.setNCC_ID(rs.getInt("NCC_ID"));
                ncc.setNCC_TEN(rs.getString("NCC_TEN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ncc;
    }
}
