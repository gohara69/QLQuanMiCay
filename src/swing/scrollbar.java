/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author VU HOANG
 */
public class scrollbar extends JScrollBar{

    public scrollbar() {
        setUI(new scrollbarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(255,69,0));
        setBackground(Color.WHITE);
    }
}
