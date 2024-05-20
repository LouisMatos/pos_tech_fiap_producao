package br.com.postechfiap.jlappproducao.infra.db.schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import br.com.postechfiap.jlappproducao.domain.enums.Estado;
import br.com.postechfiap.jlappproducao.domain.enums.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "producao")
public class ProducaoSchema implements Serializable {

  private static final long serialVersionUID = 5289878346047956608L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int pedidoId;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  @Column(columnDefinition = "TEXT", length = 4000)
  private String jsonPedido;

}
