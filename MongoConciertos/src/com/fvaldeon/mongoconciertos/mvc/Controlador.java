package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

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

    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addActionListener(ActionListener listener){
        vista.altaBtn.addActionListener(listener);
        vista.eliminarBtn.addActionListener(listener);
        vista.modificarBtn.addActionListener(listener);
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
                modelo.insertar(nuevo);
            }
            break;

            case "ModificarArtista":{
                Artista modificado = vista.list1.getSelectedValue();
                if(modificado != null){
                    modificarArtista(modificado);
                    modelo.actualizarArtista(modificado);
                }
            }
            break;

            case "EliminarArtista":{
                Artista eliminado = vista.list1.getSelectedValue();
                if(eliminado != null){
                    modelo.eliminarArtista(eliminado);
                }
            }
            break;

            default:
        }
        refrescarArtistas();
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!vista.list1.isSelectionEmpty()){
            mostrarDatosArtista(vista.list1.getSelectedValue());
        }
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        System.out.println("Desconectando");
        modelo.desconectar();
    }
}
