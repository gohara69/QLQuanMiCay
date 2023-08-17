/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import DAO.NguyenLieuDAO;
import DAO.NguyenLieuMonDAO;
import DAO.NhanVienDAO;
import DAO.OrderDAO;
import DAO.OrderMonDAO;
import DAO.TinhTrangMonDAO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.ComboBoxItem;
import model.Mon;
import model.NguyenLieu;
import model.NhaCungCap;
import model.NhanVien;
import model.Order;
import model.OrderMon;
import model.TinhTrang;
import swing.combobox;
import swing.scrollbar;

/**
 *
 * @author VU HOANG
 */
public class frmPhanCongNhom extends javax.swing.JFrame {

    /**
     * Creates new form frmPhanCongNhom
     */
    public frmPhanCongNhom() {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadDataToTableOrder();
        txtTenNguyenLieu.setHint("");
        
        ArrayList<NhanVien> dsDauBep = NhanVienDAO.layDanhSachNhanVienDauBep();
        DefaultComboBoxModel modelDauBep = new DefaultComboBoxModel();
        for(int i = 0 ; i < dsDauBep.size() ; i++){
            NhanVien nv = dsDauBep.get(i);
            modelDauBep.addElement(new ComboBoxItem(nv.getNV_ID(), nv.getNV_TEN()));
        }
        cboNhanVien.setModel(modelDauBep);
        
        ArrayList<TinhTrang> dsTinhTrang = TinhTrangMonDAO.layDanhSachTinhTrangMon();
        DefaultComboBoxModel modelTinhTrang = new DefaultComboBoxModel();
        for(int i = 0 ; i < dsTinhTrang.size() ; i++){
            TinhTrang nv = dsTinhTrang.get(i);
            modelTinhTrang.addElement(new ComboBoxItem(nv.getMA_TT(), nv.getMA_DIENGIAI()));
        }
        cboTinhTrangMon.setModel(modelTinhTrang);
    }
    
    public void clear(){
        DefaultTableModel dtm = (DefaultTableModel) tblOrder.getModel();
        dtm.setRowCount(0);
    }
    
    public void clearTableNguyenLieu(){
        DefaultTableModel dtm = (DefaultTableModel) tblNguyenLieu.getModel();
        dtm.setRowCount(0);
    }
    
    public void loadDataToTableOrder(){
        clear();
        ArrayList<OrderMon> dsOrder = OrderMonDAO.layDanhOrderHoaDonChuaThanhToan();
        for(OrderMon o: dsOrder){
            Vector info = new Vector();
            info.add(o.getB_SOBAN());
            info.add(o.getM_TEN());
            info.add(o.getO_THOIGIAN());
            info.add(o.getO_SOLUONG());
            info.add(o.getO_GHICHU());
            
            TinhTrang tt = TinhTrangMonDAO.layTinhTrangTheoMaTinhTrang(new TinhTrang(o.getTT_MON()));
            info.add(tt.getMA_DIENGIAI());
            if("".equals(o.getNV_ID())){
                info.add(o.getNV_ID());
            } else {
                NhanVien nv = NhanVienDAO.layNhanVien(new NhanVien(o.getNV_ID()));
                info.add(nv.getNV_TEN());
            }
            
            tblOrder.addRow(info);
        }
    }
    
    public void hienThiNguyenLieuCuaMon(int pos){
        String ten = (String) tblOrder.getValueAt(pos, 1);
        clearTableNguyenLieu();
        ArrayList<NguyenLieu> dsNl = NguyenLieuMonDAO.layDanhSachNguyenLieuCuaMon(new Mon(ten));
        for (NguyenLieu nl : dsNl) {
            Vector info = new Vector();
            info.add(nl.getNL_ID());
            NguyenLieu nlieu = NguyenLieuDAO.searchIngredientById(new NguyenLieu(nl.getNL_ID()));
            info.add(nlieu.getNL_TEN());
            info.add(nl.getNL_DONVITINH());
            info.add(nl.getNL_SOLUONG());
            tblNguyenLieu.addRow(info);
        }
        String nv = (String) tblOrder.getValueAt(pos, 6);
        if(nv != ""){
            cboNhanVien.setEnabled(false);
        } else {
            cboNhanVien.setEnabled(true);
        }
    }
    
    public void layDuLieuNguyenLieuTuongDong(int pos){
        if(txtTenNguyenLieu.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(frmPhanCongNhom.this, "Vui lòng chọn nguyên liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int nlieuId = (Integer) tblNguyenLieu.getValueAt(pos, 0);
        if(!NguyenLieuMonDAO.kiemTraTonTaiNguyenLieuTuongDong(new NguyenLieu(nlieuId))){
            NguyenLieu nlieu = new NguyenLieu();
            nlieu.setNL_ID((Integer) tblNguyenLieu.getValueAt(pos, 0));
            nlieu.setNL_DONVITINH((String) tblNguyenLieu.getValueAt(pos, 2));
            nlieu.setNL_SOLUONG((Integer) tblNguyenLieu.getValueAt(pos, 3));
            NguyenLieuMonDAO.themVaoNguyenLieuTuongDong(nlieu);
        }
    }
    
    public void hienThiTenNguyenLieu(int pos){
        String ten = (String) tblNguyenLieu.getValueAt(pos, 1);
        txtTenNguyenLieu.setText(ten);
        layDuLieuNguyenLieuTuongDong(pos);
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
        btnBack = new javax.swing.JLabel();
        panelBorder2 = new swing.panelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new swing.table();
        panelBorder3 = new swing.panelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguyenLieu = new swing.table();
        txtTenNguyenLieu = new swing.searchText();
        btnXemNguyenLieuTuongDong = new swing.button();
        cboNhanVien = new swing.combobox();
        jLabel1 = new javax.swing.JLabel();
        btnPhanCong = new swing.button();
        jLabel2 = new javax.swing.JLabel();
        btnXacNhan = new swing.button();
        cboTinhTrangMon = new swing.combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bàn", "Món", "Thời gian", "Số lượng", "Ghi chú", "Tình trạng", "Nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        tblOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblOrderKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrder);
        if (tblOrder.getColumnModel().getColumnCount() > 0) {
            tblOrder.getColumnModel().getColumn(0).setMinWidth(50);
            tblOrder.getColumnModel().getColumn(0).setMaxWidth(50);
            tblOrder.getColumnModel().getColumn(3).setMinWidth(70);
            tblOrder.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NL_ID", "Tên nguyên liệu", "Đơn vị tính", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguyenLieuMouseClicked(evt);
            }
        });
        tblNguyenLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblNguyenLieuKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguyenLieu);
        if (tblNguyenLieu.getColumnModel().getColumnCount() > 0) {
            tblNguyenLieu.getColumnModel().getColumn(0).setMinWidth(50);
            tblNguyenLieu.getColumnModel().getColumn(0).setMaxWidth(50);
            tblNguyenLieu.getColumnModel().getColumn(3).setMinWidth(70);
            tblNguyenLieu.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnXemNguyenLieuTuongDong.setText("Xem");
        btnXemNguyenLieuTuongDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemNguyenLieuTuongDongActionPerformed(evt);
            }
        });

        jLabel1.setText("Nhân viên");

        btnPhanCong.setText("Phân công");
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });

        jLabel2.setText("Tình trạng món");

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnBack))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cboNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                            .addComponent(cboTinhTrangMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnXemNguyenLieuTuongDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 36, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBack)
                .addGap(27, 27, 27)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemNguyenLieuTuongDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTinhTrangMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void tblOrderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOrderKeyPressed
        hienThiNguyenLieuCuaMon(tblOrder.getSelectedRow());
    }//GEN-LAST:event_tblOrderKeyPressed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        hienThiNguyenLieuCuaMon(tblOrder.getSelectedRow());
    }//GEN-LAST:event_tblOrderMouseClicked

    private void btnXemNguyenLieuTuongDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemNguyenLieuTuongDongActionPerformed
        if(tblOrder.getSelectedRow() < 0 || tblNguyenLieu.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu món để xem nguyên liệu tương đồng");
            return;
        }
        
        int nlieuId = (Integer) tblNguyenLieu.getValueAt(tblNguyenLieu.getSelectedRow(), 0);
        ArrayList<NguyenLieu> lstIngredientId = NguyenLieuMonDAO.layDanhSachNguyenLieuTuongDong(new NguyenLieu(nlieuId));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 0 ; i < lstIngredientId.size() ; i++){
            model.addElement(new ComboBoxItem(lstIngredientId.get(i).getNL_ID(), lstIngredientId.get(i).getNL_TEN()));
        }
        
        JPanel myPanel = new JPanel();
        myPanel.setSize(800, 300);
        combobox cbo = new combobox();
        myPanel.add(new JLabel("Nguyên liệu:"));
        cbo.setModel(model);
        cbo.setSize(500, cbo.getPreferredSize().height);
        myPanel.add(cbo);
        myPanel.add(Box.createHorizontalStrut(15));
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                             "Chọn nguyên liệu thay thế", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            int stt = ((ComboBoxItem)cbo.getSelectedItem()).getKey();
            NguyenLieu nl = NguyenLieuMonDAO.layNguyenLieuTuongDongTheoSTT(stt);
            tblNguyenLieu.setValueAt(nl.getNL_ID(), tblNguyenLieu.getSelectedRow(), 0);
            tblNguyenLieu.setValueAt(((ComboBoxItem)cbo.getSelectedItem()).getValue(), tblNguyenLieu.getSelectedRow(), 1);
            tblNguyenLieu.setValueAt(nl.getNL_DONVITINH(), tblNguyenLieu.getSelectedRow(), 2);
            tblNguyenLieu.setValueAt(nl.getNL_SOLUONG(), tblNguyenLieu.getSelectedRow(), 3);
        }
    }//GEN-LAST:event_btnXemNguyenLieuTuongDongActionPerformed

    private void tblNguyenLieuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNguyenLieuKeyPressed
        if(tblNguyenLieu.getSelectedRow() >= 0){
           hienThiTenNguyenLieu(tblNguyenLieu.getSelectedRow());
        }
    }//GEN-LAST:event_tblNguyenLieuKeyPressed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        hienThiTenNguyenLieu(tblNguyenLieu.getSelectedRow());
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void tblNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguyenLieuMouseClicked
        if(tblNguyenLieu.getSelectedRow() >= 0){
           hienThiTenNguyenLieu(tblNguyenLieu.getSelectedRow());
        }
    }//GEN-LAST:event_tblNguyenLieuMouseClicked

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanCongActionPerformed
        if(tblOrder.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn order món để phân công thực hiện");
            return;
        }
        
        String nvien = (String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 6);
        if(nvien != ""){
            JOptionPane.showMessageDialog(this, "Món đang được thực hiện không thể phân công");
            return;
        }
        
        for(int i = 0 ; i < tblNguyenLieu.getRowCount() ; i++){
            NguyenLieu nl = new NguyenLieu();
            nl.setNL_ID((Integer)tblNguyenLieu.getValueAt(i, 0));
            nl.setNL_SOLUONG((Integer)tblNguyenLieu.getValueAt(i, 3) * (Integer)tblOrder.getValueAt(tblOrder.getSelectedRow(), 3));
            NguyenLieuDAO.updateSoLuongNguyenLieu(nl);
        }
        Order o = new Order();
        o.setNV_ID(((ComboBoxItem)cboNhanVien.getSelectedItem()).getKeyString());
        o.setTenMon((String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 1));
        o.setSoLuong((Integer)tblOrder.getValueAt(tblOrder.getSelectedRow(), 3));
        o.setO_THOIGIAN((String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 2));
        OrderDAO.updateOrder(o);
        NhanVien nv = NhanVienDAO.layNhanVien(new NhanVien(o.getNV_ID()));
        tblOrder.setValueAt(nv.getNV_TEN(), tblOrder.getSelectedRow(), 6);
        cboNhanVien.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Phân công nhân viên thực hiện thành công");
    }//GEN-LAST:event_btnPhanCongActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String nvien = (String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 6);
        if(nvien == ""){
            JOptionPane.showMessageDialog(this, "Món chưa có nhân viên thực hiện nên không thể xác nhận tình trạng món");
            return;
        }
        
        int tt = ((ComboBoxItem)cboTinhTrangMon.getSelectedItem()).getKey();
        Order o = new Order();
        o.setTT_MON(tt);
        o.setTenMon((String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 1));
        o.setSoLuong((Integer)tblOrder.getValueAt(tblOrder.getSelectedRow(), 3));
        o.setO_THOIGIAN((String) tblOrder.getValueAt(tblOrder.getSelectedRow(), 2));
        OrderDAO.updateThucHienMonOrder(o);
        
        TinhTrang ttrang = TinhTrangMonDAO.layTinhTrangTheoMaTinhTrang(new TinhTrang(o.getTT_MON()));
        tblOrder.setValueAt(ttrang.getMA_DIENGIAI(), tblOrder.getSelectedRow(), 5);
        JOptionPane.showMessageDialog(this, "Xác nhận món thành công");
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(frmPhanCongNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhanCongNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhanCongNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhanCongNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPhanCongNhom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private swing.button btnPhanCong;
    private swing.button btnXacNhan;
    private swing.button btnXemNguyenLieuTuongDong;
    private swing.combobox cboNhanVien;
    private swing.combobox cboTinhTrangMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private swing.panelBorder panelBorder1;
    private swing.panelBorder panelBorder2;
    private swing.panelBorder panelBorder3;
    private swing.table tblNguyenLieu;
    private swing.table tblOrder;
    private swing.searchText txtTenNguyenLieu;
    // End of variables declaration//GEN-END:variables
}
