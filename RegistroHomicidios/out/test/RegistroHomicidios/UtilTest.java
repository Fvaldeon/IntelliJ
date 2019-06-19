package com.fvaldeon.registrohomicidios.test;

import com.fvaldeon.registrohomicidios.util.Util;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @org.junit.jupiter.api.Test
    void existeDirectorioImagenes() {

        boolean actual = Util.existeDirectorioImagenes();

        File fichero = new File("images");
        boolean esperado = fichero.exists();

        assertEquals(esperado, actual);
    }

}