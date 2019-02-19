package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;
import com.fvaldeon.mongoconciertos.base.Concierto;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    JFrame frame;
    JTabbedPane tabbedPane1;
    JPanel panel1;
    JTextField nombreArtistaTxt;
    JTextField estiloArtistaTxt;
    JTextField cacheArtistaTxt;
    JList<Artista> listArtistas;
    JButton eliminarArtistaBtn;
    JButton altaArtistaBtn;
    JButton modificarArtistaBtn;
    JTextField nombreConciertoTxt;
    JList<Artista> listArtistasDisponibles;
    JList<Concierto> listConciertos;
    JButton eliminarConciertoBtn;
    JButton altaConciertoBtn;
    JList<Artista> listArtistasConcierto;
    JButton anadirArtistaBtn;DatePicker conciertosDatePicker;
    DefaultListModel<Artista> dlmArtista;
    DefaultListModel<Concierto> dlmConciertos;
    DefaultListModel<Artista> dlmArtistasConcierto;
    DefaultListModel<Artista> dlmArtistasDisponibles;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        dlmArtista = new DefaultListModel<>();
        listArtistas.setModel(dlmArtista);
        dlmArtistasConcierto = new DefaultListModel<>();
        listArtistasConcierto.setModel(dlmArtistasConcierto);
        dlmArtistasDisponibles = new DefaultListModel<>();
        listArtistasDisponibles.setModel(dlmArtistasDisponibles);
        dlmConciertos = new DefaultListModel<>();
        listConciertos.setModel(dlmConciertos);
    }


}
