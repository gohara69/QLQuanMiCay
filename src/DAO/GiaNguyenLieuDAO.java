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
import model.GiaNguyenLieu;
import model.NguyenLieu;
import model.NhomQuyen;

/**
 *
 * @author VU HOANG
 */
public class GiaNguyenLieuDAO {
    public static ArrayList<GiaNguyenLieu> layDanhSachGiaNguyenLieu() {
        ArrayList<GiaNguyenLieu> dsGia = new ArrayList<>();
        try {
            String sql = "select *\n" +
                        "from GIANGUYENLIEU";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                GiaNguyenLieu nquyen = new GiaNguyenLieu();
                nquyen.setNGAYTHAYDOI(rs.getString("NGAYTHAYDOI"));
                nquyen.setNL_GIA(rs.getFloat("NL_GIA"));
                nquyen.setNL_ID(rs.getInt("NL_ID"));
                nquyen.setNV_ID(rs.getString("NV_ID"));

                dsGia.add(nquyen);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsGia;
    }
    
    public static ArrayList<GiaNguyenLieu> layDanhSachGiaNguyenLieuTheoTen(NguyenLieu nl) {
        ArrayList<GiaNguyenLieu> dsGia = new ArrayList<>();
        try {
            String sql = "select nl.NL_ID, NL_GIA, NGAYTHAYDOI, NV_ID\n" +
                        "from NGUYENLIEU nl, GIANGUYENLIEU gia\n" +
                        "where nl.NL_ID = gia.NL_ID and NL_TEN like N'%" + nl.getNL_TEN() + "%'" +
                        " order by NGAYTHAYDOI DESC";
            System.out.println(sql);
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                GiaNguyenLieu nquyen = new GiaNguyenLieu();
                nquyen.setNGAYTHAYDOI(rs.getString("NGAYTHAYDOI"));
                nquyen.setNL_GIA(rs.getFloat("NL_GIA"));
                nquyen.setNL_ID(rs.getInt("NL_ID"));
                nquyen.setNV_ID(rs.getString("NV_ID"));

                dsGia.add(nquyen);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsGia;
    }
}
