package br.com.postechfiap.jlappproducao.infra.db.schema;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import br.com.postechfiap.jlappproducao.domain.enums.Estado;
import br.com.postechfiap.jlappproducao.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "producao")
public class ProducaoSchema {

  @Id
  private String id;

  private Long pedidoId;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private String jsonPedido;

}
