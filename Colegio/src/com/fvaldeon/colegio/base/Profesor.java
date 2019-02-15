package com.fvaldeon.colegio.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor {
    private int id;
    private String nombre;
    private String dni;
    private List<Asignatura> asignaturas;

    public Profesor() {

    }

    public Profesor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        asignaturas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return nombre +" - "+ dni;
    }

    /*
        Métodos utilitarios
     */
    public void anadirAsignaturas(Collection<Asignatura> asignaturas){
        for (Asignatura asignatura : asignaturas){
            asignatura.setProfesor(this);
        }
        this.asignaturas.addAll(asignaturas);
    }

    public void desvincularAsignaturas(Collection<Asignatura> asignaturas){
        for (Asignatura asignatura : asignaturas){
            asignatura.setProfesor(null);
        }
        this.asignaturas.removeAll(asignaturas);
    }

    public void desvincularTodas(){
        for(Asignatura asignatura : asignaturas){
            asignatura.setProfesor(null);
        }
        asignaturas.clear();
    }

}
