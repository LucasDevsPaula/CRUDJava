import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente {
  private int id;
  private String nome;
  private String email;
  private String dataCadastro;
  private int idProduto;

  public Cliente(String nome, String email, String dataCadastro) {
    this.nome = nome;
    this.email = email;
    this.dataCadastro = dataCadastro;
  }

  public Cliente(String nome, String email, String dataCadastro, int idProduto) {
    this.nome = nome;
    this.email = email;
    this.dataCadastro = dataCadastro;
    this.idProduto = idProduto;
  }

  public static String getDataHoraAtual() {
    LocalDateTime dataHoraAtual = LocalDateTime.now();
    DateTimeFormatter anoMesDiaHoraMinuto = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return dataHoraAtual.format(anoMesDiaHoraMinuto);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(String dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public int getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
  }
}
