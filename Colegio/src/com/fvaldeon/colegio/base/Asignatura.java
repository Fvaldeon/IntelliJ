package com.fvaldeon.colegio.base;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asignaturas")
public class Asignatura {
    private int id;
    private String nombre;
    private String departamento;
    private int horasSemanales;
    private Profesor profesor;
    private Set<Alumno> alumnos;

    public Asignatura() {

    }

    public Asignatura(String nombre, String departamento, int horasSemanales, Profesor profesor){
        this.nombre = nombre;
        this.departamento = departamento;
        this.horasSemanales = horasSemanales;
        this.profesor = profesor;
        alumnos = new HashSet<>();
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

    @Column(name = "departamento")
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Column(name = "horas_semanales")
    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    @ManyToMany(mappedBy = "asignaturas", cascade = CascadeType.DETACH)
    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return nombre + " - " + departamento + " - Profesor: " + profesor;
    }

    /*
        Metodos Utilitarios
     */
    public void matricularAlumnos(Collection<Alumno> alumnos){
        for (Alumno alumno : alumnos){
            alumno.getAsignaturas().add(this);
        }
        this.alumnos.addAll(alumnos);
    }

    public void desmatricularAlumnos(Collection<Alumno> alumnos){
        for (Alumno alumno : alumnos){
            alumno.getAsignaturas().remove(this);
        }
        this.alumnos.removeAll(alumnos);
    }

    public void desmatricularTodos(){
        for(Alumno alumno : alumnos){
            alumno.getAsignaturas().remove(this);
        }
        alumnos.clear();
    }
}
