package br.com.postechfiap.jlappproducao.infra.mq;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;
import br.com.postechfiap.jlappproducao.usecase.producao.ProducaoUseCase;

public class PedidosSubscriberTest {

  @Mock
  private ProducaoUseCase producaoUseCase;

  @Mock
  private Logger log;

  @InjectMocks
  private PedidosSubscriber pedidosSubscriber;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldLogInfoWhenPedidoIsProcessedSuccessfully() {
    String message = "testMessage";
    when(producaoUseCase.processarPedido(message)).thenReturn(true);

    pedidosSubscriber.receive(message);

    verify(log, times(1)).info("Pedido para produção processado com sucesso: {}", message);
  }

  @Test
  public void shouldLogErrorWhenPedidoProcessingFails() {
    String message = "testMessage";
    when(producaoUseCase.processarPedido(message)).thenReturn(false);

    pedidosSubscriber.receive(message);

    verify(log, times(1)).error("Erro ao processar pedido para produção: {}", message);
  }
}
