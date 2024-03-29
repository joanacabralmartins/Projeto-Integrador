package ifpr.pgua.eic.projetointegrador;

import java.sql.Date;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.SolicitacaoDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.CarroDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCCarroDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCMotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCSolicitacaoDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;

public class Teste {
    
    public static void main(String[] args) {
        
        FabricaConexoes fabrica = FabricaConexoes.getInstance();

        MotoristaDAO dao = new JDBCMotoristaDAO(fabrica);

        String data = "1978-03-10";

        Motorista motorista = new Motorista(0, true, "87595", "Roberto", "professor", "cerveja", Date.valueOf(data), 42, "tads", "444555", "av123", "454546");

        dao.create(motorista);

        List<Motorista> motoristas = dao.listAll();
        for (Motorista m : motoristas) {
            System.out.println(m.getNome());
        }

        CarroDAO carroDAO = new JDBCCarroDAO(fabrica);
        CarroRepository repository = new CarroRepository(carroDAO);
        List<Carro> carros = repository.listar(1);
        for(Carro c : carros) {
            System.out.println(c.getCor());
        }

        SolicitacaoDAO solicitacaoDAO = new JDBCSolicitacaoDAO(fabrica);
        List<SolicitacaoCarona> solicitacoes = solicitacaoDAO.getByMotorista(6);
        for (SolicitacaoCarona s : solicitacoes) {
            System.out.println(s.getId_carona());
        }
    }
}