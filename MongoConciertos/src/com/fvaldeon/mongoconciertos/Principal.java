package com.fvaldeon.mongoconciertos;

import com.fvaldeon.mongoconciertos.mvc.Controlador;
import com.fvaldeon.mongoconciertos.mvc.Modelo;
import com.fvaldeon.mongoconciertos.mvc.Vista;

public class Principal {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);

    }

}
