package com.fvaldeon.registrohomicidios.util;

import org.hibernate.cfg.Configuration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class Util {

    public static final String RUTA_DIRECTORIO_IMAGENES = System.getProperty("user.home") + "\\Pictures";
    public static final int INFORME_VICTIMAS = 1;
    public static final int INFORME_HOMICIDAS_COMPLETO = 2;
    public static final int INFORME_HOMICIDAS_CONCRETO = 3;
    public static final int INFORME_VICTIMAS_SIN_BD = 4;
    private static final String BBDD = "registro_homicidas?createDatabaseIfNotExist=true";
    private static final String FICHERO_CONFIGURACION_SERVER = "database-server.conf";

    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void copiarFoto(File origen, File destino) throws IOException {
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static boolean existeDirectorioImagenes(){
        File directorio = new File("images");
        if(directorio.exists()){
            return true;
        }

        return directorio.mkdir();
    }

    public static boolean existeFicheroConfiguracionBD(){
        File fichero = new File(FICHERO_CONFIGURACION_SERVER);
        if(fichero.exists()){
            return true;
        }
        return false;
    }

    public static void copiarFotoRedimensionada(File inputFile, File destino, int ancho, int alto)
            throws IOException {
        // reads input image
        BufferedImage inputImage = ImageIO.read(inputFile);

        //calcular proporcion correcta
        int altoOriginal = inputImage.getHeight();
        int anchoOriginal = inputImage.getWidth();

        if(altoOriginal > anchoOriginal){

        } else {

        }

        // creates output image
        BufferedImage outputImage = new BufferedImage(ancho, alto, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, ancho, alto, null);
        g2d.dispose();

        // writes to output file
        ImageIO.write(outputImage, "png", destino);
    }

    public static void modificarConfiguracion(Configuration configuracion) {
        Properties ficheroConfiguracion = new Properties();
        try {
            ficheroConfiguracion.load(new FileInputStream(FICHERO_CONFIGURACION_SERVER));
            String usuario = ficheroConfiguracion.getProperty("user");
            String password = ficheroConfiguracion.getProperty("password");
            String url = ficheroConfiguracion.getProperty("server") + ":" + ficheroConfiguracion.getProperty("port")
                    + "/" +BBDD;

            configuracion.setProperty("hibernate.connection.username", usuario);
            configuracion.setProperty("hibernate.connection.password", password);
            configuracion.setProperty("hibernate.connection.url", url);
            System.err.println("INFORMACION: Server properties loaded");
        } catch (FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
