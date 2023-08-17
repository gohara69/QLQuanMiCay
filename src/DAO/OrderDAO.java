/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataService.DataService;
import form.frmChonMon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ban;
import model.HoaDon;
import model.Mon;
import model.Order;
import model.ModelButtonFood;

/**
 *
 * @author PC
 */
public class OrderDAO {
    public static boolean addOrder(Mon mon, int soLuong, int id_hoaDon, String ghiChu) {
        boolean kq = false;
        String sql = "insert into [ORDER] (M_TEN, HD_ID, O_SOLUONG, O_GIA, O_GHICHU) "
                + "values (N'"+mon.getTenMon()+"', "+id_hoaDon+", "+soLuong+", "+mon.getGiaMon()+", N'"+ghiChu+"')";
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static boolean removeOrder(String tenMon, int maHoaDon) {
        boolean kq = false;
        String sql = "delete from [ORDER] Where M_TEN = N'"+tenMon+"' and HD_ID = " + maHoaDon;
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static ArrayList<ModelButtonFood> getListFoodOrdered(int soBan){
        ArrayList<ModelButtonFood> ds_order = new ArrayList<ModelButtonFood>();
        try {
            String sql = "select * from [ORDER] od, HOADON hd, MON m where od.HD_ID = hd.HD_ID and od.M_TEN = m.M_TEN and hd.B_SOBAN = "+soBan+" and hd.HD_TrangThai = 'False'";
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                ModelButtonFood model = new ModelButtonFood();
                model.setTenMon(rs.getNString("M_TEN"));
                model.setSrcAnh(rs.getString("M_IMG"));
                model.setSoLuong(rs.getInt("O_SOLUONG"));
                model.setGia(rs.getInt("M_GIA"));
                ds_order.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds_order;
    }
    
    public static boolean themMoiOrder(Order o, HoaDon hd) {
        boolean kq = false;
        String sql = String.format("insert into [ORDER] values (N'%s', %d, %d, %d, N'%s', null, 1, 1, GETDATE())", o.getTenMon(), hd.getMaHD(), o.getSoLuong(), o.getGiaTien(), o.getO_GHICHU());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
    
    public static ArrayList<Order> layOrderCuaHoaDon(HoaDon hd){
        ArrayList<Order> dsOrder = new ArrayList<>();
        try {
            String sql = "select * from [ORDER] where HD_ID = " + hd.getMaHD();
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Order o = new Order();
                o.setTenMon(rs.getNString("M_TEN"));
                o.setGiaTien(rs.getInt("O_GIA"));
                o.setIdHoaDon(rs.getInt("HD_ID"));
                o.setNV_ID(rs.getString("NV_ID"));
                o.setO_GHICHU(rs.getString("O_GHICHU"));
                o.setO_THOIGIAN(rs.getString("O_THOIGIAN"));
                o.setSoLuong(rs.getInt("O_SOLUONG"));
                o.setTT_MON(rs.getInt("TT_MON"));
                o.setTT_THUCHIEN(rs.getInt("TT_THUCHIEN"));
                
                dsOrder.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsOrder;
    }
    
    public static boolean kiemTraTonTaiOrderVoiTenMon(HoaDon hd, Mon m){
        boolean kq = false;
        try {
            String sql = String.format("select *\n" +
                        "from [Order]\n" +
                        "where HD_ID = %d and M_TEN = N'%s'", hd.getMaHD(), m.getTenMon());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                kq = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public static Integer laySoLuongOrder1MonTheoHoaDon(HoaDon hd, Mon m){
        Integer kq = 0;
        try {
            String sql = String.format("select M_TEN, SUM(O_SOLUONG) as 'O_SOLUONG'\n" +
                                        "from [Order]\n" +
                                        "where HD_ID = %d and M_TEN = N'%s'\n" +
                                        "group by M_TEN", hd.getMaHD(), m.getTenMon());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                kq = rs.getInt("O_SOLUONG");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
     public static ArrayList<Order> layOrderDatCuaHoaDon(HoaDon hd){
        ArrayList<Order> dsOrder = new ArrayList<>();
        try {
            String sql = String.format("select M_TEN, Sum(O_SOLUONG) as 'O_SOLUONG', Sum(O_GIA) as 'O_GIA', O_GHICHU\n" +
                                        "from [Order]\n" +
                                        "where HD_ID = %d\n" +
                                        "group by M_TEN, O_GHICHU", hd.getMaHD());
            DataService ds = new DataService();
            ds.open();
            ResultSet rs = ds.executeQuery(sql);
            while (rs.next()) {
                Order o = new Order();
                o.setTenMon(rs.getString("M_TEN"));
                o.setGiaTien(rs.getInt("O_GIA"));
                o.setSoLuong(rs.getInt("O_SOLUONG"));
                o.setO_GHICHU(rs.getString("O_GHICHU"));
                
                dsOrder.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsOrder;
    }
     
      public static boolean updateOrder(Order order) {
        boolean kq = false;
        String sql = String.format("UPDATE [ORDER]\n" +
            "set NV_ID = '%s'\n" +
            "where M_TEN = N'%s' and O_THOIGIAN = '%s'", order.getNV_ID(), order.getTenMon(), order.getO_THOIGIAN());
        DataService ds = new DataService();
        ds.open();
        int n = ds.executeUpdate(sql);
        if(n == 1){
            kq = true;
        }
        ds.close();
        return kq;
    }
      
      public static boolean updateThucHienMonOrder(Order order) {
        boolean kq = false;
        String sql = String.format("UPDATE [ORDER]\n" +
            "set TT_MON = %d\n" +
            "where M_TEN = N'%s' and O_THOIGIAN = '%s'", order.getTT_MON(), order.getTenMon(), order.getO_THOIGIAN());
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
