/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author VU HOANG
 */
public abstract class blankPlotChatRender {
    public abstract String getLabelText(int index);

    public abstract void renderGraphics(blankPlotChart chart, Graphics2D g2, Rectangle2D rectangle);

    public abstract void mouseMove(Rectangle2D rectangle, MouseEvent mouse);
}
