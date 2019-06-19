package com.fvaldeon.registrohomicidios.base;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by PROFESOR on 17/01/2019.
 */
@Entity
@Table(name = "victimas", catalog = "")
public class Victima {

    private int id;
    private String nombre;
    private boolean esHombre;
    private Date fechaDefuncion;
    private String causaMuerte;
    private Homicida homicida;

    public Victima(String nombre, boolean esHombre, LocalDate fechaDefuncion, String causaMuerte, Homicida homicida) {
        this.nombre = nombre;
        this.esHombre = esHombre;
        this.fechaDefuncion = Date.valueOf(fechaDefuncion);
        this.causaMuerte = causaMuerte;
        this.homicida = homicida;
    }

    public Victima() {
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

    @Column(name = "genero_masculino")
    public boolean isEsHombre() {
        return esHombre;
    }

    public void setEsHombre(boolean esHombre) {
        this.esHombre = esHombre;
    }

    @Column(name = "fecha_defuncion")
    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    @Column(name = "causa_muerte")
    public String getCausaMuerte() {
        return causaMuerte;
    }

    public void setCausaMuerte(String causaMuerte) {
        this.causaMuerte = causaMuerte;
    }

    @ManyToOne
    @JoinColumn(name = "id_homicida")
    public Homicida getHomicida() {
        return homicida;
    }

    public void setHomicida(Homicida homicida) {
        this.homicida = homicida;
    }

    @Override
    public String toString() {
        return nombre + " - " + fechaDefuncion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Victima victima = (Victima) o;

        if (id != victima.id) return false;
        if (esHombre != victima.esHombre) return false;
        if (nombre != null ? !nombre.equals(victima.nombre) : victima.nombre != null) return false;
        if (fechaDefuncion != null ? !fechaDefuncion.equals(victima.fechaDefuncion) : victima.fechaDefuncion != null)
            return false;
        return causaMuerte != null ? causaMuerte.equals(victima.causaMuerte) : victima.causaMuerte == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (esHombre ? 1 : 0);
        result = 31 * result + (fechaDefuncion != null ? fechaDefuncion.hashCode() : 0);
        result = 31 * result + (causaMuerte != null ? causaMuerte.hashCode() : 0);
        return result;
    }
}
