package br.com.postechfiap.jlappproducao.domain.producao.dto;

import java.time.LocalDateTime;
import br.com.postechfiap.jlappproducao.domain.enums.Estado;
import br.com.postechfiap.jlappproducao.domain.enums.StatusPagamento;
import lombok.Data;

@Data
public class ProducaoDTO {

  private String idMongoDB;

  private Long id;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private String jsonPedido;
}
