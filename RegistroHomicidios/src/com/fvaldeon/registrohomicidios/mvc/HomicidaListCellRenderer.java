package com.fvaldeon.registrohomicidios.mvc;

import com.fvaldeon.registrohomicidios.base.Homicida;
import com.fvaldeon.registrohomicidios.util.Util;

import javax.swing.*;
import java.awt.*;

public class HomicidaListCellRenderer extends JLabel implements ListCellRenderer {
    private static final ImageIcon IMAGEN_VACIA = new ImageIcon();
    public HomicidaListCellRenderer(){

        setOpaque(true);
        setIconTextGap(12);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Homicida homicida = (Homicida) value;
        setText(homicida.toString());
        if(homicida.getNombreFoto() != null){
            ImageIcon image =  new ImageIcon(Util.IMAGES_MINIATURAS+ homicida.getNombreFoto());
            image.setImage(image.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH));
            setIcon(image);
        }else{
            setIcon(IMAGEN_VACIA);
        }

        //Colores por defecto
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
