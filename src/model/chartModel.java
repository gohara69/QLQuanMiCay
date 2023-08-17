/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VU HOANG
 */
public class chartModel {
    private String month;
    private long cost;
    private long profit;

    public chartModel() {
    }

    public chartModel(String month, long cost, long profit) {
        this.month = month;
        this.cost = cost;
        this.profit = profit;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }
    
    
}
