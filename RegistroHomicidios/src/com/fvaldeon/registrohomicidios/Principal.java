package com.fvaldeon.registrohomicidios;

import com.fvaldeon.registrohomicidios.mvc.Controlador;
import com.fvaldeon.registrohomicidios.mvc.Modelo;
import com.fvaldeon.registrohomicidios.mvc.SplashScreen;
import com.fvaldeon.registrohomicidios.mvc.Vista;
import com.fvaldeon.registrohomicidios.util.Util;

/**
 * Created by PROFESOR on 17/01/2019.
 */
public class Principal {
    public static void main(String[] args) {
        if(Util.existeDirectorioImagenes()) {
            //Creo un hilo de ejecución a partir del objeto Runnable
            Thread hilo = new Thread(new SplashScreen());

            //Arranco el hilo de ejecución
            hilo.start();

            //Lanzo la aplicación y conecto
            Vista vista = new Vista();
            Modelo modelo = new Modelo();
            modelo.conectar(true);
            Controlador controlador = new Controlador(vista, modelo);

            try {
                hilo.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Cuando el hilo termina muestro la ventana de la aplicacion
            vista.mostrar();
        }else{
            Util.mensajeError("No se ha podido crear el directorio de imagenes");
        }
    }
}
