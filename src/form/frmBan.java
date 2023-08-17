/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import DAO.BanDAO;
import DAO.HoaDonDAO;
import DAO.OrderDAO;
import DAO.TaiKhoanDAO;
import main.main;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import main.main;
import model.Ban;
import model.HoaDon;
import model.Mon;
import model.TaiKhoan;

/**
 *
 * @author VU HOANG
 */
public class frmBan extends javax.swing.JPanel {

    /**
     * Creates new form frmBan
     */
    public static int saveSoBan;
    public static int saveHoaDon;
    public Component[] components;
    public static ArrayList<Ban> dsBan = new ArrayList<Ban>();
    public frmBan() {
        initComponents();
        insertButtonTable();
        int soBan = BanDAO.getQuantityTable();
        for(int i = 0 ; i < soBan ; i++){
            int hd = HoaDonDAO.layMaHoaDonTheoBan(i + 1);
            dsBan.add(new Ban(i + 1, hd));
        }
        components = this.jPanel1.getComponents();
    }

    public JButton createButtonTable(int numTable, String srcImg) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(srcImg);
        Font font = new Font("Segoe", Font.BOLD, 18);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIcon(icon);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(font);
        button.setIconTextGap(10);
        button.setText("Bàn số " + numTable);
        return button;
    }

    public int catChuoiLaySoBan(String textButtonBan) {
        String soBan = "";
        for (int i = 7; i < textButtonBan.length(); i++) {
            soBan += textButtonBan.charAt(i);
        }
        return Integer.valueOf(soBan);
    }

    public HoaDon createHoaDon(String textButtonBan) {
        HoaDon hd = new HoaDon();
        TaiKhoan tk = main.tkhoan;
        String maNVLapHD = TaiKhoanDAO.layMaNVLapHD(tk.getTK_ID());
        LocalDate date = LocalDate.now();

        String textBan = textButtonBan;
        int lengthTextBan = textBan.length();
        String tachLaySoBan = textBan.substring(7, lengthTextBan);
        int soBan = Integer.valueOf(tachLaySoBan);
        int maHoaDon = HoaDonDAO.layMaHoaDonTiepTheo();
        saveHoaDon = maHoaDon;
        hd.setMaHD(maHoaDon);
        hd.setSoBan(soBan);
        hd.setMaNV(maNVLapHD);
        hd.setThanhTien(0);
        hd.setNgayXuatHD(date.toString());
        hd.setTrangThai(false);
        return hd;
    }

    public void insertButtonTable() {
        ArrayList<Integer> ds_ban_empty = DAO.BanDAO.getListTableEmpty();
        ArrayList<Integer> ds_ban_not_empty = DAO.BanDAO.getListTableNotEmpty();
        int length = DAO.BanDAO.getQuantityTable();
        String srcImg = "";
        int i = 0, soBan = 0;
        boolean check = false;
        for (int j = 0; j < ds_ban_not_empty.size(); j++) {
            ds_ban_empty.add(0);
        }
        while (i < length) {
            soBan++;
            if (soBan == ds_ban_empty.get(i)) {
                srcImg = "./src/icon/table_empty.png";
                check = false;
            } else {
                srcImg = "./src/icon/table_full.png";
                check = true;
                length--;
            }
            if (!check) {
                i++;
            }
            JButton button = createButtonTable(soBan, srcImg);
            jPanel1.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fileNameIconTableFull = "./src/icon/table_full.png";
                    ImageIcon icon = (ImageIcon) button.getIcon();
                    String fileNameIconTable = icon.getDescription();
                    saveSoBan = catChuoiLaySoBan(button.getText());
                    
                    frmChonMon form = new frmChonMon();
                    form.setLocation(310, 85);
                    form.setVisible(true);
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.GridLayout(4, 5, 15, 15));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/grid.png"))); // NOI18N
        jButton1.setText("Tất Cả");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(10);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/empty.png"))); // NOI18N
        jButton5.setText("Bàn Trống");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setIconTextGap(10);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user-profile.png"))); // NOI18N
        jButton6.setText("Bàn Có Khách");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setIconTextGap(10);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        jButton7.setText("Chỉnh Sửa");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setIconTextGap(10);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jPanel1.removeAll();
        for (Component component : components) {
            ImageIcon icon = (ImageIcon)((JButton) component).getIcon();
            String fileNameImgTable = icon.getDescription();
            if(fileNameImgTable.equals("./src/icon/table_full.png")){
                jPanel1.add(component);
            }
        }
        jPanel1.updateUI();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        frmChinhSuaBan frm = new frmChinhSuaBan();
        frm.setVisible(true);
        main m = new main();
        frm.setLocation(320, 82);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel1.removeAll();
        insertButtonTable();
        jPanel1.updateUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jPanel1.removeAll();
        for (Component component : components) {
            ImageIcon icon = (ImageIcon)((JButton) component).getIcon();
            String fileNameImgTable = icon.getDescription();
            if(fileNameImgTable.equals("./src/icon/table_empty.png")){
                jPanel1.add(component);
            }
        }
        jPanel1.updateUI();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
