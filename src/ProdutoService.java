import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoService {
  public void inserirProduto(Connection connection, Produto produto) {
    String sql = "INSERT INTO produtos (nome_produto, preco) VALUES (?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, produto.getNome());
      stmt.setDouble(2, produto.getPreco());

      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Produto inserido com sucesso");
      } else {
        System.out.println("Falha ao inseirir produto");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao inserir produto");
      e.printStackTrace();
    }
  }

  public void listarProdutos(Connection connection) {
    String sql = "SELECT * FROM produtos";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = stmt.executeQuery();
      System.out.println("\nLista de produtos");
      while (resultSet.next()) {
        int id = resultSet.getInt("id_produto");
        String nome = resultSet.getString("nome_produto");
        Double preco = resultSet.getDouble("preco");

        System.out.printf("%d - nome: %s | preço: %f %n", id, nome, preco);
      }
    } catch (SQLException e) {
      System.out.println("Erro ao lista produtos");
      e.printStackTrace();
    }
  }

  public void editarProduto(Connection connection, int idProduto, double preco) {
    String sql = "UPDATE produtos SET preco = ? WHERE id_produto = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setDouble(1, preco);
      stmt.setInt(2, idProduto);

      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Preço do produto atualizado com sucesso");
      } else {
        System.out.println("Não foi possível atualizar o preço do produto");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao atualizar preço do produto");
      e.printStackTrace();
    }
  }

  public void deletarProduto(Connection connection, int idProduto) {
    String sql = "DELETE FROM produtos WHERE id_produto = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, idProduto);
      int linhas = stmt.executeUpdate();
      if (linhas > 0) {
        System.out.println("Produto delatado com sucesso");
      } else {
        System.out.println("Erro ao deletar produto");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao deletar produto");
      e.printStackTrace();
    }
  }
}
