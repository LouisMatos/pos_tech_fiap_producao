package br.com.postechfiap.jlappproducao.domain.producao.gateway;

import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;

public interface IProducaoGateway {

  ProducaoDTO inserir(ProducaoDTO producaoDTO);

  public ProducaoDTO buscaPedidoNumeroPedido(String numeroPedido);

  public ProducaoDTO atualizar(ProducaoDTO producaoDTO);


}
