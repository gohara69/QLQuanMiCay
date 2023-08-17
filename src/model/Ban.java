/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author VU HOANG
 */
public class Ban {
    private int B_SOBAN;
    private String B_VITRI;
    private ArrayList<Order> dsOrder;
    private int HD_ID;

    public int getHD_ID() {
        return HD_ID;
    }

    public void setHD_ID(int HD_ID) {
        this.HD_ID = HD_ID;
    }
    
    public Ban(int B_SOBAN){
        this.B_SOBAN = B_SOBAN;
        dsOrder = new ArrayList<>();
    }

    public Ban(int B_SOBAN, int HD_ID) {
        this.B_SOBAN = B_SOBAN;
        this.HD_ID = HD_ID;
        dsOrder = new ArrayList<>();
    }

    public ArrayList<Order> getDsOrder() {
        return dsOrder;
    }

    public void setDsOrder(ArrayList<Order> dsOrder) {
        this.dsOrder = dsOrder;
    }

    public Ban() {
        dsOrder = new ArrayList<>();
    }
    
    public void addOrder(Order o){
        dsOrder.add(o);
    }
    
    public void removeOrder(Order o){
        dsOrder.remove(o);
    }

    public Ban(int B_SOBAN, String B_VITRI) {
        this.B_SOBAN = B_SOBAN;
        this.B_VITRI = B_VITRI;
    }

    public int getB_SOBAN() {
        return B_SOBAN;
    }

    public void setB_SOBAN(int B_SOBAN) {
        this.B_SOBAN = B_SOBAN;
    }

    public String getB_VITRI() {
        return B_VITRI;
    }

    public void setB_VITRI(String B_VITRI) {
        this.B_VITRI = B_VITRI;
    }
}
