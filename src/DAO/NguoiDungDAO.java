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
import model.DanhMuc;
import model.NguoiDung;

/**
 *
 * @author VU HOANG
 */
public class NguoiDungDAO {
    public static ArrayList<NguoiDung> layThongTinNguoiDung(){
        ArrayList<NguoiDung> dsnd = new ArrayList<NguoiDung>();
        try {
            String sql = "Select * from NGUOIDUNG where not (NG_PHONGBAN = N'Admin')";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setNG_DIACHI(rs.getString("NG_DIACHI"));
                nd.setNG_ID(rs.getString("NG_ID"));
                nd.setNG_PHONGBAN(rs.getString("NG_PHONGBAN"));
                
                dsnd.add(nd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsnd;
    }
}
