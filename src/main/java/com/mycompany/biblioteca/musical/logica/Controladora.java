package com.mycompany.biblioteca.musical.logica;

import com.mycompany.biblioteca.musical.persistencia.ControladoraPersistencia;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void agregarBanda(String nombre, String pais) {
        Banda nueva = new Banda();
        nueva.setNombre(nombre);
        
        Pais encontrado = this.encontrarPais(pais);
        if(encontrado != null)
            nueva.setNacionalidad(encontrado);
        
        controlPersis.agregarBandaBD(nueva);
    }

    public List<Banda> traerBandas() {
        List<Banda> listaBandas = controlPersis.traerBandasBD();       
        Comparator<Banda> comparadorPorNombre = Comparator.comparing(Banda::getNombre);       
        Collections.sort(listaBandas, comparadorPorNombre);
        
        return listaBandas;
    }

    public void agregarDisco(String nombre, String genero, int anio, int calificacion, String banda) {
        Disco nuevo = new Disco();
        nuevo.setNombre(nombre);
        nuevo.setAnio(anio);
        nuevo.setCalificacion(calificacion);
        
        Banda encontrada = this.encontrarBanda(banda);
        if(encontrada != null)
            nuevo.setAutor(encontrada);
        
        Genero encontrado = this.encontrarGenero(genero);
        if(encontrado != null)
            nuevo.setGenero(encontrado);
        
        controlPersis.agregarDiscoBD(nuevo);
        
    }

    private Banda encontrarBanda(String banda) {
        List<Banda> listaBandas = controlPersis.traerBandasBD();
        
        for(Banda actual : listaBandas){
            if(actual.getNombre().equals(banda))
                return actual;
        }
        return null;
    }

    public List<Disco> traerDiscos(int id) {
        Banda encontrada = this.traerBanda(id);
        
        List<Disco> listaDiscos = encontrada.getDiscografia();     
        Comparator<Disco> comparadorPorAnio = Comparator.comparingInt(Disco::getAnio);       
        Collections.sort(listaDiscos, comparadorPorAnio);
        
        return listaDiscos;
    }


    public void agregarPais(String nombre) {
        Pais nuevo = new Pais();
        nuevo.setNombre(nombre);
        
        controlPersis.agregarPaisBD(nuevo);
    }

    public void agregarGenero(String nombre, String descripcion) {
        Genero nuevo = new Genero();
        nuevo.setNombre(nombre);
        nuevo.setDescripcion(descripcion);
        
        controlPersis.agregarGeneroBD(nuevo);
    }

    public List<Pais> traerPaises() {
        return controlPersis.traerPaisesBD();
    }

    private Pais encontrarPais(String pais) {
        List<Pais> listaPaises = controlPersis.traerPaisesBD();
        
        for(Pais actual : listaPaises){
            if(actual.getNombre().equals(pais))
                return actual;
        }
        
        return null;
    }

    private Genero encontrarGenero(String genero) {
        List<Genero> listaGeneros = controlPersis.traerGenerosBD();
        
        for(Genero actual : listaGeneros){
            if(actual.getNombre().equals(genero))
                return actual;
        }
        
        return null;
    }

    public List<Genero> traerGeneros() {
        return controlPersis.traerGenerosBD();
    }

    public List<Disco> filtrarDiscos(String genero) {
        Genero encontrado = this.encontrarGenero(genero);       
        List<Disco> listaDiscos = encontrado.getListaDiscos();
        Comparator<Disco> comparadorPorAnio = Comparator.comparingInt(Disco::getAnio);     
        Collections.sort(listaDiscos, comparadorPorAnio);
        
        return listaDiscos;
        
    }

    public List<Banda> traerBandas(String pais) {
        Pais encontrado = this.encontrarPais(pais);      
        List<Banda> listaBandas = encontrado.getListaBandas();       
        Comparator<Banda> comparadorPorNombre = Comparator.comparing(Banda::getNombre);       
        Collections.sort(listaBandas, comparadorPorNombre);
        
        return listaBandas;
    }

    public boolean paisExiste(String nombre) {
        boolean existe = false;
        Pais encontrado = this.encontrarPais(nombre);
        if(encontrado != null)
            existe = true;
        
        return existe;
    }

    public boolean existeNombreBanda(String nombre) {
        boolean existe = false;
        Banda encontrada = this.encontrarBanda(nombre);
        if(encontrada != null)
            existe = true;
        
        return existe;
    }

    public boolean existeGeneroNombre(String nombre) {
        boolean existe = false;
        Genero encontrado = this.encontrarGenero(nombre);
        if(encontrado != null)
            existe = true;
        
        return existe;
    }

    public Banda traerBanda(int id) {
        return controlPersis.traerBandaBD(id);
    }

    
}
