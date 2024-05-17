package br.com.postechfiap.jlappproducao.usecase.producao;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.domain.producao.gateway.IProducaoGateway;
import br.com.postechfiap.jlappproducao.infra.mq.PedidoPublisher;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;

@Service
public class ProducaoUseCase {

  private final IProducaoGateway producaoGateway;

  private final PedidoPublisher pedidoPublisher;

  private final Logger log;

  public ProducaoUseCase(IProducaoGateway producaoGateway, Logger log,
      PedidoPublisher pedidoPublisher) {
    this.producaoGateway = producaoGateway;
    this.pedidoPublisher = pedidoPublisher;
    this.log = log;
  }

  public boolean processarPedido(String message) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    try {
      ProducaoDTO producaoDTO = mapper.readValue(message, ProducaoDTO.class);

      ProducaoDTO dto = producaoGateway.inserir(producaoDTO);

      log.info("Pedido para produção recebido e processado com sucesso: {}", dto);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return true;

  }

  public ProducaoDTO buscaPedidoNumeroPedido(String numero_pedido) {

    ProducaoDTO dto = producaoGateway.buscaPedidoNumeroPedido(numero_pedido);

    log.info("Pedido encontrado! {}", dto);

    return dto;
  }


  public ProducaoDTO atualizar(ProducaoDTO producaoDTO, String numero_pedido) {
    ProducaoDTO buscaPedidoNumeroPedido = this.buscaPedidoNumeroPedido(numero_pedido);

    buscaPedidoNumeroPedido.setEstado(producaoDTO.getEstado());

    ProducaoDTO dto = producaoGateway.atualizar(buscaPedidoNumeroPedido);

    try {
      pedidoPublisher.send(dto);
      log.info("Pedido enviado para fila status-pedidos-mq: {}", dto.toString());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return dto;
  }

}
