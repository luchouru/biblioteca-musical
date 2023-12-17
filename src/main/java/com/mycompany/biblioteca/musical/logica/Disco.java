package com.mycompany.biblioteca.musical.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Disco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private int anio;
    private String genero;
    private int calificacion;
    @ManyToOne
    @JoinColumn(name = "fk_autor")
    private Banda autor;

    public Disco() {
    }

    public Disco(int id, String nombre, int anio, String genero, int calificacion, Banda autor) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.genero = genero;
        this.calificacion = calificacion;
        this.autor = autor;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Banda getAutor() {
        return autor;
    }

    public void setAutor(Banda autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
    
    
    
}
