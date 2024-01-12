package com.mycompany.biblioteca.musical.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Banda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "fk_nacionalidad")
    private Pais nacionalidad;
    @OneToMany (mappedBy = "autor")
    private List <Disco> discografia;

    public Banda() {
    }

    public Banda(int id, String nombre, Pais nacionalidad, List<Disco> discografia) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.discografia = discografia;
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

    public List<Disco> getDiscografia() {
        return discografia;
    }

    public void setDiscografia(List<Disco> discografia) {
        this.discografia = discografia;
    }

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    
    
    
}
