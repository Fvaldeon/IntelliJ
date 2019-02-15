package com.fvaldeon.colegio.base;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alumnos")
public class Alumno {

    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Set<Asignatura> asignaturas;

    public Alumno() {

    }

    public Alumno(String nombre, String apellidos, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        asignaturas = new HashSet<>();
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

    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "alumno_asignatura", joinColumns = {@JoinColumn(name = "id_alumno")},
            inverseJoinColumns = {@JoinColumn(name = "id_asignatura")})
    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }

    /*
        Metodos utilitarios para actualizar los objetos
     */

    public void matricularEnAsignaturas(Collection<Asignatura> asignaturas){
        for(Asignatura asignatura : asignaturas){
            asignatura.getAlumnos().add(this);
        }
        this.asignaturas.addAll(asignaturas);
    }

    public void desmatricularDeAsignaturas(Collection<Asignatura> asignaturas){
        for(Asignatura asignatura : asignaturas){
            asignatura.getAlumnos().remove(this);
        }
        this.asignaturas.removeAll(asignaturas);
    }
}
