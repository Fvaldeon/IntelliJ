package com.fvaldeon.registrohomicidios.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by PROFESOR on 17/01/2019.
 */
@Entity
@Table(name = "homicidas", catalog = "")
public class Homicida {
    private int id;
    private String apodo;
    private String arma;
    private boolean asesinoSerie;
    private int annosCarcel;
    private String nombreFoto;
    private List<Victima> victimas;

    public Homicida() {
    }

    public Homicida(String apodo, String arma, boolean asesinoSerie, int annosCarcel) {
        this.apodo = apodo;
        this.arma = arma;
        this.asesinoSerie = asesinoSerie;
        this.annosCarcel = annosCarcel;
        this.victimas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "apodo")
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Column(name = "arma")
    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    @Column(name = "asesino_serie")
    public boolean isAsesinoSerie() {
        return asesinoSerie;
    }

    public void setAsesinoSerie(boolean asesinoSerie) {
        this.asesinoSerie = asesinoSerie;
    }

    @Column(name = "annos_carcel")
    public int getAnnosCarcel() {
        return annosCarcel;
    }

    public void setAnnosCarcel(int annosCarcel) {
        this.annosCarcel = annosCarcel;
    }

    @Column(name = "ruta_foto")
    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    @OneToMany(mappedBy = "homicida", cascade = CascadeType.ALL)
    public List<Victima> getVictimas() {
        return victimas;
    }

    public void setVictimas(List<Victima> victimas) {
        this.victimas = victimas;
    }

    /*
       Métodos utilitarios para asegurarnos que los objetos mapeados
       actualicen sus relaciones. Esta gestión es irrelevante para la bbdd.
    */
    public void addVictima(Victima victima){
        victimas.add(victima);
        victima.setHomicida(this);
    }

    public void removeVictima(Victima victima){
        victima.setHomicida(null);
        if(victimas.contains(victima)) {
            victimas.remove(victima);
        }
    }

    public void removeAllVictimas(){
        for(Victima victima : victimas){
            victima.setHomicida(null);
        }
        victimas.clear();
    }

    @Override
    public String toString() {
        return apodo + " - " + arma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Homicida homicida = (Homicida) o;

        if (id != homicida.id) return false;
        if (asesinoSerie != homicida.asesinoSerie) return false;
        if (annosCarcel != homicida.annosCarcel) return false;
        if (!apodo.equals(homicida.apodo)) return false;
        return arma.equals(homicida.arma);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + apodo.hashCode();
        result = 31 * result + arma.hashCode();
        result = 31 * result + (asesinoSerie ? 1 : 0);
        result = 31 * result + annosCarcel;
        return result;
    }
}
