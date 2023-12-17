package com.mycompany.biblioteca.musical.logica;

import com.mycompany.biblioteca.musical.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void agregarBanda(String nombre, String pais) {
        Banda nueva = new Banda();
        nueva.setNombre(nombre);
        nueva.setPais(pais);
        
        controlPersis.agregarBandaBD(nueva);
    }

    public List<Banda> traerBandas() {
        return controlPersis.traerBandasBD();
    }

    public void agregarDisco(String nombre, String genero, int anio, int calificacion, String banda) {
        Disco nuevo = new Disco();
        nuevo.setNombre(nombre);
        nuevo.setAnio(anio);
        nuevo.setGenero(genero);
        nuevo.setCalificacion(calificacion);
        
        Banda encontrada = new Banda();
        encontrada = this.encontrarBanda(banda);
        if(encontrada != null)
            nuevo.setAutor(encontrada);
        
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

    public List<Disco> traerDiscos(String nombreBanda) {
        Banda encontrada = new Banda();
        encontrada = this.encontrarBanda(nombreBanda);
        
        List<Disco> listaDiscos = encontrada.getDiscografia();
        return listaDiscos;
    }

    
}
