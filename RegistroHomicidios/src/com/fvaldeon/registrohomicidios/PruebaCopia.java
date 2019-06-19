package com.fvaldeon.registrohomicidios;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PruebaCopia {


    public static void main(String... args) throws IOException {

        File input = new File("C:\\Users\\Fer\\Pictures\\bajaSeguroMotoCopia.png");




        File output = new File("C:\\Users\\Fer\\Pictures\\bajaSeguroMotoCopia2.png");
       resize(input, output, 50, 50);


    }

    private static void resize(File input, File output, int height, int width) throws IOException {
        BufferedImage img = ImageIO.read(input);
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        System.out.println(ImageIO.write(resized, "png", output));

    }
}
