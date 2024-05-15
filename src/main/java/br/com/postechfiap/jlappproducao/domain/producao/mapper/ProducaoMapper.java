package br.com.postechfiap.jlappproducao.domain.producao.mapper;

import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.infra.db.schema.ProducaoSchema;

public class ProducaoMapper {

  public static ProducaoSchema toProducaoSchema(ProducaoDTO producaoDTO) {

    ProducaoSchema producaoSchema = new ProducaoSchema();

    producaoSchema.setId(producaoDTO.getIdMongoDB());
    producaoSchema.setPedidoId(producaoDTO.getId());
    producaoSchema.setNumeroPedido(producaoDTO.getNumeroPedido());
    producaoSchema.setStatusPagamento(producaoDTO.getStatusPagamento());
    producaoSchema.setEstado(producaoDTO.getEstado());
    producaoSchema.setDataPedido(producaoDTO.getDataPedido());
    producaoSchema.setJsonPedido(producaoDTO.getJsonPedido());

    return producaoSchema;
  }

  public static ProducaoDTO toProducaoDTO(ProducaoSchema producaoSchema) {
    ProducaoDTO producaoDTO = new ProducaoDTO();

    producaoDTO.setIdMongoDB(producaoSchema.getId());
    producaoDTO.setId(producaoSchema.getPedidoId());
    producaoDTO.setNumeroPedido(producaoSchema.getNumeroPedido());
    producaoDTO.setStatusPagamento(producaoSchema.getStatusPagamento());
    producaoDTO.setEstado(producaoSchema.getEstado());
    producaoDTO.setDataPedido(producaoSchema.getDataPedido());
    producaoDTO.setJsonPedido(producaoSchema.getJsonPedido());

    return producaoDTO;
  }

}
