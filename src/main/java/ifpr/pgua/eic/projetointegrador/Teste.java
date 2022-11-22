package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCMotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;

public class Teste {
    
    public static void main(String[] args) {
        
        FabricaConexoes fabrica = FabricaConexoes.getInstance();

        MotoristaDAO dao = new JDBCMotoristaDAO(fabrica);

        Motorista motorista = new Motorista(87595, "Roberto", "professor", "cerveja", 38, "tads", 444555, "robertolandia", 5453, 10);

    }


}
