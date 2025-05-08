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
    Button BotaoEntrar, BotaoCadastrar, BotaoEditar, BotaoExcluir;

    @FXML
    MenuItem BotaoCliente, BotaoUsuario, BotaoOS;

    @FXML
    protected void cadastrarCliente () {
        cliente cliente = new cliente();
        cliente.setNome(Nome.getText());
        cliente.setEmail(email.getText());
        cliente.setCpf(CPF.getText());
        cliente.setTelefone(Tel.getText());
        cliente.setAparelho(cliente.getAparelho());

        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(cliente);
    }

    @FXML
    protected void editarCliente () {
    }

    @FXML
    protected  void excluirCliente () {
        int id = 1; // Substitua por seleção real do cliente
        if (ClienteDAO.deletarCliente(id)) {
            showAlert("Sucesso", "Cliente deletado com sucesso!");
        } else {
            showAlert("Erro", "Erro ao deletar o cliente.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
