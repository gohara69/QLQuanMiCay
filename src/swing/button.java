/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author VU HOANG
 */
public class button extends JButton{
    public button(){
        setColor(new Color(255,69,0));
        setBackground(new Color(255,69,0));
        colorOver = new Color(255, 100, 36);
        colorClick = new Color(255, 129, 64);
        colorBorder = new Color(255,69,0);
        Font myFont1 = new Font("Serif", Font.BOLD, 14);
        this.setFont(myFont1);
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(over){
                    setBackground(colorOver);

                } else {
                    setBackground(color);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }   
        });
    }
    
    public button(String text){
        this.setText(text);
        setColor(new Color(255,69,0));
        setBackground(new Color(255,69,0));
        colorOver = new Color(255, 100, 36);
        colorClick = new Color(255, 129, 64);
        colorBorder = new Color(255, 158, 90);
        Font myFont1 = new Font("Serif", Font.BOLD, 14);
        this.setFont(myFont1);
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(over){
                    setBackground(colorOver);

                } else {
                    setBackground(color);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }   
        });
    }
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color colorBorder;
    private int radius;

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return colorBorder;
    }

    public void setBorderColor(Color colorBorder) {
        this.colorBorder = colorBorder;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorBorder);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 15, 15);
        super.paintComponent(g); 
    }
    
    
}
