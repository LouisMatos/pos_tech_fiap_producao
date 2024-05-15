package br.com.postechfiap.jlappproducao.infra.producao.gateway;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.domain.producao.gateway.IProducaoGateway;
import br.com.postechfiap.jlappproducao.domain.producao.mapper.ProducaoMapper;
import br.com.postechfiap.jlappproducao.infra.db.repository.ProducaoRepository;
import br.com.postechfiap.jlappproducao.infra.db.schema.ProducaoSchema;
import br.com.postechfiap.jlappproducao.shared.exception.NotFoundException;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;

@Component
public class ProducaoGateway implements IProducaoGateway {

  @Autowired
  private ProducaoRepository producaoRepository;

  @Autowired
  private Logger log;

  @Override
  public ProducaoDTO inserir(ProducaoDTO producaoDTO) {
    ProducaoSchema producaoSchema =
        producaoRepository.insert(ProducaoMapper.toProducaoSchema(producaoDTO));

    log.info("Cadastrando novo pedido para a produção na base de dados!");
    return ProducaoMapper.toProducaoDTO(producaoSchema);
  }

  @Override
  public ProducaoDTO buscaPedidoNumeroPedido(String numeroPedido) {
    Optional<ProducaoSchema> producaoSchema = producaoRepository.findByNumeroPedido(numeroPedido);

    ProducaoDTO producaoDTO = ProducaoMapper.toProducaoDTO(producaoSchema.orElseThrow(
        () -> new NotFoundException("Número de pedido: " + numeroPedido + " não encontrado!")));

    return producaoDTO;
  }

  @Override
  public ProducaoDTO atualizar(ProducaoDTO producaoDTO) {
    ProducaoSchema pedidoSchema =
        producaoRepository.save(ProducaoMapper.toProducaoSchema(producaoDTO));

    log.info("Atualizando pedido de producao na base de dados!");
    return ProducaoMapper.toProducaoDTO(pedidoSchema);
  }

}
