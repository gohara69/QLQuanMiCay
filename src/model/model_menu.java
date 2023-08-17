/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author VU HOANG
 */
public class model_menu {
    String icon;
    String name;
    menuType type;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public menuType getType() {
        return type;
    }

    public void setType(menuType type) {
        this.type = type;
    }

    public model_menu() {
    }

    public model_menu(String icon, String name, menuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource(icon + ".png"));
    }
    
    public static enum menuType{
        TITILE, MENU, EMPTY
    }
}
