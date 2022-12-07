package ifpr.pgua.eic.projetointegrador;

import java.sql.Date;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCMotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;

public class Teste {
    
    public static void main(String[] args) {
        
        FabricaConexoes fabrica = FabricaConexoes.getInstance();

        MotoristaDAO dao = new JDBCMotoristaDAO(fabrica);

        String data = "1978-03-10";

        Motorista motorista = new Motorista("87595", "Roberto", "professor", "cerveja", Date.valueOf(data), 42, "tads", 444555, "av123", 454546);

        dao.create(motorista);
    }
}