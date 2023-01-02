package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;

public class JanelaCarro {

    private MotoristaRepository repositorio;
    
    public JanelaCarro(MotoristaRepository repositorio) {
        this.repositorio = repositorio;
    }

}
