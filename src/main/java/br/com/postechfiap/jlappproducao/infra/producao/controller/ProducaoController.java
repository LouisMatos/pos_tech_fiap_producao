package br.com.postechfiap.jlappproducao.infra.producao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;
import br.com.postechfiap.jlappproducao.usecase.producao.ProducaoUseCase;

@RestController
@RequestMapping(path = "/api/v1/producao")
public class ProducaoController {

  private final ProducaoUseCase producaoUseCase;

  private final Logger log;

  public ProducaoController(ProducaoUseCase producaoUseCase, Logger log) {
    this.producaoUseCase = producaoUseCase;
    this.log = log;
  }

  @PatchMapping("/{numero_pedido}")
  public ResponseEntity<ProducaoDTO> atualizarStatusPedidoProducao(
      @RequestBody ProducaoDTO producaoDTO, @PathVariable final String numero_pedido) {
    log.info("Iniciando a atualização do status do pedido produção: {}", numero_pedido);
    ProducaoDTO dto = producaoUseCase.atualizar(producaoDTO, numero_pedido);
    return ResponseEntity.ok(dto);
  }


}
