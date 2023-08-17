/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import DAO.ChiTietNhapDAO;
import DAO.HoaDonNhapDAO;
import DAO.NguyenLieuDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.PhieuDatDAO;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietNhap;
import model.ChiTietPhieuDat;
import model.ComboBoxItem;
import model.HoaDonNhap;
import model.NguyenLieu;
import model.NhanVien;
import model.PhieuDat;
import swing.scrollbar;

/**
 *
 * @author VU HOANG
 */
public class frmHoaDonNhap extends javax.swing.JFrame {

    Vector dataHoaDon = new Vector();
    Vector dataChiTietNhap = new Vector();
    /**
     * Creates new form frmHoaDonNhap
     */
    public frmHoaDonNhap() {
        initComponents();
        spTableCTHDN.setVerticalScrollBar(new scrollbar());
        spTableCTHDN.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        spTableCTHDN.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        spTableHoaDonNhap.setVerticalScrollBar(new scrollbar());
        spTableHoaDonNhap.getViewport().setBackground(Color.WHITE);
        JPanel pa = new JPanel();
        spTableHoaDonNhap.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pa);
        
        txtNhaCungCap.setHint("");
        
        loadDataToCombobox();
        getDataToTxtNhaCungCap();
        getDataToTableHoaDonNhap();
        cboMaDatHang.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                getDataToTxtNhaCungCap();
                getDataToTableHoaDonNhap();
            }
        });
    }
    
    public void loadDataToCombobox(){
        cboMaDatHang.removeAllItems();
        ArrayList<Integer> dsMaPhieuDat = PhieuDatDAO.layDsMaPhieuDatDaCoHoaDonNhan();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ComboBoxItem(-1, "Tất cả"));
        for(Integer a : dsMaPhieuDat){
            model.addElement(new ComboBoxItem(a, a + ""));
        }
        cboMaDatHang.setModel(model);
        getDataToTxtNhaCungCap();
    }
    
    public void getDataToTxtNhaCungCap(){
        int phieuDatId = ((ComboBoxItem)cboMaDatHang.getSelectedItem()).getKey();
        if(phieuDatId == -1){
            txtNhaCungCap.setText("");
            return;
        } 
        PhieuDat pd = new PhieuDat();
        pd.setPD_ID(phieuDatId);
        txtNhaCungCap.setText(NhaCungCapDAO.layTenNhaCungCapTheoMaPhieuDat(pd));
    }
    
     public void clearTableHoaDonNhap(){
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonNhap.getModel();
        dtm.setRowCount(0);
    }
     
     public void clearTableChiTietNhap(){
        DefaultTableModel dtm = (DefaultTableModel) tblCTN.getModel();
        dtm.setRowCount(0);
    }
    
    public void getDataToTableHoaDonNhap(){
        int phieuDatId = ((ComboBoxItem)cboMaDatHang.getSelectedItem()).getKey();
        if(phieuDatId == -1){
            getDataToTableHoaDonNhapKhiChonTatCa();
            return;
        } 
        
        PhieuDat pd = new PhieuDat();
        pd.setPD_ID(phieuDatId);
        getDataToTableHoaDonVoiPhieuDat(pd);
    }
    
    public void getDataToTableHoaDonVoiPhieuDat(PhieuDat pd){
        dataHoaDon.removeAllElements();
        ArrayList<HoaDonNhap> listHDN = HoaDonNhapDAO.layDanhSachHoaDonNhapChoPhieuDat(pd);
        for(int i = 0 ; i < listHDN.size() ; i++){
            Vector info = new Vector();
            HoaDonNhap hdn = listHDN.get(i);
            info.add(hdn.getHDN_ID());

            NhanVien a = new NhanVien();
            a.setNV_ID(hdn.getNV_ID());
            NhanVien nv = NhanVienDAO.layNhanVien(a);
            info.add(nv.getNV_TEN());
            info.add(hdn.getHDN_NGAYNHAP());
            info.add(hdn.getHDN_THANHTIEN());
            dataHoaDon.add(info);
        }
        clearTableHoaDonNhap();
        for(int i = 0 ; i < dataHoaDon.size() ; i++){
            tblHoaDonNhap.addRow((Vector) dataHoaDon.get(i));
        }
    }
    
    public void getDataToTableHoaDonNhapKhiChonTatCa(){
        dataHoaDon.removeAllElements();
        ArrayList<HoaDonNhap> listHDN = HoaDonNhapDAO.layDanhSachTatCaHoaDonNhap();
        for(int i = 0 ; i < listHDN.size() ; i++){
            Vector info = new Vector();
            HoaDonNhap hdn = listHDN.get(i);
            info.add(hdn.getHDN_ID());

            NhanVien a = new NhanVien();
            a.setNV_ID(hdn.getNV_ID());
            NhanVien nv = NhanVienDAO.layNhanVien(a);
            info.add(nv.getNV_TEN());
            info.add(hdn.getHDN_NGAYNHAP());
            info.add(hdn.getHDN_THANHTIEN());
            dataHoaDon.add(info);
        }
        clearTableHoaDonNhap();
        for(int i = 0 ; i < dataHoaDon.size() ; i++){
            tblHoaDonNhap.addRow((Vector) dataHoaDon.get(i));
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

        btnBack = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboMaDatHang = new swing.combobox();
        panelBorder1 = new swing.panelBorder();
        spTableCTHDN = new javax.swing.JScrollPane();
        tblCTN = new swing.table();
        panelBorder2 = new swing.panelBorder();
        spTableHoaDonNhap = new javax.swing.JScrollPane();
        tblHoaDonNhap = new swing.table();
        jLabel4 = new javax.swing.JLabel();
        txtNhaCungCap = new swing.searchText();

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

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        spTableCTHDN.setBorder(null);

        tblCTN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NL_ID", "Tên nguyên liệu", "Đơn vị tính", "Số lượng", "Đơn giá", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTableCTHDN.setViewportView(tblCTN);
        if (tblCTN.getColumnModel().getColumnCount() > 0) {
            tblCTN.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblCTN.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblCTN.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblCTN.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblCTN.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblCTN.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableCTHDN)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableCTHDN, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        spTableHoaDonNhap.setBorder(null);

        tblHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Ngày lập", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonNhapMouseClicked(evt);
            }
        });
        spTableHoaDonNhap.setViewportView(tblHoaDonNhap);
        if (tblHoaDonNhap.getColumnModel().getColumnCount() > 0) {
            tblHoaDonNhap.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDonNhap.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblHoaDonNhap.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblHoaDonNhap.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblHoaDonNhap.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableHoaDonNhap)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableHoaDonNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhà cung cấp");

        txtNhaCungCap.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(201, 201, 201)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboMaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMaDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void tblHoaDonNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonNhapMouseClicked
        Vector info = (Vector) dataHoaDon.get(tblHoaDonNhap.getSelectedRow());
        HoaDonNhap hdn = new HoaDonNhap();
        hdn.setHDN_ID(Integer.parseInt(info.get(0).toString()));
        dataChiTietNhap.removeAllElements();
        ArrayList<ChiTietNhap> listCTN = ChiTietNhapDAO.layDsChiTietNhapCuaHoaDonNhap(hdn);
        for(int i = 0 ; i < listCTN.size() ; i++){
            ChiTietNhap ctn = listCTN.get(i);
            Vector inf = new Vector();
            inf.add(ctn.getNL_ID());
            
            NguyenLieu nlieu = new NguyenLieu();
            nlieu.setNL_ID(ctn.getNL_ID());
            NguyenLieu nl = NguyenLieuDAO.searchIngredientById(nlieu);
            inf.add(nl.getNL_TEN());
            inf.add(nl.getNL_DONVITINH());
            inf.add(ctn.getCTN_SOLUONG());
            inf.add(nl.getNL_GIA());
            inf.add(ctn.getCTN_GIA());
            dataChiTietNhap.add(inf);
        }
        clearTableChiTietNhap();
        for(int i = 0 ; i < dataChiTietNhap.size() ; i++){
            tblCTN.addRow((Vector) dataChiTietNhap.get(i));
        }
    }//GEN-LAST:event_tblHoaDonNhapMouseClicked

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
            java.util.logging.Logger.getLogger(frmHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHoaDonNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHoaDonNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private swing.combobox cboMaDatHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private swing.panelBorder panelBorder1;
    private swing.panelBorder panelBorder2;
    private javax.swing.JScrollPane spTableCTHDN;
    private javax.swing.JScrollPane spTableHoaDonNhap;
    private swing.table tblCTN;
    private swing.table tblHoaDonNhap;
    private swing.searchText txtNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
