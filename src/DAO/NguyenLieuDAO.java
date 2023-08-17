/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.util.ArrayList;
import model.NguyenLieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhaCungCap;

/**
 *
 * @author VU HOANG
 */
public class NguyenLieuDAO {
     public static ArrayList<NguyenLieu> layDanhSach() {
        ArrayList<NguyenLieu> dsNl = new ArrayList<>();
   
        try {
            String sql = "select nl.NL_ID, NL_TEN, NL_DONVITINH, NL_GIA, NL_SOLUONG\n" +
                        "from NGUYENLIEU nl, GIANGUYENLIEU gia\n" +
                        "where nl.NL_ID = gia.NL_ID";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(rs.getString("NL_TEN"));
                nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
                nl.setNL_GIA(rs.getFloat("NL_GIA"));
               
                dsNl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsNl;
    }
     public static ArrayList<NguyenLieu> timKiemTenNL (NguyenLieu nl) {
        ArrayList<NguyenLieu> dsNl = new ArrayList<NguyenLieu>();
        try {
            String sql = "select * from NGUYENLIEU where NL_TEN like N'%" + nl.getNL_TEN() + "%'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NguyenLieu Nl = new NguyenLieu();
                Nl.setNL_ID(rs.getInt("NL_ID"));
                Nl.setNL_TEN(rs.getString("NL_TEN"));
                Nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                Nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
                dsNl.add(Nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNl;
    }
//    public static ArrayList<NguyenLieu> searchIngredientById (NguyenLieu nl, int nccId) {
//        ArrayList<NguyenLieu> dsdm = new ArrayList<>();
//        try {
//            String sql = "select top 1 NGUYENLIEU.NL_ID, NL_TEN, NL_DONVITINH, NL_GIA\n" +
//                        "from NGUYENLIEU, GIANGUYENLIEU\n" +
//                        "where NGUYENLIEU.NL_ID = GIANGUYENLIEU.NL_ID and NGUYENLIEU.NL_ID = " + nl.getNL_ID()
//                        + " and NCC_ID = " + nccId + " order by NGAYTHAYDOI desc";
//            DataService ds = new DataService();
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()){
//                NguyenLieu nlieu = new NguyenLieu();
//                nlieu.setNL_ID(rs.getInt("NL_ID"));
//                nlieu.setNL_TEN(rs.getString("NL_TEN"));
//                nlieu.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
//                nlieu.setNL_GIA(rs.getFloat("NL_GIA"));
//                
//                dsdm.add(nlieu);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dsdm;
//    }
    
    public static ArrayList<NguyenLieu> searchIngredientById (NguyenLieu nl, int nccId) {
        ArrayList<NguyenLieu> dsdm = new ArrayList<>();
        try {
            String sql = "select top 1 NGUYENLIEU.NL_ID, NL_TEN, NL_DONVITINH, NL_GIA\n" +
                        "from NGUYENLIEU, GIANGUYENLIEU\n" +
                        "where NGUYENLIEU.NL_ID = GIANGUYENLIEU.NL_ID and NGUYENLIEU.NL_ID = " + nl.getNL_ID()
                        + " and NCC_ID = " + nccId + " order by NGAYTHAYDOI desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NguyenLieu nlieu = new NguyenLieu();
                nlieu.setNL_ID(rs.getInt("NL_ID"));
                nlieu.setNL_TEN(rs.getString("NL_TEN"));
                nlieu.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nlieu.setNL_GIA(rs.getFloat("NL_GIA"));
                
                dsdm.add(nlieu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsdm;
    }
    
    public static boolean isThisIngredientById (NguyenLieu nl) {
        ArrayList<NguyenLieu> dsdm = new ArrayList<>();
        try {
            String sql = "select * from NguyenLieu where NL_ID = " + nl.getNL_ID()+ "";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static NguyenLieu searchIngredientById (NguyenLieu nl) {
        NguyenLieu a = new NguyenLieu();
        try {
            String sql = "select nl.NL_ID, NL_TEN, NL_DONVITINH, NL_GIA \n" +
                        "from NGUYENLIEU nl, GIANGUYENLIEU gnl \n" +
                        "where nl.NL_ID = gnl.NL_ID and nl.NL_ID = " + nl.getNL_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                a.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                a.setNL_GIA(rs.getFloat("NL_GIA"));
                a.setNL_ID(rs.getInt("NL_ID"));
                a.setNL_TEN(rs.getString("NL_TEN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public static ArrayList<Integer> searchIngredientByNCCId (NhaCungCap ncc) {
        ArrayList<Integer> dsnl = new ArrayList<>();
        try {
            String sql = "select distinct NL_ID from NGUYENLIEU where NCC_ID = " + ncc.getNCC_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                dsnl.add(rs.getInt("NL_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
//    public static ArrayList<Integer> searchIngredientByNCCId (NhaCungCap ncc) {
//        ArrayList<Integer> dsnl = new ArrayList<>();
//        try {
//            String sql = "select distinct NL_ID from NGUYENLIEU where NCC_ID = " + ncc.getNCC_ID();
//            DataService ds = new DataService();
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()){
//                dsnl.add(rs.getInt("NL_ID"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dsnl;
//    }
    
//    public static ArrayList<NguyenLieu> searchIngredientNameByNCCId (NhaCungCap ncc) {
//        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
//        try {
//            String sql = "select distinct NGUYENLIEU.NL_ID ,NL_TEN from GIANGUYENLIEU, NGUYENLIEU where " + 
//                    "GIANGUYENLIEU.NL_ID = NGUYENLIEU.NL_ID and NCC_ID = " + ncc.getNCC_ID();
//            DataService ds = new DataService();
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()){
//                NguyenLieu nl = new NguyenLieu();
//                nl.setNL_ID(rs.getInt("NL_ID"));
//                nl.setNL_TEN(rs.getString("NL_TEN"));
//                
//                dsnl.add(nl);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dsnl;
//    }
    
    public static ArrayList<NguyenLieu> searchIngredientNameByNCCId (NhaCungCap ncc) {
        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
        try {
            String sql = "select distinct NGUYENLIEU.NL_ID ,NL_TEN from GIANGUYENLIEU, NGUYENLIEU where " + 
                    "GIANGUYENLIEU.NL_ID = NGUYENLIEU.NL_ID and NCC_ID = " + ncc.getNCC_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(rs.getString("NL_TEN"));
                
                dsnl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
    public static ArrayList<NguyenLieu> searchIngredientFullByNCCId (NhaCungCap ncc) {
        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
        try {
            String sql = "select nl.NL_ID, nl.NL_TEN, nl.NL_DONVITINH, nl.NL_SOLUONG\n" +
                        "from NGUYENLIEU nl, NHACUNGCAP ncc\n" +
                        "where nl.NCC_ID = ncc.NCC_ID and ncc.NCC_ID = " + ncc.getNCC_ID();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(rs.getString("NL_TEN"));
                nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
                
                dsnl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
    public static ArrayList<NguyenLieu> searchIngredientByNCCIdAndSoLuong (NhaCungCap ncc, int SoLuong) {
        ArrayList<NguyenLieu> dsnl = new ArrayList<>();
        try {
            String sql = String.format("select nl.NL_ID, nl.NL_TEN, nl.NL_DONVITINH, nl.NL_SOLUONG\n" +
                    "from NGUYENLIEU nl, NHACUNGCAP ncc\n" +
                    "where nl.NCC_ID = ncc.NCC_ID and ncc.NCC_ID = %d and nl.NL_SOLUONG <= %d", ncc.getNCC_ID(), SoLuong);
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                NguyenLieu nl = new NguyenLieu();
                nl.setNL_ID(rs.getInt("NL_ID"));
                nl.setNL_TEN(rs.getString("NL_TEN"));
                nl.setNL_DONVITINH(rs.getString("NL_DONVITINH"));
                nl.setNL_SOLUONG(rs.getInt("NL_SOLUONG"));
                
                dsnl.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
//     public static Float searchIngredientPriceById (NguyenLieu nl) {
//        ArrayList<Float> dsGia = new ArrayList<>();
//        try {
//            String sql = "select TOP 1 NL_GIA from GIANGUYENLIEU where NL_ID = " + nl.getNL_ID() + " order by NGAYTHAYDOI desc";
//            DataService ds = new DataService();
//            ds.open();
//            ResultSet rs = ds.executeQuery(sql);
//            while(rs.next()){
//                dsGia.add(rs.getFloat("NL_GIA"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dsGia.get(0);
//    }
     
     public static Float searchIngredientPriceById (NguyenLieu nl) {
        ArrayList<Float> dsGia = new ArrayList<>();
        try {
            String sql = "select TOP 1 NL_GIA from GIANGUYENLIEU where NL_ID = " + nl.getNL_ID() + " order by NGAYTHAYDOI desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                dsGia.add(rs.getFloat("NL_GIA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsGia.get(0);
    }
     
     public static Integer getLastNguyenLieuId () {
        int id = 0;
        try {
            String sql = "select TOP 1 NL_ID from NGUYENLIEU order by NL_ID desc";
            DataService ds = new DataService(main.main.nguoiDung);
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("NL_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    } 
     
    public static ArrayList<String> layTatCaDonViTinh () {
        ArrayList<String> dsnl = new ArrayList<>();
        try {
            String sql = String.format("select DISTINCT NL_DONVITINH\n" +
                                        "from NGUYENLIEU");
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                String dvt = rs.getString("NL_DONVITINH");
                
                dsnl.add(dvt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsnl;
    }
    
    public static boolean themNguyenLieu(NguyenLieu nlieu) {
        boolean kq = false;
        String sql = String.format("insert into NGUYENLIEU " + 
                                    "values(%d, N'%s', N'%s', 0, %d)", nlieu.getNL_ID(), nlieu.getNL_TEN(), nlieu.getNL_DONVITINH(), nlieu.getNL_NCC());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean themGiaNguyenLieu(NguyenLieu nlieu) {
        boolean kq = false;
        String sql = String.format("insert into GIANGUYENLIEU " + 
                                    "values(%d, GETDATE(), %f, '%s')", nlieu.getNL_ID(), nlieu.getNL_GIA(), main.main.tkhoan.getTK_NV());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean updateGiaNguyenLieu(NguyenLieu nlieu) {
        boolean kq = false;
        String sql = String.format("UPDATE GIANGUYENLIEU\n" +
                                    "set NGAYTHAYDOI = GETDATE(), NL_GIA = %f, NV_ID = '%s'\n" +
                                    "where NL_ID = %d", nlieu.getNL_GIA(), main.main.tkhoan.getTK_NV(), nlieu.getNL_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean updateSoLuongNguyenLieu(NguyenLieu nlieu) {
        boolean kq = false;
        String sql = String.format("update NGUYENLIEU\n" +
                    "set NL_SOLUONG = NL_SOLUONG - %d\n" +
                    "where NL_ID = %d", nlieu.getNL_SOLUONG(), nlieu.getNL_ID());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
}
