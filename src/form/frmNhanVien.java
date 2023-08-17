/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;
import DAO.NhanVienDAO;
import DAO.PhanQuyenDAO;
import java.awt.Color;
import javax.swing.JOptionPane;
import model.NhanVien;
import swing.table;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.TaiKhoan;
import swing.scrollbar;

/**
 *
 * @author NGOC TUYEN
 */
public class frmNhanVien extends javax.swing.JPanel {

    Vector data = new Vector();
    TaiKhoan tkhoan = main.main.tkhoan;
    /**
     * Creates new form frmNhanVien
     */
    public frmNhanVien() {
        initComponents();
//        txtSearch2.setText("Tìm kiếm theo tên ... ");

        spTable.setVerticalScrollBar(new scrollbar());
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spTable.setVerticalScrollBar(new scrollbar());
        
        txt_ID.setText("");
        txtName.setText("");
        txtSex.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txt_SDT.setText("");
        loadData();

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
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
                NhanVien a = new NhanVien();
                a.setNV_TEN(txtSearch.getText().trim());
                ArrayList<NhanVien> dsNv = NhanVienDAO.timKiemTen(a);
                for (NhanVien nv : dsNv) {
                    Vector info = new Vector();
                    info.add(nv.getNV_ID());
                    info.add(nv.getNV_TEN());
                    if(nv.getNV_GIOITINH() == true){
                        info.add("Nam");
                    } else {
                        info.add("Nữ");
                    }
                    info.add(nv.getNV_NGAYSINH());
                    info.add(nv.getNV_DIACHI());
                    info.add(nv.getNV_SDT());
                    info.add(nv.getNV_TINHTRANG());
                    data.add(info);
                    tblNhanVien.addRow(info);
                }
            }
                 
        });
             
    }
     public void clear() {
        DefaultTableModel dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.setRowCount(0);
    }
     
     public void loadData() {
         clear();
         data.removeAllElements();
        ArrayList<NhanVien> dsNv = NhanVienDAO.layDanhSachNhanVien();
        for (NhanVien nv : dsNv) {
            Vector info = new Vector();
            info.add(nv.getNV_ID());
            info.add(nv.getNV_TEN());
            if(nv.getNV_GIOITINH() == true){
                info.add("Nam");
            } else {
                info.add("Nữ");
            }
            info.add(nv.getNV_NGAYSINH());
            info.add(nv.getNV_DIACHI());
            info.add(nv.getNV_SDT());
            info.add(nv.getNV_TINHTRANG());
            tblNhanVien.addRow(info);
            data.add(info);
        }
    }
     public void showDetail(int pos) {
        Vector info = (Vector) data.get(tblNhanVien.getSelectedRow());
        txt_ID.setText(info.get(0).toString());
        txtName.setText(info.get(1).toString());
        txtSex.setText(info.get(2).toString());
        txtNgaySinh.setText(info.get(3).toString());
        txtDiaChi.setText(info.get(4).toString());
        txt_SDT.setText(info.get(5).toString());
        txtTinhTrang.setText(info.get(6).toString());
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
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSex = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTinhTrang = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        panelBorder1 = new swing.panelBorder();
        spTable = new javax.swing.JScrollPane();
        tblNhanVien = new swing.table();

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

        btnthem.setBackground(new java.awt.Color(255, 153, 102));
        btnthem.setForeground(new java.awt.Color(0, 51, 51));
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(255, 153, 102));
        btnxoa.setForeground(new java.awt.Color(0, 51, 51));
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(255, 153, 102));
        btnsua.setForeground(new java.awt.Color(0, 51, 51));
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        jLabel2.setText("ID :");

        jLabel4.setText("Họ Tên :");

        jLabel5.setText("Giới Tính :");

        jLabel6.setText("Ngày Sinh :");

        jLabel7.setText("Địa chỉ :");

        jLabel8.setText("SDT :");

        jLabel9.setText("Tình Trạng :");

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Tình trạng"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        tblNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblNhanVienKeyPressed(evt);
            }
        });
        spTable.setViewportView(tblNhanVien);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(15, 15, 15)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ID)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtName))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(44, 44, 44)
                                .addComponent(txt_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtSex)
                                .addGap(194, 194, 194))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnthem)
                                .addGap(45, 45, 45)
                                .addComponent(btnxoa)
                                .addGap(57, 57, 57)
                                .addComponent(btnsua)
                                .addGap(0, 62, Short.MAX_VALUE)))))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnxoa)
                    .addComponent(btnsua)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        if(!PhanQuyenDAO.kiemTraCoQuyenThaoTacNhanVien(tkhoan)){
            JOptionPane.showMessageDialog(this, "Bạn không có quyền thao tác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (txtName.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên để thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        loadData();
        int lastIndex = tblNhanVien.getRowCount();
        try{
            NhanVien nv = new NhanVien();
            nv.setNV_ID(txt_ID.getText());
            nv.setNV_TEN(txtName.getText().trim());
            nv.setNV_GIOITINH(Boolean.valueOf(txtSex.getText()));
            nv.setNV_NGAYSINH(txtNgaySinh.getText());
            nv.setNV_DIACHI(txtDiaChi.getText().trim());
            nv.setNV_SDT(txt_SDT.getText());
            nv.setNV_TINHTRANG(txtTinhTrang.getText());
            NhanVienDAO.themNhanVien(nv);
        loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thêm được nhân viên ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        if(!PhanQuyenDAO.kiemTraCoQuyenThaoTacNhanVien(tkhoan)){
            JOptionPane.showMessageDialog(this, "Bạn không có quyền thao tác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int pos = tblNhanVien.getSelectedRow();
        if (pos >= 0 && JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này", "Thông báo", JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
            NhanVien nv = new NhanVien();
            String selectedID = tblNhanVien.getValueAt(pos, 0).toString();
            nv.setNV_ID(selectedID);
            if (NhanVienDAO.xoaNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
        }
        loadData();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        if(!PhanQuyenDAO.kiemTraCoQuyenThaoTacNhanVien(tkhoan)){
            JOptionPane.showMessageDialog(this, "Bạn không có quyền thao tác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        NhanVien nv = new NhanVien();
            nv.setNV_ID(txt_ID.getText());
            nv.setNV_TEN(txtName.getText().trim());
            if(txtSex.getText() == "Nam"){
                nv.setNV_GIOITINH(true);
            } else {
                nv.setNV_GIOITINH(false);
            }
            nv.setNV_NGAYSINH(txtNgaySinh.getText());
            nv.setNV_DIACHI(txtDiaChi.getText().trim());
            nv.setNV_SDT(txt_SDT.getText());
            nv.setNV_TINHTRANG(txtTinhTrang.getText());
        if (NhanVienDAO.capNhatNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên  thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        loadData();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        // TODO add your handling code here:
        showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_jPanel1KeyReleased

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        int pos = tblNhanVien.getSelectedRow();
        showDetail(pos);
        txt_ID.setEnabled(true);
    }//GEN-LAST:event_jPanel1MousePressed

    private void tblNhanVienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNhanVienKeyPressed
       showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_tblNhanVienKeyPressed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        showDetail(((table) evt.getSource()).getSelectedRow());
    }//GEN-LAST:event_tblNhanVienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private swing.panelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private swing.table tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSex;
    private javax.swing.JTextField txtTinhTrang;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables

    
}