package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroCarro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroMotorista;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaCadastroUsuario;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaLogin;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaOpcaoCadastro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaPrincipal;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaPrincipalCaroneiro;
import ifpr.pgua.eic.projetointegrador.controllers.JanelaUsuario;
import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCMotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.daos.JDBCUsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.utils.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

    private FabricaConexoes fabricaConexoes = FabricaConexoes.getInstance();

    private MotoristaDAO motoristaDAO;
    private MotoristaRepository motoristaRepository;

    private UsuarioDAO usuarioDAO;
    private UsuarioRepository usuarioRepository;

    @Override
    public void init() throws Exception {
        super.init();

        motoristaDAO = new JDBCMotoristaDAO(fabricaConexoes);
        motoristaRepository = new MotoristaRepository(motoristaDAO);

        usuarioDAO = new JDBCUsuarioDAO(fabricaConexoes);
        usuarioRepository = new UsuarioRepository(usuarioDAO);
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
                new ScreenRegistryFXML(getClass(), "fxml/usuario.fxml", (o) -> new JanelaUsuario(motoristaRepository, usuarioRepository)));

        registraTela("LOGIN",
                new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o) -> new JanelaLogin(motoristaRepository, usuarioRepository)));

        registraTela("OPCAO CADASTRO",
                new ScreenRegistryFXML(getClass(), "fxml/opcao-cadastro.fxml", (o) -> new JanelaOpcaoCadastro()));

        registraTela("CADASTRO CARROS", new ScreenRegistryFXML(getClass(), "fxml/cadastro-carro.fxml",
                (o) -> new JanelaCadastroCarro(motoristaRepository)));

        registraTela("CADASTRO USUARIO", new ScreenRegistryFXML(getClass(), "fxml/cadastro-usuario.fxml",
                (o) -> new JanelaCadastroUsuario(usuarioRepository)));

        registraTela("CADASTRO MOTORISTA", new ScreenRegistryFXML(getClass(), "fxml/cadastro-motorista.fxml",
                (o) -> new JanelaCadastroMotorista(motoristaRepository)));

    }

}