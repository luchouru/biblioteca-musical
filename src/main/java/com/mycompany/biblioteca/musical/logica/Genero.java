package com.mycompany.biblioteca.musical.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "genero")
    private List<Disco> listaDiscos;

    public Genero() {
    }

    public Genero(int id, String nombre, String descripcion, List<Disco> listaDiscos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaDiscos = listaDiscos;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Disco> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(List<Disco> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }
    
    
    
}
