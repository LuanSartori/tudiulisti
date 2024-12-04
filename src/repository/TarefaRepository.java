package repository;

import models.Tarefa;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    
    // Criar uma novo tarefa
    public void adicionarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, prazo) VALUES (?, ?, ?)";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // prepara a query para o banco
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getPrazo());

            // verifica se deu certo
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Tarefa adicionado com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar tarefa.");
            e.printStackTrace();
        }
    }

    // Obter todos os tarefas
    public List<Tarefa> obterTodosTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM Tarefas";

        try (
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            // passa pelos dados vindos da query
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("prazo"),
                    rs.getBoolean("status")
                );
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter tarefas.");
            e.printStackTrace();
        }

        return tarefas;
    }

    // Obter tarefas por ID
    public Tarefa obterTarefaPorId(int id) {

        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Tarefa tarefa = null;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // prepara e query e a executa
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // se existir, crie um objeto para ser retornado
            if (rs.next()) {
                tarefa = new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("prazo"),
                    rs.getBoolean("status")
                );
            }


        } catch (SQLException e) {
            System.out.println("Erro ao obter tarefa por ID.");
            e.printStackTrace();
        }

        return tarefa;
    }

    // Atualizar um tarefa
    public void atualizarTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, prazo = ?, status = ? WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // prepara a query
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getPrazo());
            stmt.setBoolean(4, tarefa.getStatus());
            stmt.setInt(5, tarefa.getId());

            // verifica se deu certo
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Tarefa atualizado com sucesso!");
            } else {
                System.out.println("Tarefa não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa.");
            e.printStackTrace();
        }
    }

    // Deletar um tarefa
    public void deletarTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            // prepara a query
            stmt.setInt(1, id);

            // verifica se deu certo
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Tarefa deletado com sucesso!");
            } else {
                System.out.println("Tarefa não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar tarefa.");
            e.printStackTrace();
        }
    }

}
