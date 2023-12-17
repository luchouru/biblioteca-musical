package com.mycompany.biblioteca.musical.persistencia;

import com.mycompany.biblioteca.musical.logica.Banda;
import com.mycompany.biblioteca.musical.logica.Disco;
import java.util.List;


public class ControladoraPersistencia {
    
    BandaJpaController bandaController = new BandaJpaController();
    DiscoJpaController discoController = new DiscoJpaController();

    public void agregarBandaBD(Banda nueva) {
        bandaController.create(nueva);
    }

    public List<Banda> traerBandasBD() {
        return bandaController.findBandaEntities();
    }

    public void agregarDiscoBD(Disco nuevo) {
        discoController.create(nuevo);
    }

    
    
}
