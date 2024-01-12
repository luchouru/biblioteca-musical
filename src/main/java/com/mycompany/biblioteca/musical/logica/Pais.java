package com.mycompany.biblioteca.musical.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pais implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "nacionalidad")
    private List<Banda> listaBandas;

    public Pais() {
    }
    
    public Pais(int id, String nombre, List<Banda> listaBandas) {
        this.id = id;
        this.nombre = nombre;
        this.listaBandas = listaBandas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Banda> getListaBandas() {
        return listaBandas;
    }

    public void setListaBandas(List<Banda> listaBandas) {
        this.listaBandas = listaBandas;
    }
    
    
}
