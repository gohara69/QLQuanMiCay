/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import DAO.DanhMucDAO;
import DAO.HoaDonDAO;
import DAO.MonDAO;
import DAO.NhanVienDAO;
import DAO.OrderDAO;
import DAO.PhanQuyenDAO;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.Document;
import javax.swing.text.Element;
import model.Ban;
import model.DanhMuc;
import model.HoaDon;
import model.ModelButtonFood;
import model.Mon;
import model.NhanVien;
import model.Order;

/**
 *
 * @author PC
 */
public class frmChonMon extends javax.swing.JFrame {

    /**
     * Creates new form frmChonMon1
     */

    int sumPriceOrder = 0;
    JTextField xField = new JTextField(5);
    JTextField yField = new JTextField(5);
    JPanel myPanel = new JPanel();
    Ban ban = frmBan.dsBan.get(frmBan.saveSoBan - 1);
    public frmChonMon() {
        initComponents();
        insertAllButtonFood();
        insertButtonCategoryFood();
        insertButtonFoodOrderd();
        lbSoBanDaChon.setText("Chi Tiết Gọi Món Bàn Số " + frmBan.saveSoBan);
        jPanel6.removeAll();
        layDanhSachOrderDaDat();
        ArrayList<Order> listOrderGom = gomOrderHienThi();
        for(int i = 0 ; i < listOrderGom.size() ; i++){
            Order o = listOrderGom.get(i);
            Mon m = MonDAO.layMonTheoTenMon(new Mon(o.getTenMon()));
            JButton bt = createButtonFood(m.getSrcAnh(), o.getTenMon(), o.getGiaTien(), "Số lượng: " + o.getSoLuong());
            insertButtonFoodChoose(jPanel6, bt);
        }
        jLabel1.setText(sumPriceOrder + " VNĐ");
        jPanel1.updateUI();
        createPanelAskQuantity();
        NhanVien nhanVien = NhanVienDAO.layNhanVienTheoTaiKhoan(main.main.tkhoan);
        txtNhanVien.setText(nhanVien.getNV_TEN());
    }
    
    public ArrayList<Order> gomOrderHienThi(){
        ArrayList<Order> listOrder = new ArrayList<>();
        for(int k = 0 ; k < ban.getDsOrder().size() ; k++){
            Order order = new Order(ban.getDsOrder().get(k));
            listOrder.add(order);
        }
        for(int i = 0 ; i < listOrder.size() - 1 ; i++){
            Order o1 =  listOrder.get(i);
            for(int j = i + 1 ; j < listOrder.size() ; j++){
                Order o2 = listOrder.get(j);
                if(o1.getTenMon().equals(o2.getTenMon())){
                    o1.setSoLuong(o1.getSoLuong() + o2.getSoLuong());
                    Mon m = MonDAO.layMonTheoTenMon(new Mon(o1.getTenMon()));
                    o1.setGiaTien(m.getGiaMon() * o1.getSoLuong());
                    listOrder.remove(j);
                    j--;
                }
            }
        }
        return listOrder;
    }
    
    public void layDanhSachOrderDaDat(){
        if(ban.getHD_ID() == 0){
            int hd_id = HoaDonDAO.layHoaDonChuaThanhToanTheoBan(new Ban(frmBan.saveSoBan));
            ban.setHD_ID(hd_id);
        } 
        ban.setDsOrder(OrderDAO.layOrderDatCuaHoaDon(new HoaDon(ban.getHD_ID())));
        for(int i = 0 ; i < ban.getDsOrder().size() ; i++){
            Order o = ban.getDsOrder().get(i);
            sumPriceOrder+= o.getGiaTien();
        }
    }

    public ImageIcon resizeImage(String srcImg, int height, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(srcImg));
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(image);
            return icon;
        } catch (IOException ex) {
            Logger.getLogger(frmChonMon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JButton createButtonFood(String srcImage, String nameFood, int priceFood, String quantity) {
        JButton button = new JButton();
        Font font = new Font("Segoe", Font.BOLD, 18);
        File file = new File("./src/images/" + srcImage);
        String absolutePath = file.getAbsolutePath();
        ImageIcon icon = resizeImage(absolutePath, 100, 100);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIcon(icon);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(font);
        button.setText("<html><body style=\"text-align: center; color: #00DD00\">" + nameFood + "<br>" + "<p style=\"color: #33CCFF\">" + priceFood + " VNĐ" + "<br>" + "<span style=\"color: #FF00FF;\">" + quantity + "</span>" + "</p>" + "</body></html>");
        return button;
    }

    public String tachChuoiLayTenMon(String textButtonFood) {
        String textNameFood = "";
        for (int i = 55; i < textButtonFood.length(); i++) {
            if (textButtonFood.charAt(i) != '<')
                textNameFood += textButtonFood.charAt(i);
            else 
                break;
        }
        return textNameFood;
    }
    
    public Order layOrderTamTheoTen(String ten){
        ArrayList<Order> listOrderGom = gomOrderHienThi();
        for(int i = 0 ; i < listOrderGom.size() ; i++){
            Order o = listOrderGom.get(i);
            if(o.getTenMon().equals(ten)){
                return o;
            }
        }
        return null;
    }
    
    public void deleteListOrder(String name){
        for(int i = 0 ; i < ban.getDsOrder().size() ; i++){
            if(ban.getDsOrder().get(i).getTenMon().equals(name)){
                ban.getDsOrder().remove(i);
            }
        }
    }

    public void insertButtonFoodChoose(JPanel panelContainBtnFoodOrder, JButton buttonFoodChoose) {
        panelContainBtnFoodOrder.add(buttonFoodChoose);
        panelContainBtnFoodOrder.updateUI();
        buttonFoodChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frmChonMon.this, "Bạn có muốn xóa món?");
                int i = 0;
                if (option == 0) {
                    String nameFood = tachChuoiLayTenMon(buttonFoodChoose.getText());
                    if(ban.getHD_ID() == 0){
                        panelContainBtnFoodOrder.remove(buttonFoodChoose);
                        panelContainBtnFoodOrder.revalidate();
                        panelContainBtnFoodOrder.repaint();
                        deleteListOrder(nameFood);
                        capNhatGiaTien();
                        JOptionPane.showMessageDialog(frmChonMon.this, "Đã xóa món");
                    } else {
                        HoaDon hd = new HoaDon(ban.getHD_ID());
                        if(OrderDAO.kiemTraTonTaiOrderVoiTenMon(hd, new Mon(nameFood))){
                            int soLuongDat = OrderDAO.laySoLuongOrder1MonTheoHoaDon(hd, new Mon(nameFood));
                            Order orderXoa = layOrderTamTheoTen(nameFood);
                            int soLuongTam = orderXoa.getSoLuong();
                            if(soLuongTam == soLuongDat){
                                JOptionPane.showMessageDialog(frmChonMon.this, "Không thể xóa món đã đặt");
                            } else {
                                int soLuong = soLuongTam - soLuongDat;
                                for(i = ban.getDsOrder().size() - 1 ; i >= 0 ; i--){
                                    Order o = ban.getDsOrder().get(i);
                                    if(o.getTenMon().equals(nameFood)){
                                        soLuong-= o.getSoLuong();
                                        ban.getDsOrder().remove(i);
                                        if(soLuong == 0){
                                            break;
                                        }
                                    }
                                }
                                
                                panelContainBtnFoodOrder.removeAll();
                                ArrayList<Order> listOrderGom = gomOrderHienThi();
                                for(i = 0 ; i < listOrderGom.size() ; i++){
                                    Order o = listOrderGom.get(i);
                                    Mon m = MonDAO.layMonTheoTenMon(new Mon(o.getTenMon()));
                                    JButton bt = createButtonFood(m.getSrcAnh(), o.getTenMon(), o.getGiaTien(), "Số lượng: " + o.getSoLuong());
                                    insertButtonFoodChoose(jPanel6, bt);
                                }
                                capNhatGiaTien();
                                JOptionPane.showMessageDialog(frmChonMon.this, "Đã xóa món");
                            }
                        } else {
                            panelContainBtnFoodOrder.remove(buttonFoodChoose);
                            panelContainBtnFoodOrder.revalidate();
                            panelContainBtnFoodOrder.repaint();
                            deleteListOrder(nameFood);
                            capNhatGiaTien();
                            JOptionPane.showMessageDialog(frmChonMon.this, "Đã xóa món");
                        }
                    }
                }
            }
        });
    }

    public void insertButtonFoodOrderd(){
        ArrayList<ModelButtonFood> listFoodOrder = OrderDAO.getListFoodOrdered(frmBan.saveSoBan);
        for(ModelButtonFood a : listFoodOrder){
            JButton button = createButtonFood(a.getSrcAnh(), a.getTenMon(), a.getGia(), String.valueOf(a.getSoLuong()));
            jPanel6.add(button);
        }
        jPanel6.updateUI();
    }
    
    public void createPanelAskQuantity(){
        myPanel.add(new JLabel("Nhập số lượng:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Nhập ghi chú:"));
        myPanel.add(yField);
    }
    
    public void capNhatGiaTien(){
        int sum = 0;
        for(int i = 0 ; i < ban.getDsOrder().size() ; i++){
            sum+= ban.getDsOrder().get(i).getGiaTien();
        }
        jLabel1.setText(sum + " VNĐ");
    }
    
    public void insertButtonFood(ArrayList<Mon> dsMon, JPanel panelContainBtnFood, JPanel panelContainBtnFoodOrder) {
        for (Mon mon : dsMon) {
            JButton button = createButtonFood(mon.getSrcAnh(), mon.getTenMon(), mon.getGiaMon(), "");
            panelContainBtnFood.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, myPanel, 
                             "Mời nhập số lượng", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        panelContainBtnFoodOrder.removeAll();
                        int quantityOrder = 0;
                        try{
                            quantityOrder = Integer.parseInt(xField.getText());
                            if(quantityOrder <= 0){
                                JOptionPane.showMessageDialog(frmChonMon.this, "Vui lòng nhập số lượng dương", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        } catch(Exception ex){
                            JOptionPane.showMessageDialog(frmChonMon.this, "Vui lòng nhập số", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        Order o = new Order();
                        o.setO_GHICHU((yField.getText()) == null ? "" : yField.getText());
                        o.setSoLuong(quantityOrder);
                        o.setGiaTien(mon.getGiaMon() * o.getSoLuong());
                        o.setTenMon(mon.getTenMon());
                        
                        ban.getDsOrder().add(o);
                        capNhatGiaTien();
                        
                        ArrayList<Order> listOrderGom = gomOrderHienThi();
                        for(int i = 0 ; i < listOrderGom.size() ; i++){
                            Order or = listOrderGom.get(i);
                            Mon m = MonDAO.layMonTheoTenMon(new Mon(or.getTenMon()));
                            JButton bt = createButtonFood(m.getSrcAnh(), or.getTenMon(), or.getGiaTien(), "Số lượng: " + or.getSoLuong());
                            insertButtonFoodChoose(jPanel6, bt);
                        }
                    }
                }
            });
        }
    }

    public void insertAllButtonFood() {
        ArrayList<Mon> ds_mon = MonDAO.layDanhSachMonAn();
        insertButtonFood(ds_mon, jPanel1, jPanel6);
    }

    public void insertButtonCategoryFood() {
        ArrayList<DanhMuc> dsdm = DanhMucDAO.layDanhSachDanhMuc();
        JButton btAll = new JButton("Tất cả");
        jPanel5.add(btAll);
        btAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel1.removeAll();
                insertAllButtonFood();
                jPanel1.updateUI();
            }
        });
        for (DanhMuc dm : dsdm) {
            JButton bt = new JButton();
            bt.setText(dm.getDM_TEN());
            jPanel5.add(bt);
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanel1.removeAll();
                    ArrayList<Mon> ds_mon_dm = MonDAO.layDanhSachMonAnTheoDM(dm.getDM_ID());
                    insertButtonFood(ds_mon_dm, jPanel1, jPanel6);
                    jPanel1.updateUI();
                    jPanel6.updateUI();
                }
            });
        }
    }
    
    public int laySoLuongMon(String ten){
        int sl = 0;
        for(int i = 0 ; i < ban.getDsOrder().size() ; i++){
            Order o = ban.getDsOrder().get(i);
            if(o.getTenMon().equals(ten)){
                sl+= o.getSoLuong();
            }
        }
        return sl;
    }
    
    public void themOrder(HoaDon hd){
        ArrayList<Order> listOrderGom = gomOrderHienThi();
        for(int i = 0 ; i < listOrderGom.size() ; i++){
            Order o = listOrderGom.get(i);
            int soLuongDat = OrderDAO.laySoLuongOrder1MonTheoHoaDon(hd, new Mon(o.getTenMon()));
            int soLuongTam = o.getSoLuong();
            if(soLuongTam != soLuongDat){
                int soLuong = soLuongTam - soLuongDat;
                for(int j = ban.getDsOrder().size() - 1 ; j >= 0 ; j--){
                    Order or = ban.getDsOrder().get(j);
                    if(or.getTenMon().equals(o.getTenMon())){
                        OrderDAO.themMoiOrder(or, hd);
                        soLuong-= or.getSoLuong();
                    }
                    if(soLuong == 0){
                        break;
                    }
                }
            }
        }
        ban.setDsOrder(OrderDAO.layOrderDatCuaHoaDon(new HoaDon(ban.getHD_ID())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lbSoBanDaChon = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnGoiMon = new swing.button();
        txtNhanVien = new swing.searchText();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(jPanel5);

        jPanel1.setLayout(new java.awt.GridLayout(38, 2, 0, 40));
        jScrollPane2.setViewportView(jPanel1);

        lbSoBanDaChon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jPanel6.setLayout(new java.awt.GridLayout(10, 1, 0, 10));
        jScrollPane3.setViewportView(jPanel6);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel32.setText("Tạm tính");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel34.setText("Thành tiền");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel35.setText("Nhân viên");

        jButton3.setText("Thanh toán");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("a");

        btnGoiMon.setText("Gọi món");
        btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiMonActionPerformed(evt);
            }
        });

        txtNhanVien.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(btnGoiMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGoiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSoBanDaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSoBanDaChon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(HoaDonDAO.kiemTraTatCaHoaDonChuaHoanThanh(new HoaDon(ban.getHD_ID()))){
            JOptionPane.showMessageDialog(frmChonMon.this, "Món chưa được thực hiện xong, không thể thanh toán");
            return;
        }
        
        try{
            HoaDonDAO.thanhToanHoaDon(ban.getB_SOBAN());
            HoaDonDAO.capNhatThanhTienHoaDon(new HoaDon(ban.getHD_ID()));
        } catch(Exception e){
            JOptionPane.showMessageDialog(frmChonMon.this, "Thanh toán thất bại");
        }
        JOptionPane.showMessageDialog(frmChonMon.this, "Thanh toán thành công");
        ban.setDsOrder(new ArrayList<>());
        ban.setHD_ID(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        if(!PhanQuyenDAO.kiemTraCoQuyenGoiMon(main.main.tkhoan)){
            JOptionPane.showMessageDialog(frmChonMon.this, "Bạn không có quyền gọi món");
            return;
        }
        
        if(ban.getHD_ID() == 0){
            HoaDon hd = new HoaDon();
            hd.setMaHD(HoaDonDAO.layMaHoaDonTiepTheo());
            hd.setMaNV(main.main.tkhoan.getTK_NV());
            hd.setSoBan(ban.getB_SOBAN());
            hd.setTrangThai(false);
            
            try{
                HoaDonDAO.themMoiHoaDon(hd);
                ban.setHD_ID(hd.getMaHD());
                themOrder(hd);
                JOptionPane.showMessageDialog(frmChonMon.this, "Thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch(Exception e){
                JOptionPane.showMessageDialog(frmChonMon.this, "Thêm món thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            HoaDon hd = new HoaDon(ban.getHD_ID());
            try{
                themOrder(hd);
                JOptionPane.showMessageDialog(frmChonMon.this, "Thêm món thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch(Exception e){
                JOptionPane.showMessageDialog(frmChonMon.this, "Thêm món thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGoiMonActionPerformed

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
            java.util.logging.Logger.getLogger(frmChonMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmChonMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmChonMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChonMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmChonMon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.button btnGoiMon;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbSoBanDaChon;
    private swing.searchText txtNhanVien;
    // End of variables declaration//GEN-END:variables
}
