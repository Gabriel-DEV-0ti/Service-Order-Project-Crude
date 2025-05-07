package VdeVigilancia.Projeto_OS.dao;

import VdeVigilancia.Projeto_OS.cliente.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    public void inserirCliente(cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, cpf, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = VdeVigilancia.Projeto_OS.db.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());

            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

