/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import DAO.PhanQuyenDAO;
import javax.swing.JOptionPane;
import model.TaiKhoan;
import DAO.NguyenLieuDAO;
import DAO.NhaCungCapDAO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import swing.scrollbar;
import swing.table;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import main.main;
import static main.main.tkhoan;
import model.ComboBoxItem;
import model.NguyenLieu;
import model.NhaCungCap;



/**
 *
 * @author NGOC TUYEN
 */
public class frmNguyenLieu extends javax.swing.JPanel {
    TaiKhoan tk = main.tkhoan;
    Vector data = new Vector();
    Boolean isAdd = false;
    /**
     * Creates new form frmNguyenLieu
     */
    public frmNguyenLieu() {
        initComponents();
        spTable.setVerticalScrollBar(new scrollbar());
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spTable.setVerticalScrollBar(new scrollbar());
        
        txtid.setText("");
        txtTenNL.setText("");
        
        txtid.setHint("");
        txtTenNL.setHint("");
        txtDonGia.setHint("");
        txtSearch4.setHint("Tìm kiếm theo tên nguyên liệu ..");
        
        cboDonViTinh.removeAllItems();
        ArrayList<String> dsDVT = NguyenLieuDAO.layTatCaDonViTinh();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(String a : dsDVT){
            model.addElement(new ComboBoxItem(a, a));
        }
        cboDonViTinh.setModel(model);
        cboDonViTinh.setEditable(true);

        
        cboNhaCungCap.removeAllItems();
        ArrayList<NhaCungCap> dsNCC = NhaCungCapDAO.layDanhSachNhaCungCap();
        DefaultComboBoxModel modelNCC = new DefaultComboBoxModel();
        for(NhaCungCap a : dsNCC){
            modelNCC.addElement(new ComboBoxItem(a.getNCC_ID(), a.getNCC_TEN()));
        }
        cboNhaCungCap.setModel(modelNCC);
        cboNhaCungCap.setEditable(false);
        
        loadData();
        txtSearch4.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadDataFromSearch();
            }
                 @Override
            public void removeUpdate(DocumentEvent e) {
                loadDataFromSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadDataFromSearch();
            }
                public void loadDataFromSearch() {
                clear();
                data.removeAllElements();
                NguyenLieu a = new NguyenLieu();
                a.setNL_TEN(txtSearch4.getText().trim());
                ArrayList<NguyenLieu> dsNl = NguyenLieuDAO.timKiemTenNL(a);
                for (NguyenLieu nl : dsNl) {
                    Vector info = new Vector();
                    info.add(nl.getNL_ID());
                    info.add(nl.getNL_TEN());
                    info.add(nl.getNL_DONVITINH());
                    info.add(nl.getNL_GIA());
                    data.add(info);
                    tblNguyenLieu.addRow(info);
                 }
            }
        });
        tblNguyenLieu.setRowSelectionInterval(0, 0);
        showDetail(0);
    }
     public void clear() {
        DefaultTableModel dtm = (DefaultTableModel) tblNguyenLieu.getModel();
        dtm.setRowCount(0);
    }
     public void loadData() {
         clear();
         data.removeAllElements();
        ArrayList<NguyenLieu> dsNl = NguyenLieuDAO.layDanhSach();
        for (NguyenLieu nl : dsNl) {
            Vector info = new Vector();
            info.add(nl.getNL_ID());
            info.add(nl.getNL_TEN());
            info.add(nl.getNL_DONVITINH());
            info.add(nl.getNL_GIA());
            tblNguyenLieu.addRow(info);
            data.add(info);
        }
    }
     public void showDetail(int pos) {
        Vector info = (Vector) data.get(tblNguyenLieu.getSelectedRow());
        txtid.setText(info.get(0).toString());
        txtTenNL.setText(info.get(1).toString());
        cboDonViTinh.setSelectedItem(info.get(2).toString());
        NhaCungCap ncc = NhaCungCapDAO.timKiemTheoNguyenLieu(new NguyenLieu(Integer.parseInt(txtid.getText())));
        cboNhaCungCap.setSelectedItem(ncc.getNCC_TEN());
        txtDonGia.setText(info.get(3).toString());
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDatNguyenLieu = new swing.button();
        btnNhapNguyenLieu = new swing.button();
        btnHoaDonNhap = new swing.button();
        panelBorder3 = new swing.panelBorder();
        spTable = new javax.swing.JScrollPane();
        tblNguyenLieu = new swing.table();
        txtid = new swing.searchText();
        txtTenNL = new swing.searchText();
        txtSearch4 = new swing.searchText();
        btnCapNhatNguyenLieu = new swing.button();
        btnThem = new swing.button();
        btnLichSuGia = new swing.button();
        jLabel5 = new javax.swing.JLabel();
        cboNhaCungCap = new swing.combobox();
        cboDonViTinh = new swing.combobox();
        txtDonGia = new swing.searchText();

        panelBorder1.setPreferredSize(new java.awt.Dimension(910, 550));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Đơn Vị Tính :");
        panelBorder1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 205, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Đơn giá:");
        panelBorder1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Nguyên Liệu :");
        panelBorder1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));

        btnDatNguyenLieu.setText("Đặt nguyên liệu");
        btnDatNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatNguyenLieuActionPerformed(evt);
            }
        });
        panelBorder1.add(btnDatNguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, 37));

        btnNhapNguyenLieu.setText("Nhập nguyên liệu");
        btnNhapNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapNguyenLieuActionPerformed(evt);
            }
        });
        panelBorder1.add(btnNhapNguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, 37));

        btnHoaDonNhap.setText("Hóa đơn nhập");
        btnHoaDonNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonNhapActionPerformed(evt);
            }
        });
        panelBorder1.add(btnHoaDonNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 37));

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        tblNguyenLieu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên nguyên liệu", "Đơn vị tính", "Đơn giá"
            }
        ));
        tblNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguyenLieuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNguyenLieuMousePressed(evt);
            }
        });
        tblNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblNguyenLieuKeyReleased(evt);
            }
        });
        spTable.setViewportView(tblNguyenLieu);

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelBorder1.add(panelBorder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 250, -1, 290));

        txtid.setEditable(false);
        panelBorder1.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 97, -1));

        txtTenNL.setEditable(false);
        panelBorder1.add(txtTenNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 270, 40));
        panelBorder1.add(txtSearch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 378, -1));

        btnCapNhatNguyenLieu.setText("Lưu");
        btnCapNhatNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNguyenLieuActionPerformed(evt);
            }
        });
        panelBorder1.add(btnCapNhatNguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 130, 40));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelBorder1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 140, 40));

        btnLichSuGia.setText("Lịch sử giá");
        btnLichSuGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichSuGiaActionPerformed(evt);
            }
        });
        panelBorder1.add(btnLichSuGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 120, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("ID :");
        panelBorder1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        cboNhaCungCap.setEditable(true);
        panelBorder1.add(cboNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 190, -1));

        cboDonViTinh.setEditable(true);
        panelBorder1.add(cboDonViTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 190, 40));
        panelBorder1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 270, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatNguyenLieuActionPerformed
        if(PhanQuyenDAO.kiemTraCoQuyenDatNguyenLieu(tk)){
            frmDatNguyenLieu frm = new frmDatNguyenLieu();
            frm.setVisible(true);
            frm.setLocation(320, 85);
        }
        else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDatNguyenLieuActionPerformed

    private void btnNhapNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapNguyenLieuActionPerformed
        if(PhanQuyenDAO.kiemTraCoQuyenNhapNguyenLieu(tk)){
            frmNhapNguyenLieu frm = new frmNhapNguyenLieu();
            frm.setVisible(true);
            frm.setLocation(320, 85);
        }
        else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnNhapNguyenLieuActionPerformed

    private void btnHoaDonNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonNhapActionPerformed
        if(PhanQuyenDAO.kiemTraCoQuyenXemHoaDonNhap(tk)){
            frmHoaDonNhap frm = new frmHoaDonNhap();
            frm.setVisible(true);
            frm.setLocation(320, 85);
        }
        else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnHoaDonNhapActionPerformed

    private void tblNguyenLieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNguyenLieuKeyReleased
        // TODO add your handling code here:
        showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_tblNguyenLieuKeyReleased

    private void tblNguyenLieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguyenLieuMousePressed
        // TODO add your handling code here:
        int pos = tblNguyenLieu.getSelectedRow();
        showDetail(pos);
        txtid.setEnabled(false);
    }//GEN-LAST:event_tblNguyenLieuMousePressed

    private void tblNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguyenLieuMouseClicked
        // TODO add your handling code here:
        showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_tblNguyenLieuMouseClicked

    private void btnCapNhatNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNguyenLieuActionPerformed
        if(!PhanQuyenDAO.kiemTraCoQuyenThaoTacNguyenLieu(main.tkhoan)){
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(txtTenNL.getText().trim().length() * txtDonGia.getText().trim().length() ==0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.OK_OPTION);
            return;
        }
        
        int donGia = 0;
        try{
            donGia = Integer.parseInt(txtDonGia.getText());
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số dương", "Thông báo", JOptionPane.OK_OPTION);
        }
        
        cboNhaCungCap.setEditable(false);
        NguyenLieu nl = new NguyenLieu();
        nl.setNL_ID(Integer.parseInt(txtid.getText()));
        nl.setNL_GIA(donGia);
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        nl.setNL_NCC(nccId);
        nl.setNL_TEN(txtTenNL.getText().trim());
        if(isAdd){
            try{
                NguyenLieuDAO.themNguyenLieu(nl);
                NguyenLieuDAO.themGiaNguyenLieu(nl);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thất bại", "Thông báo", JOptionPane.OK_OPTION);
            }
            JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thành công", "Thông báo", JOptionPane.OK_OPTION);
        } else {
            try{
                NguyenLieuDAO.updateGiaNguyenLieu(nl);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Cập nhật giá nguyên liệu thất bại", "Thông báo", JOptionPane.OK_OPTION);
            }
            JOptionPane.showMessageDialog(this, "Cập nhật giá nguyên liệu thành công", "Thông báo", JOptionPane.OK_OPTION);
        }
        txtTenNL.setEnabled(false);
        cboNhaCungCap.setEditable(true);
        isAdd = false;
        loadData();
    }//GEN-LAST:event_btnCapNhatNguyenLieuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(PhanQuyenDAO.kiemTraCoQuyenThaoTacNguyenLieu(main.tkhoan)){
            txtid.setText(NguyenLieuDAO.getLastNguyenLieuId() + "");
            txtTenNL.setEditable(true);
            txtTenNL.setText("");
            txtDonGia.setEnabled(true);
            cboNhaCungCap.setEditable(false);
            cboDonViTinh.setEnabled(true);
            cboDonViTinh.setEditable(false);
            txtDonGia.setText("");
            isAdd = true;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLichSuGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichSuGiaActionPerformed
        frmLichSuGia frm = new frmLichSuGia();
        frm.setVisible(true);
        frm.setLocation(320, 85);
    }//GEN-LAST:event_btnLichSuGiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.button btnCapNhatNguyenLieu;
    private swing.button btnDatNguyenLieu;
    private swing.button btnHoaDonNhap;
    private swing.button btnLichSuGia;
    private swing.button btnNhapNguyenLieu;
    private swing.button btnThem;
    private swing.combobox cboDonViTinh;
    private swing.combobox cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.panelBorder panelBorder1;
    private swing.panelBorder panelBorder3;
    private javax.swing.JScrollPane spTable;
    private swing.table tblNguyenLieu;
    private swing.searchText txtDonGia;
    private swing.searchText txtSearch4;
    private swing.searchText txtTenNL;
    private swing.searchText txtid;
    // End of variables declaration//GEN-END:variables
}
