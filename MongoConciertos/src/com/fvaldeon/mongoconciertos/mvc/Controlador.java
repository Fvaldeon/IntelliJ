package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;
import com.fvaldeon.mongoconciertos.base.Concierto;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.Set;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
        modelo.conectar();
        refrescarArtistas();
        refrescarConciertos();

    }



    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listArtistas.addListSelectionListener(listener);
        vista.listConciertos.addListSelectionListener(listener);
    }

    private void addActionListener(ActionListener listener){
        vista.altaArtistaBtn.addActionListener(listener);
        vista.eliminarArtistaBtn.addActionListener(listener);
        vista.modificarArtistaBtn.addActionListener(listener);
        vista.altaConciertoBtn.addActionListener(listener);
        vista.eliminarConciertoBtn.addActionListener(listener);
        vista.anadirArtistaBtn.addActionListener(listener);
    }

    private void addWindowListener(WindowListener adapter){
        vista.frame.addWindowListener(adapter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch(comando){
            case "AltaArtista":{
                Artista nuevo = new Artista();
                modificarArtista(nuevo);
                modelo.insertarArtista(nuevo);
            }
            break;

            case "ModificarArtista":{
                Artista modificado = vista.listArtistas.getSelectedValue();
                if(modificado != null){
                    modificarArtista(modificado);
                    modelo.actualizarArtista(modificado);
                }
            }
            break;

            case "EliminarArtista":{
                Artista eliminado = vista.listArtistas.getSelectedValue();
                if(eliminado != null){
                    modelo.eliminarArtista(eliminado);
                }
            }
            break;

            case "AltaConcierto":{
                Concierto nuevo = new Concierto();
                modificarConcierto(nuevo);
                modelo.insertarConcierto(nuevo);
            }
            break;

            case "EliminarConcierto":{
                Concierto eliminado = vista.listConciertos.getSelectedValue();
                if(eliminado != null){
                    modelo.eliminarConcierto(eliminado);
                }
            }
            break;

            case "AnadirArtista":{
                if(!vista.listConciertos.isSelectionEmpty() && !vista.listArtistasDisponibles.isSelectionEmpty()){
                    vista.listConciertos.getSelectedValue().getArtistas().addAll(vista.listArtistasDisponibles.getSelectedValuesList());
                }
            }
            break;
            default:
        }
        refrescarArtistas();
        refrescarConciertos();
    }

    private void modificarConcierto(Concierto nuevo) {
        nuevo.setNombre(vista.nombreConciertoTxt.getText());
        nuevo.setFecha(vista.conciertosDatePicker.getDate());
    }

    private void refrescarArtistas() {
        vista.dlmArtista.clear();
        for(Artista artista : modelo.obtenerArtistas()){
            vista.dlmArtista.addElement(artista);
        }
    }


    private void modificarArtista(Artista artista){
        artista.setNombre(vista.nombreArtistaTxt.getText());
        artista.setEstilo(vista.estiloArtistaTxt.getText());
        artista.setCache(Double.parseDouble(vista.cacheArtistaTxt.getText()));
    }

    private void mostrarDatosArtista(Artista artista){
        vista.nombreArtistaTxt.setText(artista.getNombre());
        vista.estiloArtistaTxt.setText(artista.getEstilo());
        vista.cacheArtistaTxt.setText(String.valueOf(artista.getCache()));
    }

    private void refrescarConciertos() {
        vista.dlmConciertos.clear();
        for(Concierto concierto : modelo.obtenerConciertos()){
            vista.dlmConciertos.addElement(concierto);
        }
    }

    private void mostrarDatosConcierto(Concierto concierto) {
        vista.nombreConciertoTxt.setText(concierto.getNombre());
        vista.conciertosDatePicker.setDate(concierto.getFecha());
        listarArtistasConcierto(concierto);
        listarArtistasDisponibles(concierto);
    }

    private void listarArtistasConcierto(Concierto concierto) {
        vista.dlmArtistasConcierto.clear();
        for(Artista artista : concierto.getArtistas()){
            vista.dlmArtistasConcierto.addElement(artista);
        }
    }

    private void listarArtistasDisponibles(Concierto concierto){
        vista.dlmArtistasDisponibles.clear();
        Set<Artista> artistasDisponibles = modelo.obtenerArtistas();
        artistasDisponibles.removeAll(concierto.getArtistas());
        for(Artista artista : artistasDisponibles){
            vista.dlmArtistasDisponibles.addElement(artista);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vista.listArtistas && !vista.listArtistas.isSelectionEmpty()){
            mostrarDatosArtista(vista.listArtistas.getSelectedValue());
        }else if(e.getSource() == vista.listConciertos && !vista.listConciertos.isSelectionEmpty()){
            mostrarDatosConcierto(vista.listConciertos.getSelectedValue());
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        System.out.println("Desconectando");
        modelo.desconectar();
    }
}
