/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author VU HOANG
 */
public class scrollbarUI extends BasicScrollBarUI{

    private int THUMB_SIZE = 40;
    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(0, THUMB_SIZE);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(0, THUMB_SIZE);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int orientation = scrollbar.getOrientation();
        int size;
        int x;
        int y;
        int width;
        int height;
        size = trackBounds.width / 2;
        x = trackBounds.x + ((trackBounds.width - size) / 2);
        y = trackBounds.y;
        width = size;
        height = trackBounds.height;
        g2.setColor(new Color(240, 240, 240));
        g2.fillRect(x, y, width, height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = thumbBounds.x;
        int y = thumbBounds.y;
        int width = thumbBounds.width;
        int height = thumbBounds.height;
        y+= 8;
        height-= 16;
        g2.setColor(scrollbar.getForeground());
        g2.fillRoundRect(x, y, width, height, 10, 10);
    }
    
    private class ScrollBarButton extends JButton {
        public ScrollBarButton(){
            setBorder(BorderFactory.createEmptyBorder());
        }
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ScrollBarButton();
    }
    
    
}
