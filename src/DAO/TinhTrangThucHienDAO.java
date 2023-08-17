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
import model.TinhTrang;

/**
 *
 * @author VU HOANG
 */
public class TinhTrangThucHienDAO {
    public static ArrayList<TinhTrang> layDanhSachTinhTrangThucHien() {
        ArrayList<TinhTrang> dsTT = new ArrayList<>();
   
        try {
            String sql = "select * \n" +
                        "from TINHTRANG_THUCHIEN";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                TinhTrang tt = new TinhTrang();
                tt.setMA_TT(rs.getInt("MA_TT"));
                tt.setMA_DIENGIAI(rs.getString("MA_DIENGIAI"));
             
                dsTT.add(tt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dsTT;
    }
    
    public static TinhTrang layDanhSachTinhTrangTheoMaTinhTrang(TinhTrang tt) {
        TinhTrang ttrang = new TinhTrang();
        try {
            String sql = String.format("select * \n" +
                        "from TINHTRANG_THUCHIEN\n" +
                        "where MA_TT = %d", tt.getMA_TT());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()) {
                ttrang.setMA_TT(rs.getInt("MA_TT"));
                ttrang.setMA_DIENGIAI(rs.getString("MA_DIENGIAI"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ttrang;
    }
}
