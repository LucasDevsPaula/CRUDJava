import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteService {
  public void inserirCliente(Connection connection, Cliente cliente) {
    String sql = "INSERT INTO clientes (nome, email, data_cadastro) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, cliente.getNome());
      stmt.setString(2, cliente.getEmail());
      stmt.setString(3, cliente.getDataCadastro());

      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Cliente inserido com sucesso");
      } else {
        System.out.println("Falha ao inseirir cliente");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao inserir cliente");
      e.printStackTrace();
    }
  }

  public void inserirClienteProduto(Connection connection, Cliente cliente) {
    String sql =
        "INSERT INTO clientes (nome, email, data_cadastro, id_produto) VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, cliente.getNome());
      stmt.setString(2, cliente.getEmail());
      stmt.setString(3, cliente.getDataCadastro());
      stmt.setInt(4, cliente.getIdProduto());

      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Cliente inserido com sucesso");
      } else {
        System.out.println("Falha ao inseirir cliente");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao inserir cliente");
      e.printStackTrace();
    }
  }

   public void buscarClientes(Connection connection) {
    String sql = "SELECT * FROM clientes";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = stmt.executeQuery();
      System.out.println("\n Lista de clientes");
      while (resultSet.next()) {
        int id = resultSet.getInt("id_cliente");
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String dataCadastro = resultSet.getString("data_cadastro");
        int idProduto = resultSet.getInt("id_produto");

        System.out.printf(
                "%d - nome: %s, email: %s, data de cadastro: %s, produto: %d %n",
                id, nome, email, dataCadastro, idProduto);
      }
    } catch (SQLException e) {
      System.out.println("Erro ao lista clientes");
      e.printStackTrace();
    }
  }

  public void buscarClientesProdutos(Connection connection) {
    String sql =
            """   
               SELECT c.id_cliente, c.nome, c.email, c.data_cadastro, p.id_produto, p.nome_produto, p.preco FROM clientes AS c
               INNER JOIN produtos AS p
               ON c.id_produto = p.id_produto
            """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = stmt.executeQuery();
      System.out.println("\nLista de clientes X produtos");
      while (resultSet.next()) {
        int id = resultSet.getInt("id_cliente");
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String dataCadastro = resultSet.getString("data_cadastro");
        int id_produto = resultSet.getInt("id_produto");
        String nomeProduto = resultSet.getString("nome_produto");
        Double preco = resultSet.getDouble("preco");

        System.out.printf(
                "Cliente: %d - nome: %s, email: %s, data de cadastro: %s | Produto: %d - %s - %f %n",
                id, nome, email, dataCadastro, id_produto, nomeProduto, preco);
      }
    } catch (SQLException e) {
      System.out.println("Erro ao lista clientes");
      e.printStackTrace();
    }
  }

  public void adicionarProdutoAoCliente(
          Connection connection, int idCliente, int idProduto) {
    String sql =
            """
             UPDATE clientes SET id_produto = ?
             WHERE id_cliente = ?
            """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, idProduto);
      stmt.setInt(2, idCliente);

      int linhas = stmt.executeUpdate();

      if (linhas > 0) {
        System.out.println("Produto adicionado/atualizado no usuário.");
      } else {
        System.out.println("Não foi possível adicionar/atualizar produto no usuário.");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao adicionar produto ao usuário.");
      e.printStackTrace();
    }
  }

  public void deletarCliente(Connection connection, int idCliente) {
    String sql = "DELETE FROM clientes WHERE id_cliente = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, idCliente);

      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Cliente deletado com sucesso");
      } else {
        System.out.println("Erro ao deletar cliente");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao deletar cliente");
      e.printStackTrace();
    }
  }
}
