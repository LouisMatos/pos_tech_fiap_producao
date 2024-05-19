package br.com.postechfiap.jlappproducao.usecase.producao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.domain.producao.gateway.IProducaoGateway;
import br.com.postechfiap.jlappproducao.infra.mq.PedidoPublisher;
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
  public void shouldUpdatePedidoSuccessfully() {
    String numero_pedido = "123";
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoGateway.buscaPedidoNumeroPedido(anyString())).thenReturn(producaoDTO);
    when(producaoGateway.atualizar(any())).thenReturn(producaoDTO);

    producaoUseCase.atualizar(producaoDTO, numero_pedido);

    verify(log, times(2)).info(anyString(), any());
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
