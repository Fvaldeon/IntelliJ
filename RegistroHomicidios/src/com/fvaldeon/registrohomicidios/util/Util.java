package com.fvaldeon.registrohomicidios.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Util {

    public static final String RUTA_DIRECTORIO_IMAGENES = System.getProperty("user.home") + "\\Pictures";
    public static final int INFORME_VICTIMAS = 1;
    public static final int INFORME_HOMICIDAS_COMPLETO = 2;
    public static final int INFORME_HOMICIDAS_CONCRETO = 3;

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
}
