package br.com.postechfiap.jlappproducao.domain.producao.model;

import java.time.LocalDateTime;
import br.com.postechfiap.jlappproducao.domain.enums.Estado;
import br.com.postechfiap.jlappproducao.domain.enums.StatusPagamento;

public class Producao {

  private Long id;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private String jsonPedido;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumeroPedido() {
    return numeroPedido;
  }

  public void setNumeroPedido(String numeroPedido) {
    this.numeroPedido = numeroPedido;
  }

  public StatusPagamento getStatusPagamento() {
    return statusPagamento;
  }

  public void setStatusPagamento(StatusPagamento statusPagamento) {
    this.statusPagamento = statusPagamento;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public LocalDateTime getDataPedido() {
    return dataPedido;
  }

  public void setDataPedido(LocalDateTime dataPedido) {
    this.dataPedido = dataPedido;
  }

  public String getJsonPedido() {
    return jsonPedido;
  }

  public void setJsonPedido(String jsonPedido) {
    this.jsonPedido = jsonPedido;
  }

  @Override
  public String toString() {
    return "Producao{" + "id=" + id + ", numeroPedido='" + numeroPedido + '\''
        + ", statusPagamento=" + statusPagamento + ", estado=" + estado + ", dataPedido="
        + dataPedido + ", jsonPedido='" + jsonPedido + '\'' + '}';
  }


}
