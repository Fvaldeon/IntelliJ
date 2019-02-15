package com.fvaldeon.mongoconciertos.base;

import org.bson.types.ObjectId;

public class Artista {
    private ObjectId id;
    private String nombre;
    private String estilo;
    private double cache;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    @Override
    public String toString() {
        return nombre + " - " + estilo;
    }
}
