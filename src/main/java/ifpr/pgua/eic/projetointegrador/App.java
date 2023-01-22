package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroCarona;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroCarro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroMotorista;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroUsuario;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCaronaMotorista;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCaronaMotoristaInicial;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCaronaUsuario;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCarro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaEditarCarona;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaEditarCarro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaEditarMotorista;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaEditarUsuario;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaGerenciarCarros;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaLogin;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaOpcaoCadastro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaPrincipal;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaPrincipalCaroneiro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaSolicitacaoMotorista;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaSolicitacaoMotoristaInicial;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaSolicitacoes;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaUsuario;
import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.PontoDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.SolicitacaoDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.CaronaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.CarroDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCCaronaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCCarroDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCMotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCPontoDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCSolicitacaoDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.PontoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

    private FabricaConexoes fabricaConexoes = FabricaConexoes.getInstance();

    private MotoristaDAO motoristaDAO;
    private MotoristaRepository motoristaRepository;

    private UsuarioDAO usuarioDAO;
    private UsuarioRepository usuarioRepository;

    private CarroDAO carroDAO;
    private CarroRepository carroRepository;

    private CaronaDAO caronaDAO;
    private CaronaRepository caronaRepository;

    private PontoDAO pontoDAO;
    private PontoRepository pontoRepository;

    private SolicitacaoDAO solicitacaoDAO;
    private SolicitacaoRepository solicitacaoRepository;

    @Override
    public void init() throws Exception {
        super.init();

        motoristaDAO = new JDBCMotoristaDAO(fabricaConexoes);
        motoristaRepository = new MotoristaRepository(motoristaDAO);

        usuarioDAO = new JDBCUsuarioDAO(fabricaConexoes);
        usuarioRepository = new UsuarioRepository(usuarioDAO);

        carroDAO = new JDBCCarroDAO(fabricaConexoes);
        carroRepository = new CarroRepository(carroDAO);

        caronaDAO = new JDBCCaronaDAO(fabricaConexoes);
        caronaRepository = new CaronaRepository(caronaDAO);

        pontoDAO = new JDBCPontoDAO(fabricaConexoes);
        pontoRepository = new PontoRepository(pontoDAO);

        solicitacaoDAO = new JDBCSolicitacaoDAO(fabricaConexoes);
        solicitacaoRepository = new SolicitacaoRepository(solicitacaoDAO);
    }

    @Override
    public String getHome() {
        return "LOGIN";
    }

    @Override
    public String getAppTitle() {
        return "IFCAR";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL MOTORISTA",
                new ScreenRegistryFXML(getClass(), "fxml/base.fxml", (o) -> new JanelaPrincipal()));

        registraTela("PRINCIPAL CARONEIRO",
                new ScreenRegistryFXML(getClass(), "fxml/base-caroneiro.fxml", (o) -> new JanelaPrincipalCaroneiro()));

        registraTela("USUARIO",
                new ScreenRegistryFXML(getClass(), "fxml/usuario.fxml", (o) -> new JanelaUsuario(motoristaRepository, usuarioRepository, carroRepository, caronaRepository)));

        registraTela("EDITAR USUARIO",
                new ScreenRegistryFXML(getClass(), "fxml/editar-usuario.fxml", (o) -> new JanelaEditarUsuario(usuarioRepository)));

        registraTela("EDITAR MOTORISTA",
                new ScreenRegistryFXML(getClass(), "fxml/editar-motorista.fxml", (o) -> new JanelaEditarMotorista(motoristaRepository, usuarioRepository)));

        registraTela("EDITAR CARRO",
                new ScreenRegistryFXML(getClass(), "fxml/editar-carro.fxml", (o) -> new JanelaEditarCarro(motoristaRepository, carroRepository)));

        registraTela("LOGIN",
                new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o) -> new JanelaLogin(motoristaRepository, usuarioRepository)));

        registraTela("OPCAO CADASTRO",
                new ScreenRegistryFXML(getClass(), "fxml/opcao-cadastro.fxml", (o) -> new JanelaOpcaoCadastro()));

        registraTela("CARROS", new ScreenRegistryFXML(getClass(), "fxml/carro.fxml",
                (o) -> new JanelaCarro()));

        registraTela("GERENCIAR CARROS", new ScreenRegistryFXML(getClass(), "fxml/gerenciar-carros.fxml",
                (o) -> new JanelaGerenciarCarros(motoristaRepository, carroRepository)));  

        registraTela("CADASTRO CARROS", new ScreenRegistryFXML(getClass(), "fxml/cadastro-carro.fxml",
                (o) -> new JanelaCadastroCarro(motoristaRepository, carroRepository)));

        registraTela("CADASTRO USUARIO", new ScreenRegistryFXML(getClass(), "fxml/cadastro-usuario.fxml",
                (o) -> new JanelaCadastroUsuario(usuarioRepository)));

        registraTela("CADASTRO MOTORISTA", new ScreenRegistryFXML(getClass(), "fxml/cadastro-motorista.fxml",
                (o) -> new JanelaCadastroMotorista(motoristaRepository, usuarioRepository)));

        registraTela("CARONA USUARIO", new ScreenRegistryFXML(getClass(), "fxml/carona-usuario.fxml", 
                (o) -> new JanelaCaronaUsuario(carroRepository, motoristaRepository, usuarioRepository, caronaRepository, pontoRepository, solicitacaoRepository)));

        registraTela("SOLICITACOES", new ScreenRegistryFXML(getClass(), "fxml/solicitacao.fxml", 
                (o) -> new JanelaSolicitacoes(solicitacaoRepository, caronaRepository, usuarioRepository)));

        registraTela("SOLICITACOES MOTORISTA INICIAL", new ScreenRegistryFXML(getClass(), "fxml/solicitacao-motorista-inicial.fxml", 
                (o) -> new JanelaSolicitacaoMotoristaInicial()));

        registraTela("SOLICITACOES MOTORISTA", new ScreenRegistryFXML(getClass(), "fxml/solicitacao-motorista.fxml", 
                (o) -> new JanelaSolicitacaoMotorista(solicitacaoRepository, caronaRepository, usuarioRepository, motoristaRepository)));
             
        registraTela("CARONA MOTORISTA INICIAL", new ScreenRegistryFXML(getClass(), "fxml/carona-motorista-inicial.fxml", 
                (o) -> new JanelaCaronaMotoristaInicial()));

        registraTela("CARONA MOTORISTA", new ScreenRegistryFXML(getClass(), "fxml/carona-motorista.fxml", 
                (o) -> new JanelaCaronaMotorista(carroRepository, motoristaRepository, caronaRepository, pontoRepository, solicitacaoRepository, usuarioRepository)));

        registraTela("CADASTRO CARONA", new ScreenRegistryFXML(getClass(), "fxml/cadastro-carona.fxml", 
                (o) -> new JanelaCadastroCarona(caronaRepository, carroRepository, motoristaRepository)));

/*
        registraTela("GERENCIAR PASSAGEIROS", new ScreenRegistryFXML(getClass(), "fxml/gerenciar-passageiros.fxml", 
                (o) -> new JanelaGerenciarPassageiros(caronaRepository, usuarioRepository, solicitacaoRepository)));
*/
        registraTela("EDITAR CARONA", new ScreenRegistryFXML(getClass(), "fxml/editar-carona.fxml", 
                (o) -> new JanelaEditarCarona(caronaRepository, carroRepository, motoristaRepository, pontoRepository)));                
    }

}