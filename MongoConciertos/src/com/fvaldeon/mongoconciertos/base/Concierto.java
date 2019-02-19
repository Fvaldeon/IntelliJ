package com.fvaldeon.mongoconciertos.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concierto {
    private ObjectId id;
    private String nombre;
    private LocalDate fecha;
    private Set<Artista> artistas;

    public Concierto() {
        artistas = new HashSet<>();
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(Set<Artista> artistas) {
        this.artistas = artistas;
    }

    @Override
    public String toString() {
        return nombre + " " + fecha;
    }
}
