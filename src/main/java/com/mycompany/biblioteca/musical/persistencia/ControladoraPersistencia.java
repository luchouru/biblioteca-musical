package com.mycompany.biblioteca.musical.persistencia;

import com.mycompany.biblioteca.musical.logica.Banda;
import com.mycompany.biblioteca.musical.logica.Disco;
import com.mycompany.biblioteca.musical.logica.Genero;
import com.mycompany.biblioteca.musical.logica.Pais;
import java.util.List;


public class ControladoraPersistencia {
    
    BandaJpaController bandaController = new BandaJpaController();
    DiscoJpaController discoController = new DiscoJpaController();
    PaisJpaController paisController = new PaisJpaController();
    GeneroJpaController generoController = new GeneroJpaController();

    public void agregarBandaBD(Banda nueva) {
        bandaController.create(nueva);
    }

    public List<Banda> traerBandasBD() {
        return bandaController.findBandaEntities();
    }

    public void agregarDiscoBD(Disco nuevo) {
        discoController.create(nuevo);
    }

    public void agregarPaisBD(Pais nuevo) {
        paisController.create(nuevo);
    }

    public void agregarGeneroBD(Genero nuevo) {
        generoController.create(nuevo);
    }

    public List<Pais> traerPaisesBD() {
        return paisController.findPaisEntities();
    }

    public List<Genero> traerGenerosBD() {
        return generoController.findGeneroEntities();
    }


}
