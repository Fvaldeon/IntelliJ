package com.fvaldeon.registrohomicidios.mvc;

import javax.swing.*;

public class SplashScreen extends JDialog implements Runnable{

    private JProgressBar progressBar;
    private JPanel panel1;

    public SplashScreen() {
        setContentPane(panel1);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ocultar(){
        dispose();
    }

    private void setValorCarga(int valor){
        progressBar.setValue(valor);
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 100; i++) {
                Thread.sleep(20);
                setValorCarga(i);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        ocultar();
    }
}
