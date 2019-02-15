package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;

import javax.swing.*;

public class Vista {
    JFrame frame;
    JTabbedPane tabbedPane1;
    JPanel panel1;
    JTextField nombreArtistaTxt;
    JTextField estiloArtistaTxt;
    JTextField cacheArtistaTxt;
    JList<Artista> list1;
    JButton eliminarBtn;
    JButton altaBtn;
    JButton modificarBtn;
    DefaultListModel<Artista> dlmArtista;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        dlmArtista = new DefaultListModel<>();
        list1.setModel(dlmArtista);
    }


}
