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
import model.Ban;
import model.OrderMon;

/**
 *
 * @author VU HOANG
 */
public class OrderMonDAO {
    public static ArrayList<OrderMon> layDanhOrderHoaDonChuaThanhToan() {
        ArrayList<OrderMon> dsOrder = new ArrayList<>();
        try {
            String sql = "select B_SOBAN, M_TEN, O_THOIGIAN, O_SOLUONG,O_GHICHU, O.TT_MON, O.NV_ID\n" +
                        "from [ORDER] o, HOADON hd\n" +
                        "where o.HD_ID = hd.HD_ID and hd.HD_TrangThai = 0 order by O_THOIGIAN desc";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                OrderMon o = new OrderMon();
                o.setB_SOBAN(rs.getInt("B_SOBAN"));
                o.setM_TEN(rs.getString("M_TEN"));
                o.setO_THOIGIAN(rs.getString("O_THOIGIAN"));
                o.setO_SOLUONG(rs.getInt("O_SOLUONG"));
                if(rs.getString("O_GHICHU") == null){
                    o.setO_GHICHU("");
                } else {
                    o.setO_GHICHU(rs.getString("O_GHICHU"));
                }
                o.setTT_MON(rs.getInt("TT_MON"));
                if(rs.getString("NV_ID") == null){
                    o.setNV_ID("");
                } else {
                    o.setNV_ID(rs.getString("NV_ID"));
                }

                dsOrder.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsOrder;
    }
    
    public static ArrayList<OrderMon> layDanhOrderHoaDonChuaThanhToanTheoBan(Ban b) {
        ArrayList<OrderMon> dsOrder = new ArrayList<>();
        try {
            String sql = "select B_SOBAN, M_TEN, O_THOIGIAN, O_SOLUONG,O_GHICHU, O.TT_MON, O.NV_ID\n" +
                        "from [ORDER] o, HOADON hd\n" +
                        "where o.HD_ID = hd.HD_ID and hd.HD_TrangThai = 0 and B_SOBAN = " + b.getB_SOBAN();
            DataService ds = new DataService();
            System.out.println(sql);
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                OrderMon o = new OrderMon();
                o.setB_SOBAN(rs.getInt("B_SOBAN"));
                o.setM_TEN(rs.getString("M_TEN"));
                o.setO_THOIGIAN(rs.getString("O_THOIGIAN"));
                o.setO_SOLUONG(rs.getInt("O_SOLUONG"));
                if(rs.getString("O_GHICHU") == null){
                    o.setO_GHICHU("");
                } else {
                    o.setO_GHICHU(rs.getString("O_GHICHU"));
                }
                o.setTT_MON(rs.getInt("TT_MON"));
                if(rs.getString("NV_ID") == null){
                    o.setNV_ID("");
                } else {
                    o.setNV_ID(rs.getString("NV_ID"));
                }

                dsOrder.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsOrder;
    }
    
    
}
