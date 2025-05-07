package VdeVigilancia.Projeto_OS;
import VdeVigilancia.Projeto_OS.cliente.cliente;
import VdeVigilancia.Projeto_OS.dao.ClienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class Controller {
    @FXML
    Button BotaoCadastro, BotaoEntrar, BotaoCadastrar;

    @FXML
    MenuItem BotaoCliente, BotaoUsuario, BotaoOS;

    @FXML
    protected void cadastrarCliente () {
        cliente cliente = new cliente();
        cliente.setNome(Nome.getText());
        cliente.setEmail(email.getText());
        cliente.setCpf(CPF.getText());
        cliente.setTelefone(Tel.getText());

        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(cliente);
    }

    @FXML
    protected void abrirTelaCliente () {
        changeScreen(BotaoCliente, "/TelaClientes.fxml");
    }

    @FXML
    protected void abrirTelaInicio () {
        changeScreen(BotaoEntrar, "/TelaInicio.fxml");
    }

    @FXML
    protected void abrirTelaOS () {changeScreen(BotaoOS, "/TelaOS.fxml"); }

    @FXML
    protected void abrirTelaUsuario () {changeScreen(BotaoUsuario, "/TelaUsuario.fxml"); }

    @FXML
    protected void changeScreen(Button currentButton, String screen) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            AnchorPane root = loader.load();
            Scene scene = currentButton.getScene();
            scene.setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @FXML
        protected void changeScreen(MenuItem currentMenu, String screen) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
                AnchorPane root = loader.load();
                Scene scene = currentMenu.getParentPopup().getOwnerWindow().getScene();
                scene.setRoot(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    @FXML private TextField Nome;
    @FXML private TextField email;
    @FXML private TextField CPF;
    @FXML private TextField Tel;

    @FXML
    private void initialize() {
        // Pode deixar vazio ou carregar algo
    }
}
