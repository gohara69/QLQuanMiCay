/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import DAO.PhanQuyenDAO;
import DAO.TaiKhoanDAO;
import event.eventMenuSelected;
import form.frmBan;
import form.frmBep;
import form.frmChonMon;
import form.frmDangNhap;
import form.frmDanhMuc;
import form.frmDoanhThu;
import form.frmHoaDon;
import form.frmNguyenLieu;
import form.frmNhaCungCap;
import form.frmNhanVien;
import form.frmQuyen;
import form.frmTrangChu;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import model.NguoiDung;
import model.TaiKhoan;

/**
 *
 * @author VU HOANG
 */
public class main extends javax.swing.JFrame {

    public static TaiKhoan tkhoan;
    public static NguoiDung nguoiDung;
    /**
     * Creates new form main
     */
    public main() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        menu.initMoving(main.this);
        menu.addEventMenuSelected(new eventMenuSelected(){
            @Override
            public void selected(int index) {
                switch(index){
                    case 0:
                         setForm(new frmNguyenLieu());
                        break;
                    case 1:
                        setForm(new frmNhaCungCap());
                        break;
                    case 2:
                        setForm(new frmDanhMuc());
                        break;
                    case 3:
                        setForm(new frmNhanVien());
                        break;
                    case 4:
                        setForm(new frmBan());
                    case 5:
                        setForm(new frmBep());
                        break;
                    case 6:
                        setForm(new frmHoaDon());
                        break;
                    case 8:
                        setForm(new frmQuyen());
                    case 9:
                        setForm(new frmDoanhThu());
                        break;
                    case 10:
                        System.exit(0);
                        break;
                }
            }
            
        });
        setForm(new frmTrangChu(main.this));
    }
    
    public main(TaiKhoan tk) {
        tkhoan = tk;
        nguoiDung = TaiKhoanDAO.layNguoiDungTheoTaiKhoan(tkhoan);
        initComponents();
        setBackground(new Color(0,0,0,0));
        menu.initMoving(main.this);
        menu.addEventMenuSelected(new eventMenuSelected(){
            @Override
            public void selected(int index) {
                switch(index){
                    case 0:
                        if(PhanQuyenDAO.kiemTraCoQuyenXemNguyenLieu(tkhoan)){
                            setForm(new frmNguyenLieu());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                         break;
                    case 1:
                        if(PhanQuyenDAO.kiemTraCoQuyenXemNhaCungCap(tkhoan)){
                            setForm(new frmNhaCungCap());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 2:
                        if(PhanQuyenDAO.kiemTraCoQuyenXemDanhMuc(tkhoan)){
                            setForm(new frmDanhMuc());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 3:
                        if(PhanQuyenDAO.kiemTraCoQuyenXemNhanVien(tkhoan)){
                            setForm(new frmNhanVien());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 4:
                        setForm(new frmBan());
                        break;
                    case 5:
                        setForm(new frmBep());
                        break;
                    case 6:
                        setForm(new frmHoaDon());
                        break;
                    case 8:
                        if("sa".equals(tkhoan.getTK_NGUOIDUNG())){
                            setForm(new frmQuyen());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                         break;
                    case 9:
                        if(PhanQuyenDAO.kiemTraCoQuyenXemDoanhThu(tkhoan)){
                            setForm(new frmDoanhThu());
                        } else {
                            JOptionPane.showMessageDialog(main.this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 10:
                        frmDangNhap frm = new frmDangNhap();
                        setVisible(false);
                        dispose();
                        frm.setVisible(true);
                        break;
                }
            }
            
        });
        setForm(new frmTrangChu());
    }
    
    public void setForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.panelBorder();
        menu = new component.menu();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel mainPanel;
    public component.menu menu;
    private swing.panelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
