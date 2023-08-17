/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quyen;

/**
 *
 * @author VU HOANG
 */
public class QuyenDAO {
    public static Integer layMaQuyenTiepTheo(){
        Integer kq = 0;
        try {
            String sql = "select top 1 QUYEN_ID\n" +
                    "from QUYEN\n" +
                    "order by QUYEN_ID desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = rs.getInt("QUYEN_ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq + 1;
    }
    
    public static Boolean kiemTraDaTonTaiQuyen(Quyen q){
        Boolean kq = false;
        try {
            String sql = String.format("Select *\n" +
                    "from QUYEN\n" +
                    "where DIENGIAI = '%s'", q.getDIENGIAI());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq = true;
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static Boolean themQuyen(Quyen q){
        Boolean kq = false;
            String sql = String.format("insert into QUYEN"
                    + " values(%d, '%s')", QuyenDAO.layMaQuyenTiepTheo(), q.getDIENGIAI());
            DataService ds = new DataService();
            ds.open();
            int n = ds.executeUpdate(sql);
            if(n > 0){
                kq = true;
            }
        
        return kq;
    }
    
    public static Quyen layQuyenTheoDienGiai(Quyen q){
        Quyen kq = new Quyen();
        try {
            String sql = String.format("Select *"
                    + " from Quyen"
                    + " where DIENGIAI = '%s'", q.getDIENGIAI());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while(rs.next()){
                kq.setQUYEN_ID(rs.getInt("QUYEN_ID"));
                kq.setDIENGIAI(rs.getString("DIENGIAI"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
}
