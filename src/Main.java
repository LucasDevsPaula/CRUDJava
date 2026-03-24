import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
  private static final String URL =
      "jdbc:mysql://localhost:3306/ebac2?SSL=false&serverTimeZone=UTC";
  private static final String USER = "root";
  private static final String PASSWORD = "*******";

  public static void main(String[] args) {

    ClienteService clienteService = new ClienteService();
    ProdutoService produtoService = new ProdutoService();
    Produto produto = new Produto("Leite", 5.20);
    Cliente cliente =
        new Cliente("Lucas de Paula", "lucas.paula@email.com", Cliente.getDataHoraAtual());
    Cliente cliente1 =
        new Cliente("Ana Clara", "ana.clara@email.com", Cliente.getDataHoraAtual(), 2);
    Cliente cliente2 = new Cliente("Fabricio", "fabricio@email.com", Cliente.getDataHoraAtual(), 2);

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      // clienteService.inserirCliente(connection, cliente);
      // clienteService.inserirClienteProduto(connection, cliente2);
      // clienteService.adicionarProdutoAoCliente(connection, 9, 1);
      // clienteService.deletarCliente(connection, 3);
      // produtoService.inserirProduto(connection, produto);
      clienteService.buscarClientes(connection);
      clienteService.buscarClientesProdutos(connection);
      // produtoService.deletarProduto(connection, 3);
      produtoService.editarProduto(connection, 4, 3.50);
      produtoService.listarProdutos(connection);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
