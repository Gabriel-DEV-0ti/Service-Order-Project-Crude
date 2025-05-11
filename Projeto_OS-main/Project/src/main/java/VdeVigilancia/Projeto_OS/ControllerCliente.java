package VdeVigilancia.Projeto_OS;
import VdeVigilancia.Projeto_OS.cliente.cliente;
import VdeVigilancia.Projeto_OS.dao.ClienteDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ControllerCliente {
    @FXML
    Button BotaoEntrar, BotaoCadastrar, BotaoEditar, BotaoExcluir, BotaoPesquisar;

    // CAMPOS DE TEXTO
    @FXML private TextField Nome;
    @FXML private TextField email;
    @FXML private TextField CPF;
    @FXML private TextField Telefone;
    @FXML private TextField Aparelho;
    @FXML private TextField Pesquisar;


    // TREE TABLEVIEW E COLUNAS
    @FXML private TreeTableView<cliente> tabelaClientes;
    @FXML private TreeTableColumn<cliente, String> colunaNome;
    @FXML private TreeTableColumn<cliente, String> colunaEmail;
    @FXML private TreeTableColumn<cliente, String> colunaCPF;
    @FXML private TreeTableColumn<cliente, String> colunaTelefone;
    @FXML private TreeTableColumn<cliente, String> colunaAparelho;

    @FXML
    protected void cadastrarCliente () {
        cliente cliente = new cliente();
        cliente.setNome(Nome.getText());
        cliente.setEmail(email.getText());
        cliente.setCpf(CPF.getText());
        cliente.setTelefone(Telefone.getText());
        cliente.setAparelho(Aparelho.getText());

        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(cliente);
    }

    @FXML
    protected void editarCliente () {
        TreeItem<cliente> itemSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            cliente selecionado = itemSelecionado.getValue();

            cliente clienteEditado = new cliente();
            clienteEditado.setId(selecionado.getId());
            clienteEditado.setNome(Nome.getText());
            clienteEditado.setEmail(email.getText());
            clienteEditado.setCpf(CPF.getText());
            clienteEditado.setTelefone(Telefone.getText());
            clienteEditado.setAparelho(Aparelho.getText());

            ClienteDAO dao = new ClienteDAO();
            boolean sucesso = dao.editarCliente(clienteEditado);

            if (sucesso) {
                showAlert("Sucesso", "Cliente editado com sucesso!");
                carregarClientes(); // Atualiza a tabela
            } else {
                showAlert("Erro", "Erro ao editar o cliente.");
            }
        } else {
            showAlert("Aviso", "Selecione um cliente na tabela para editar.");
        }
    }

    @FXML
    protected  void excluirCliente () {
        TreeItem<cliente> itemSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            int id = itemSelecionado.getValue().getId();
            if (ClienteDAO.deletarCliente(id)) {
                showAlert("Sucesso", "Cliente deletado com sucesso!");
                carregarClientes(); // Atualiza a tabela
            } else {
                showAlert("Erro", "Erro ao deletar o cliente.");
            }
        } else {
            showAlert("Aviso", "Selecione um cliente na tabela para excluir.");
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
    private void initialize() {
        assert colunaNome != null : "colunaNome não foi injetada. Verifique fx:id no FXML";
        assert colunaEmail != null : "colunaEmail não foi injetada. Verifique fx:id no FXML";
        assert colunaCPF != null : "colunaCPF não foi injetada. Verifique fx:id no FXML";
        assert colunaTelefone != null : "colunaTelefone não foi injetada. Verifique fx:id no FXML";
        assert colunaAparelho != null : "colunaAparelho não foi injetada. Verifique fx:id no FXML";

        colunaNome.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getNome()));
        colunaEmail.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getEmail()));
        colunaCPF.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getCpf()));
        colunaTelefone.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getTelefone()));
        colunaAparelho.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAparelho()));

        carregarClientes();
    }

    private void carregarClientes() {
        ClienteDAO dao = new ClienteDAO();
        List<cliente> lista = dao.buscarTodos();  // Método no DAO que retorna List<cliente>

        TreeItem<cliente> raiz = new TreeItem<>(new cliente()); // item "oculto" na raiz

        for (cliente c : lista) {
            raiz.getChildren().add(new TreeItem<>(c));
        }

        tabelaClientes.setRoot(raiz);
        tabelaClientes.setShowRoot(false);
    }

    @FXML
    protected void pesquisarCliente() {
        String termo = Pesquisar.getText();
        ClienteDAO dao = new ClienteDAO();
        List<cliente> clientesFiltrados = dao.pesquisarClientes(termo);

        // Atualiza a TreeTableView com os clientes filtrados
        atualizarTabela(clientesFiltrados);
    }

    private void atualizarTabela(List<cliente> lista) {
        TreeItem<cliente> raiz = new TreeItem<>(new cliente()); // item "oculto" na raiz

        for (cliente c : lista) {
            raiz.getChildren().add(new TreeItem<>(c));
        }

        tabelaClientes.setRoot(raiz);
        tabelaClientes.setShowRoot(false);
    }
}
