/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import DAO.ChiTietNhapDAO;
import DAO.ChiTietPhieuDatDAO;
import DAO.HoaDonNhapDAO;
import DAO.NguyenLieuDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.PhieuDatDAO;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.ChiTietNhap;
import model.ChiTietPhieuDat;
import model.ComboBoxItem;
import model.HoaDonNhap;
import model.NguyenLieu;
import model.NhanVien;
import model.PhieuDat;
import model.TaiKhoan;
import swing.scrollbar;
import swing.tableActionCellEditor;
import swing.tableActionEvent;
import swing.tableCellRenderer;

/**
 *
 * @author VU HOANG
 */
public class frmNhapNguyenLieu extends javax.swing.JFrame {

    Vector data = new Vector();
    ItemListener listener;
    TaiKhoan taiKhoan = main.main.tkhoan;
    NhanVien nhanVien = NhanVienDAO.layNhanVienTheoTaiKhoan(taiKhoan);
    TableModelListener modelTableListener = new TableModelListener(){
        @Override
        public void tableChanged(TableModelEvent e) {
            int row = tblNhapNguyenLieu.getSelectedRow();
            int col = tblNhapNguyenLieu.getSelectedColumn();
            if(col == 4){
                handleWhenEditColSoLuongGiao(row, col);
            }
        }
    };
    /**
     * Creates new form frmNhapNguyenLieu
     */
    public frmNhapNguyenLieu() {
        initComponents();
        spTable.setVerticalScrollBar(new scrollbar());
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        txtMaHoaDon.setText(HoaDonNhapDAO.layMaHoaDonNhapTiepTheo() + "");
        txtNhanVien.setText(nhanVien.getNV_TEN());
        txtThanhTien.setText("");
        txtThanhTien.setHint("");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDate now = LocalDate.now();  
        txtNgayNhap.setText(now + "");
        
        loadDataToCombobox();
        listener = new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                getDataToTxtNhaCungCap();
                getDataToTableNhapNguyenLieu();
            }
        };
        cboMaDatHang.addItemListener(listener);
    }
    
    public void loadDataToCombobox(){
        cboMaDatHang.removeAllItems();
        ArrayList<Integer> dsMaPhieuDat = PhieuDatDAO.layDsMaPhieuDatChuaGiaoDu();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Integer a : dsMaPhieuDat){
            model.addElement(new ComboBoxItem(a, a + ""));
        }
        cboMaDatHang.setModel(model);
        getDataToTxtNhaCungCap();
        getDataToTableNhapNguyenLieu();
    }
    
    public void getDataToTxtNhaCungCap(){
        int phieuDatId = ((ComboBoxItem)cboMaDatHang.getSelectedItem()).getKey();
        PhieuDat pd = new PhieuDat();
        pd.setPD_ID(phieuDatId);
        txtNhaCungCap.setText(NhaCungCapDAO.layTenNhaCungCapTheoMaPhieuDat(pd));
    }
    
    public void clear(){
        DefaultTableModel dtm = (DefaultTableModel) tblNhapNguyenLieu.getModel();
        dtm.setRowCount(0);
    }
    
    public void updateTable(){
        tblNhapNguyenLieu.getModel().removeTableModelListener(modelTableListener);
        clear();
        for(int i = 0 ; i < data.size() ; i++){
            tblNhapNguyenLieu.addRow((Vector) data.get(i));
        }
        tblNhapNguyenLieu.getModel().addTableModelListener(modelTableListener);
    }
    
    public void getDataToTableNhapNguyenLieu(){
        data.removeAllElements();
        int phieuDatId = ((ComboBoxItem)cboMaDatHang.getSelectedItem()).getKey();
        PhieuDat pd = new PhieuDat();
        pd.setPD_ID(phieuDatId);
        ArrayList<ChiTietPhieuDat> listCTPD = ChiTietPhieuDatDAO.layDsChiTietPDCuaPhieuDat(pd);
        for(int i = 0 ; i < listCTPD.size() ; i++){
            Vector info = new Vector();
            ChiTietPhieuDat ctpd = listCTPD.get(i);
            info.add(ctpd.getNL_ID());
            
            NguyenLieu a = new NguyenLieu();
            a.setNL_ID(ctpd.getNL_ID());
            NguyenLieu nl = NguyenLieuDAO.searchIngredientById(a);
            info.add(nl.getNL_TEN());
            info.add(nl.getNL_DONVITINH());
            info.add(ctpd.getSOLUONG() - ctpd.getDAGIAO());
            info.add("");
            info.add(nl.getNL_GIA());
            info.add("");
            data.add(info);
        }
        tblNhapNguyenLieu.getModel().removeTableModelListener(modelTableListener);
        clear();
        for(int i = 0 ; i < data.size() ; i++){
            tblNhapNguyenLieu.addRow((Vector) data.get(i));
        }
        tblNhapNguyenLieu.getModel().addTableModelListener(modelTableListener);
    }
    
    public void handleWhenEditColSoLuongGiao(int row, int col){
        Vector info = (Vector) data.get(row);
        int quantity;
        try {
            quantity = Integer.parseInt((String) info.get(4));
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số", "Thông báo", JOptionPane.OK_OPTION);
            info.set(4, "");
            info.set(6, "");
            updateTable();
            sumTotalPrice();
            return;
        }
        
        if(Integer.parseInt((String) info.get(4)) <= 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0", "Thông báo", JOptionPane.OK_OPTION);
            return;
        }
        
        if(Integer.parseInt((String) info.get(4)) > Integer.parseInt(info.get(3).toString())){
            JOptionPane.showMessageDialog(this, "Số lượng giao không được lớn hơn số lượng đã đặt", "Thông báo", JOptionPane.OK_OPTION);
            return;
        }
        
        info.set(6, Integer.parseInt((String) info.get(4)) * Float.parseFloat(info.get(5).toString()));
        updateTable();
        sumTotalPrice();
    }
    
    public void sumTotalPrice(){
        float sum = 0;
        for(int i = 0 ; i < data.size() ; i++){
            Vector info = (Vector) data.get(i);
            if(info.get(6).toString() != ""){
                sum+= Float.parseFloat(info.get(6).toString());
            }
        }
        txtThanhTien.setText(sum + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNhanVien = new swing.searchText();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoaDon = new swing.searchText();
        cboMaDatHang = new swing.combobox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNhaCungCap = new swing.searchText();
        txtNgayNhap = new swing.searchText();
        panelBorder1 = new swing.panelBorder();
        spTable = new javax.swing.JScrollPane();
        tblNhapNguyenLieu = new swing.table();
        button1 = new swing.button();
        jLabel6 = new javax.swing.JLabel();
        txtThanhTien = new swing.searchText();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã đặt hàng");

        txtNhanVien.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nhân viên");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhà cung cấp");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ngày nhập");

        txtNhaCungCap.setEditable(false);

        txtNgayNhap.setEditable(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        tblNhapNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NL_ID", "Tên nguyên liệu", "Đơn vị tính", "Số lượng cần giao", "Số lượng giao", "Đơn giá", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblNhapNguyenLieu);
        if (tblNhapNguyenLieu.getColumnModel().getColumnCount() > 0) {
            tblNhapNguyenLieu.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblNhapNguyenLieu.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblNhapNguyenLieu.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblNhapNguyenLieu.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblNhapNguyenLieu.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblNhapNguyenLieu.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblNhapNguyenLieu.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

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
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        button1.setText("Nhập nguyên liệu");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thành tiền");

        txtThanhTien.setEditable(false);
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addGap(39, 39, 39)
                                        .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(260, 260, 260))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(26, 26, 26)
                                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnBack))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(cboMaDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if(txtThanhTien.getText() == "" || txtThanhTien.getText() == "0.0"){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng để nhập hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        HoaDonNhap hd = new HoaDonNhap();
        hd.setHDN_ID(Integer.parseInt(txtMaHoaDon.getText()));
        hd.setNV_ID("QL001");
        int phieuDatId = ((ComboBoxItem)cboMaDatHang.getSelectedItem()).getKey();
        hd.setPD_ID(phieuDatId);
        if(HoaDonNhapDAO.themHoaDonNhap(hd)){
            for(int i = 0 ; i < data.size() ; i++){
                Vector info = (Vector) data.get(i);
                ChiTietNhap ctn = new ChiTietNhap();
                if(info.get(4).toString()!= ""){
                    ctn.setCTN_SOLUONG(Integer.parseInt(info.get(4).toString()));
                    ctn.setCTN_GIA(Float.parseFloat(info.get(6).toString()));
                } else {
                    ctn.setCTN_SOLUONG(0);
                    ctn.setCTN_GIA(0);
                }
                ctn.setHDN_ID(Integer.parseInt(txtMaHoaDon.getText()));
                ctn.setNL_ID(Integer.parseInt(info.get(0).toString()));
                
                if(!ChiTietNhapDAO.themCTPhieuNhap(ctn)){
                    JOptionPane.showMessageDialog(this, "Không thể thêm mới chi tiết phiếu nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Nhập nguyên liệu thành công", "Thông báo", JOptionPane.OK_OPTION);
            cboMaDatHang.removeItemListener(listener);
            loadDataToCombobox();
            cboMaDatHang.addItemListener(listener);
            txtThanhTien.setText("");
            txtMaHoaDon.setText(HoaDonNhapDAO.layMaHoaDonNhapTiepTheo() + "");
        } else {
            JOptionPane.showMessageDialog(this, "Không thể thêm mới hóa đơn nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

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
            java.util.logging.Logger.getLogger(frmNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNhapNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNhapNguyenLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private swing.button button1;
    private swing.combobox cboMaDatHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private swing.panelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private swing.table tblNhapNguyenLieu;
    private swing.searchText txtMaHoaDon;
    private swing.searchText txtNgayNhap;
    private swing.searchText txtNhaCungCap;
    private swing.searchText txtNhanVien;
    private swing.searchText txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
