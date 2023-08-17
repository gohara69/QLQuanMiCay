/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import DAO.NguyenLieuDAO;
import DAO.NhaCungCapDAO;
import DAO.PhieuDatDAO;
import DAO.ChiTietPhieuDatDAO;
import DAO.NhanVienDAO;
import DAO.PhanQuyenDAO;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.ComboBoxItem;
import model.NguyenLieu;
import model.NhaCungCap;
import model.PhieuDat;
import model.ChiTietPhieuDat;
import model.NhanVien;
import model.TaiKhoan;
import swing.button;
import swing.combobox;
import swing.scrollbar;
import swing.tableActionCellEditor;
import swing.tableActionEvent;
import swing.tableCellRenderer;

/**
 *
 * @author VU HOANG
 */
public class frmDatNguyenLieu extends javax.swing.JFrame {
    Vector data = new Vector();
    Vector dataId = new Vector();
    tableActionEvent event;
    TaiKhoan taiKhoan = main.main.tkhoan;
    NhanVien nhanVien = NhanVienDAO.layNhanVienTheoTaiKhoan(taiKhoan);
    TableModelListener modelTableListener = new TableModelListener(){
        @Override
        public void tableChanged(TableModelEvent e) {
            int row = tblNguyenLieu.getSelectedRow();
            int col = tblNguyenLieu.getSelectedColumn();
            if(col == 0){
                handleWhenEditColId(row, col);
            } else if (col == 1) {
                handleWhenEditColTenNguyenLieu(row, col);
            } else if (col == 3) {
                handleWhenEditColSoLuong(row, col);
            }
        }
    };
    /**
     * Creates new form frmNhapNguyenLieu
     */
    public frmDatNguyenLieu() {
        initComponents();
        spTable.setVerticalScrollBar(new scrollbar());
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        spTableNguyenLieu.setVerticalScrollBar(new scrollbar());
        spTableNguyenLieu.getViewport().setBackground(Color.WHITE);
        JPanel panel = new JPanel();
        spTableNguyenLieu.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        
        txtPhieuDatId.setHint("");
        txtPhieuDatId.setHint("");
        txtTenNhanVien.setHint("");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDate now = LocalDate.now();  
        txtNgayNhap.setText(now + "");
        txtThanhTien.setHint("");
        txtSoLuong.setHint("");
        tblNguyenLieu.getModel().addTableModelListener(modelTableListener);
        txtTenNhanVien.setText(nhanVien.getNV_TEN());
        txtPhieuDatId.setText(PhieuDatDAO.layMaPhieuDatTiepTheo() + "");
        
        cboNhaCungCap.removeAllItems();
        ArrayList<NhaCungCap> dsNCC = NhaCungCapDAO.layDanhSachNhaCungCap();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(NhaCungCap a : dsNCC){
            model.addElement(new ComboBoxItem(a.getNCC_ID(), a.getNCC_TEN()));
        }
        cboNhaCungCap.setModel(model);
        cboNhaCungCap.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                getDataToColNL_IDFromNCC_ID();
                getDataToColTenNguyenLieuFromNCC_ID();
                getDataToTableNguyenLieu();
                DefaultTableModel dtm = (DefaultTableModel) tblNguyenLieu.getModel();
                dtm.setRowCount(0);
                data.removeAllElements();
                Vector nul = new Vector();
                nul.add("");
                nul.add("");
                nul.add("");
                nul.add("");
                nul.add("");
                nul.add("");
                nul.add("");
                tblNguyenLieu.addRow(nul);
            }
        });
        getDataToColNL_IDFromNCC_ID();
        getDataToColTenNguyenLieuFromNCC_ID();
        getDataToTableNguyenLieu();
        
        event = new tableActionEvent(){
            @Override
            public void onDelete(int row) {
                if(tblNguyenLieu.isEditing()){
                    tblNguyenLieu.getCellEditor().stopCellEditing();
                }
                data.remove(row);
                updateTableWhenEnterQuantity();
            }
        };
    }
    
    public void updateTable(){
        tblNguyenLieu.getModel().removeTableModelListener(modelTableListener);
        clear();
        for(int i = 0 ; i < data.size() ; i++){
            tblNguyenLieu.addRow((Vector) data.get(i));
        }
        tblNguyenLieu.getModel().addTableModelListener(modelTableListener);
        tblNguyenLieu.getColumnModel().getColumn(6).setCellRenderer(new tableCellRenderer());
        tblNguyenLieu.getColumnModel().getColumn(6).setCellEditor(new tableActionCellEditor(event));
    }
    
    public void updateTableWhenEnterQuantity(){
        tblNguyenLieu.getModel().removeTableModelListener(modelTableListener);
        clear();
        for(int i = 0 ; i < data.size() ; i++){
            tblNguyenLieu.addRow((Vector) data.get(i));
        }
        Vector nul = new Vector();
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        tblNguyenLieu.addRow(nul);
        tblNguyenLieu.getModel().addTableModelListener(modelTableListener);
    }
    
    public void clear(){
        DefaultTableModel dtm = (DefaultTableModel) tblNguyenLieu.getModel();
        dtm.setRowCount(0);
    }
    
    public void getDataToColNL_IDFromNCC_ID(){
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        NhaCungCap ncc = new NhaCungCap();
        ncc.setNCC_ID(nccId);
        ArrayList<Integer> lstIngredientId = NguyenLieuDAO.searchIngredientByNCCId(ncc);
        combobox comboId = new combobox();
        for(int i = 0 ; i < lstIngredientId.size() ; i++){
            comboId.addItem(lstIngredientId.get(i) + "");
        }
        TableColumn col = tblNguyenLieu.getColumnModel().getColumn(0);
        col.setCellEditor(new DefaultCellEditor(comboId));
    }
    
    public void getDataToColTenNguyenLieuFromNCC_ID(){
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        NhaCungCap ncc = new NhaCungCap();
        ncc.setNCC_ID(nccId);
        ArrayList<NguyenLieu> lstIngredientId = NguyenLieuDAO.searchIngredientNameByNCCId(ncc);
        combobox comboId = new combobox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 0 ; i < lstIngredientId.size() ; i++){
            model.addElement(new ComboBoxItem(lstIngredientId.get(i).getNL_ID(), lstIngredientId.get(i).getNL_TEN()));
        }
        comboId.setModel(model);
        TableColumn col = tblNguyenLieu.getColumnModel().getColumn(1);
        col.setCellEditor(new DefaultCellEditor(comboId));
    }
    
    public void handleWhenEditColId(int row, int col){
        String id = (String) tblNguyenLieu.getValueAt(row, col);
        try{
            int IngredientId = Integer.parseInt(id);
            NguyenLieu nl = new NguyenLieu();
            nl.setNL_ID(IngredientId);
            
            if(data.isEmpty() || ("".equals((String) tblNguyenLieu.getValueAt(row, 1))) == true ){
                handleWhenEditColIdNotEditQuantity(nl);
            } else {
                if ("".equals((String) tblNguyenLieu.getValueAt(row, 5))) {
                    handleInExistedRow(row, nl);
                } else {
                    handleWhenEditColIdEditQuantity(nl, row);
                }
            } 
            cboNhaCungCap.setEnabled(false);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(frmDatNguyenLieu.this, "Vui lòng nhập mã nguyên liệu dòng 142", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void handleInExistedRow(int row, NguyenLieu nl){
        Vector info = (Vector) data.get(row);
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        NguyenLieu nlieu = NguyenLieuDAO.searchIngredientById(nl, nccId).get(0);
        info.set(0, nlieu.getNL_ID());
        info.set(1, nlieu.getNL_TEN());
        info.set(2, nlieu.getNL_DONVITINH());
        info.set(4, nlieu.getNL_GIA());
        updateTable();
    }
    
    public void handleWhenEditColIdNotEditQuantity(NguyenLieu nl){
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        ArrayList<NguyenLieu> dsNL = NguyenLieuDAO.searchIngredientById(nl, nccId);
        for(NguyenLieu nlieu: dsNL){
            Vector info = new Vector();
            info.add(nlieu.getNL_ID());
            info.add(nlieu.getNL_TEN());
            info.add(nlieu.getNL_DONVITINH());
            info.add("");
            info.add(nlieu.getNL_GIA());
            info.add("");
            info.add("");
                        
            data.add(info);
            updateTable();
        }
    }
    
    public void handleWhenEditColIdEditQuantity(NguyenLieu nl, int row){
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        ArrayList<NguyenLieu> dsNL = NguyenLieuDAO.searchIngredientById(nl, nccId);
        Vector info = (Vector) data.get(row);
        Float gia = NguyenLieuDAO.searchIngredientPriceById(nl);
        for(NguyenLieu nlieu: dsNL){
            info.set(0, nlieu.getNL_ID());
            info.set(1, nlieu.getNL_TEN());
            info.set(2, nlieu.getNL_DONVITINH());
            info.set(4, nlieu.getNL_GIA());
            info.set(5,"" + nlieu.getNL_GIA()* Integer.parseInt((String) info.get(3)));
        }
        updateTableWhenEnterQuantity();
        sumTotalPrice();
    }
    
    public void handleWhenEditColTenNguyenLieu(int row, int col){
        int id = ((ComboBoxItem)tblNguyenLieu.getValueAt(row, col)).getKey();
        try{
            NguyenLieu nl = new NguyenLieu();
            nl.setNL_ID(id);
            
            if(data.isEmpty() || "".equals((String) tblNguyenLieu.getValueAt(row, 2))){
                handleWhenEditColIdNotEditQuantity(nl);
            } else {
                if ("".equals((String) tblNguyenLieu.getValueAt(row, 5))) {
                    handleInExistedRow(row, nl);
                } else {
                    handleWhenEditColIdEditQuantity(nl, row);
                }
            }
            cboNhaCungCap.setEnabled(false);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(frmDatNguyenLieu.this, "Vui lòng nhập mã nguyên liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void handleWhenEditColSoLuong(int row, int col){
        if(tblNguyenLieu.getValueAt(row, 0) == null || tblNguyenLieu.getValueAt(row, 1) == null ) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu để nhập trước khi chọn số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            updateTable();
            return;
        } 
        
        int id = (Integer)tblNguyenLieu.getValueAt(row, 0);
        Vector info = (Vector) data.get(row);
        int soluong;
        try{
            soluong = Integer.parseInt((String) info.get(3));
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số vào cột số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            info.set(3, "");
            info.set(5, "");
            updateTable();
            sumTotalPrice();
            return;
        }
        if(Integer.parseInt((String) info.get(3)) <= 0){
            JOptionPane.showMessageDialog(this, "Số lượng cần nhập phải lớn hơn 0", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            updateTable();
            return;
        }
        try{
            NguyenLieu nl = new NguyenLieu();
            nl.setNL_ID(id);
            int quantity = Integer.parseInt((String) info.get(3));
            float price = (float) info.get(4);
            info.set(5,"" +  price * quantity);
            updateTableWhenEnterQuantity();
            sumTotalPrice();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(frmDatNguyenLieu.this, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void sumTotalPrice(){
        float sum = 0;
        for(int i = 0 ; i < data.size() ; i++){
            Vector info = (Vector)data.get(i);
            if(info.get(5) == ""){
                continue;
            }
            sum+= Float.parseFloat((String)(info.get(5)));
        }
        txtThanhTien.setText(sum + "");
    }
    
    public void themPhieuDat(){
        PhieuDat pd = new PhieuDat();
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        pd.setNCC_ID(nccId);
        pd.setNV_ID(nhanVien.getNV_ID());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDate now = LocalDate.now();  
        pd.setPD_DATE("" + now);
        pd.setPD_ID(Integer.parseInt(txtPhieuDatId.getText()));
        pd.setPD_TRANGTHAI(false);
        PhieuDatDAO.themPhieuNhap(pd);
    }
    
    public void gopChiTietPhieuDat(){
        for(int i = 0 ; i < data.size() - 1 ; i++){
            Vector a = (Vector) data.get(i);
            for(int j = i + 1 ; j < data.size() ; j++){
                Vector b = (Vector) data.get(j);
                if(a.get(0) == b.get(0)){
                    int soluong = Integer.parseInt((String) b.get(3)) + Integer.parseInt((String)a.get(3));
                    a.set(3, soluong + "");
                    a.set(5, soluong * Float.parseFloat(a.get(4).toString()));
                    data.remove(b);
                    j--;
                }
            }
        }
    }
    
    public void themChiTietPhieuDat(){
        gopChiTietPhieuDat();
        for(int i = 0 ; i < data.size() ; i++){
            ChiTietPhieuDat ctpd = new ChiTietPhieuDat();
            ctpd.setPD_ID(Integer.parseInt(txtPhieuDatId.getText()));
            ctpd.setDAGIAO(0);
            Vector info = (Vector) data.get(i);
            ctpd.setNL_ID((int) info.get(0));
            ctpd.setSOLUONG(Integer.parseInt((String)info.get(3)));
            
            ChiTietPhieuDatDAO.themCTPhieuNhap(ctpd);
        }
    }
    
    public void clearAll(){
        tblNguyenLieu.getModel().removeTableModelListener(modelTableListener);
        txtPhieuDatId.setText(PhieuDatDAO.layMaPhieuDatTiepTheo() + "");
        txtThanhTien.setText("");
        DefaultTableModel dtm = (DefaultTableModel) tblNguyenLieu.getModel();
        dtm.setRowCount(0);
        Vector nul = new Vector();
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        nul.add("");
        tblNguyenLieu.addRow(nul);
        tblNguyenLieu.getColumnModel().getColumn(6).setCellRenderer(new tableCellRenderer());
        tblNguyenLieu.getColumnModel().getColumn(6).setCellEditor(new tableActionCellEditor(event));
        data.removeAllElements();
        tblNguyenLieu.getModel().addTableModelListener(modelTableListener);
    }
    
    public void getDataToTableNguyenLieu(){
        DefaultTableModel dtm = (DefaultTableModel) tblNL.getModel();
        dtm.setRowCount(0);
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        ArrayList<NguyenLieu> dsNL = NguyenLieuDAO.searchIngredientFullByNCCId(new NhaCungCap(nccId));
        for(NguyenLieu nlieu: dsNL){
            Vector info = new Vector();
            info.add(nlieu.getNL_ID());
            info.add (nlieu.getNL_TEN());
            info.add(nlieu.getNL_DONVITINH());
            info.add(nlieu.getNL_SOLUONG());
            info.add("");
            
            tblNL.addRow(info);
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

        panelBorder1 = new swing.panelBorder();
        btnBack = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPhieuDatId = new swing.searchText();
        jLabel2 = new javax.swing.JLabel();
        txtThanhTien = new swing.searchText();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenNhanVien = new swing.searchText();
        panelBorder2 = new swing.panelBorder();
        spTable = new javax.swing.JScrollPane();
        tblNguyenLieu = new swing.table();
        btnNhapNguyenLieu = new swing.button();
        jLabel5 = new javax.swing.JLabel();
        txtNgayNhap = new swing.searchText();
        cboNhaCungCap = new swing.combobox();
        panelBorder3 = new swing.panelBorder();
        spTableNguyenLieu = new javax.swing.JScrollPane();
        tblNL = new swing.table();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new swing.searchText();
        btnLoc = new swing.button();
        button1 = new swing.button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã đặt hàng");

        txtPhieuDatId.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nhân viên");

        txtThanhTien.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nhà cung cấp");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày nhập");

        txtTenNhanVien.setEnabled(false);

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NL_ID", "Tên nguyên liệu", "Đơn vị tính", "Số lượng", "Đơn giá", "Giá", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguyenLieu.setRowHeight(45);
        tblNguyenLieu.setSelectionBackground(new java.awt.Color(255, 216, 143));
        spTable.setViewportView(tblNguyenLieu);
        if (tblNguyenLieu.getColumnModel().getColumnCount() > 0) {
            tblNguyenLieu.getColumnModel().getColumn(0).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(0).setPreferredWidth(2);
            tblNguyenLieu.getColumnModel().getColumn(1).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblNguyenLieu.getColumnModel().getColumn(2).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(2).setPreferredWidth(25);
            tblNguyenLieu.getColumnModel().getColumn(3).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblNguyenLieu.getColumnModel().getColumn(4).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblNguyenLieu.getColumnModel().getColumn(5).setResizable(false);
            tblNguyenLieu.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNhapNguyenLieu.setText("Đặt nguyên liệu");
        btnNhapNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapNguyenLieuActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Thành tiền");

        txtNgayNhap.setEnabled(false);

        cboNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hải Chiều", "Gia Long", "Tự Đức" }));
        cboNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNhaCungCapMouseClicked(evt);
            }
        });

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        spTableNguyenLieu.setBorder(null);

        tblNL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NL_ID", "Tên nguyên liệu", "Đơn vị tính", "Số lượng"
            }
        ));
        spTableNguyenLieu.setViewportView(tblNL);

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableNguyenLieu)
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTableNguyenLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        button1.setText("Thêm vào phiếu đặt");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhieuDatId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(24, 24, 24)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(btnNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack))
                .addGap(12, 12, 12)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPhieuDatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhapNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        getContentPane().add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void cboNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNhaCungCapMouseClicked
        getDataToColNL_IDFromNCC_ID();
        getDataToColTenNguyenLieuFromNCC_ID();
    }//GEN-LAST:event_cboNhaCungCapMouseClicked

    private void btnNhapNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapNguyenLieuActionPerformed
        if("".equals(txtThanhTien.getText())){
            JOptionPane.showMessageDialog(this, "Không thể đặt hàng khi chưa chọn nguyên liệu để đặt", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(PhanQuyenDAO.kiemTraCoQuyenDatNguyenLieu(main.main.tkhoan)){
            try{
                themPhieuDat();
                themChiTietPhieuDat(); 
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Đặt nguyên liệu thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Đặt nguyên liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền đặt nguyên liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        clearAll();
    }//GEN-LAST:event_btnNhapNguyenLieuActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        int soLuong;
        try{
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng để lọc", "Thông báo", JOptionPane.OK_OPTION);
            return;
        }
        
        DefaultTableModel dtm = (DefaultTableModel) tblNL.getModel();
        dtm.setRowCount(0);
        int nccId = ((ComboBoxItem)cboNhaCungCap.getSelectedItem()).getKey();
        ArrayList<NguyenLieu> dsNL = NguyenLieuDAO.searchIngredientByNCCIdAndSoLuong(new NhaCungCap(nccId), soLuong);
        for(NguyenLieu nlieu: dsNL){
            Vector info = new Vector();
            info.add(nlieu.getNL_ID());
            info.add (nlieu.getNL_TEN());
            info.add(nlieu.getNL_DONVITINH());
            info.add(nlieu.getNL_SOLUONG());
            info.add("");
            
            tblNL.addRow(info);
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        cboNhaCungCap.setEnabled(false);
        
        int row = tblNL.getSelectedRow();
        NguyenLieu nl = new NguyenLieu();
        nl.setNL_ID(Integer.parseInt(tblNL.getValueAt(row, 0).toString()));
        if(row >= 0){
            NguyenLieu nlieu = NguyenLieuDAO.searchIngredientById(nl);
            Vector info = new Vector();
            info.add(nlieu.getNL_ID());
            info.add(nlieu.getNL_TEN());
            info.add(nlieu.getNL_DONVITINH());
            info.add("");
            info.add(nlieu.getNL_GIA());
            info.add("");
            info.add("");
                        
            data.add(info);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu", "Thông báo", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_button1ActionPerformed
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
            java.util.logging.Logger.getLogger(frmDatNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDatNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDatNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDatNguyenLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDatNguyenLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private swing.button btnLoc;
    private swing.button btnNhapNguyenLieu;
    private swing.button button1;
    private swing.combobox cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private swing.panelBorder panelBorder1;
    private swing.panelBorder panelBorder2;
    private swing.panelBorder panelBorder3;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTableNguyenLieu;
    private swing.table tblNL;
    private swing.table tblNguyenLieu;
    private swing.searchText txtNgayNhap;
    private swing.searchText txtPhieuDatId;
    private swing.searchText txtSoLuong;
    private swing.searchText txtTenNhanVien;
    private swing.searchText txtThanhTien;
    // End of variables declaration//GEN-END:variables

    private boolean typeof(int IngredientId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Object Object(button button) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
