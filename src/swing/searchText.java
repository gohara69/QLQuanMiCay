/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author VU HOANG
 */
public class searchText extends JTextField{

    public searchText() {
        setBackground(new Color(255, 255, 255, 0));
        setBorder(new EmptyBorder(10,10,10,10));
        setSelectionColor(new Color(220, 204, 182));
        setDisabledTextColor(Color.BLACK);
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    private String hint = "Tìm kiếm ... ";
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (getText().length() == 0) {
            int h = getHeight();
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
        super.paint(g);
    }
}
