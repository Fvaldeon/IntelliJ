package com.fvaldeon.colegio;

import com.fvaldeon.colegio.mvc.Controlador;
import com.fvaldeon.colegio.mvc.Modelo;
import com.fvaldeon.colegio.mvc.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
