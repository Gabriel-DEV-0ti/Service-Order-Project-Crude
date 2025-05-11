package VdeVigilancia.Projeto_OS.dao;

import VdeVigilancia.Projeto_OS.cliente.cliente;
import javafx.scene.control.TreeItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public List<cliente> pesquisarClientes(String termo) {
        List<cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? OR cpf LIKE ? OR id LIKE ?";

        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String filtro = "%" + termo + "%"; // Adiciona as porcentagens para o LIKE
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            stmt.setString(3, filtro);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cliente c = new cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setAparelho(rs.getString("aparelho"));
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void inserirCliente(cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, cpf, telefone, aparelho) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getAparelho());

            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean editarCliente(cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, cpf = ?, telefone = ?, aparelho = ? WHERE id = ?";
        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getAparelho());
            stmt.setInt(6, cliente.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<cliente> botaoFiltrar() {
        List<cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cliente c = new cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setAparelho(rs.getString("aparelho")); // se existir essa coluna na tabela
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<cliente> buscarTodos() {
        List<cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = VdeVigilancia.Projeto_OS.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cliente c = new cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setAparelho(rs.getString("aparelho")); // se existir essa coluna na tabela
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}

