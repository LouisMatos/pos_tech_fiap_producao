package br.com.postechfiap.jlappproducao.usecase.producao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.postechfiap.jlappproducao.domain.enums.Estado;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.domain.producao.gateway.IProducaoGateway;
import br.com.postechfiap.jlappproducao.infra.mq.PedidoPublisher;
import br.com.postechfiap.jlappproducao.shared.exception.UnprocessableEntityException;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;

public class ProducaoUseCaseTest {

  @Mock
  private IProducaoGateway producaoGateway;

  @Mock
  private PedidoPublisher pedidoPublisher;

  @Mock
  private Logger log;

  @InjectMocks
  private ProducaoUseCase producaoUseCase;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  public void shouldFindPedidoByNumeroPedido() {
    String numero_pedido = "123";
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoGateway.buscaPedidoNumeroPedido(anyString())).thenReturn(producaoDTO);

    producaoUseCase.buscaPedidoNumeroPedido(numero_pedido);

    verify(log, times(1)).info(anyString(), any());
  }

  @Test
  public void shouldUpdatePedidoSuccessfully() throws JsonProcessingException {
    String numero_pedido = "123";
    ProducaoDTO pedidoAtual = new ProducaoDTO();
    pedidoAtual.setEstado(Estado.RECEBIDO);
    ProducaoDTO producaoDTO = new ProducaoDTO();
    producaoDTO.setEstado(Estado.EM_PREPARACAO);
    when(producaoGateway.buscaPedidoNumeroPedido(anyString())).thenReturn(pedidoAtual);
    when(producaoGateway.atualizar(any())).thenReturn(pedidoAtual);

    producaoUseCase.atualizar(producaoDTO, numero_pedido);

    verify(pedidoPublisher, times(1)).send(any());
    verify(log, times(2)).info(anyString(), any());
  }

  @Test
  public void shouldRejectInvalidTransitionWithoutPublishing() throws JsonProcessingException {
    String numero_pedido = "123";
    ProducaoDTO pedidoAtual = new ProducaoDTO();
    pedidoAtual.setEstado(Estado.RECEBIDO);
    ProducaoDTO producaoDTO = new ProducaoDTO();
    producaoDTO.setEstado(Estado.PRONTO);
    when(producaoGateway.buscaPedidoNumeroPedido(anyString())).thenReturn(pedidoAtual);

    assertThrows(UnprocessableEntityException.class,
        () -> producaoUseCase.atualizar(producaoDTO, numero_pedido));

    verify(producaoGateway, never()).atualizar(any());
    verify(pedidoPublisher, never()).send(any());
  }

  @Test
  public void shouldProcessPedidoSuccessfully() {
    String message = "{\"estado\":\"EM_PRODUCAO\"}";
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoGateway.inserir(any())).thenReturn(producaoDTO);

    boolean result = producaoUseCase.processarPedido(message);

    assertTrue(result);
    verify(log, times(0)).info(anyString(), any());
  }

  @Test
  public void shouldNotProcessPedidoWhenJsonProcessingExceptionOccurs() {
    String message = "invalidJson";

    boolean result = producaoUseCase.processarPedido(message);

    assertTrue(result);
    verify(log, never()).info(anyString(), any());
  }
}
