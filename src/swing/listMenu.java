/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import event.eventMenuSelected;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import model.model_menu;

/**
 *
 * @author VU HOANG
 * @param <E>
 */
public class listMenu<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int overIndex = -1;
    
    private eventMenuSelected event;
    public void addEventMenuSelected(eventMenuSelected event){
        this.event = event;
    }
    
    public listMenu(){
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    int index = locationToIndex(e.getPoint());
                    Object o = model.getElementAt(index);
                    if(o instanceof model_menu){
                        model_menu menu = (model_menu)o;
                        if(menu.getType() == model_menu.menuType.MENU){
                            selectedIndex = index;
                            if(event != null){
                                event.selected(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
                super.mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overIndex = -1;
                repaint();
            }
            
            
        });
        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if(index != overIndex){
                    Object o = model.getElementAt(index);
                    if(o instanceof model_menu){
                        model_menu menu = (model_menu)o;
                        if(menu.getType() == model_menu.menuType.MENU){
                            overIndex = index;
                        } else {
                            overIndex = -1;
                        } 
                        repaint();
                    }
                }
            }
            
        });
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer(){
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean focus) {
                model_menu data;
                if(value instanceof model_menu){
                    data = (model_menu)value;
                } else {
                    data = new model_menu("", value + "", model_menu.menuType.EMPTY);
                }
                
                menuItem item = new menuItem(data); 
                item.setSelected(selectedIndex == index);
                item.setOver(overIndex == index);
                return item;
            }
        };
    }
    
    public void addItem(model_menu data){
        model.addElement(data);
    }
}
