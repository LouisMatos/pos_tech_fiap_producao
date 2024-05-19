package br.com.postechfiap.jlappproducao.infra.mq;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;

public class PedidoPublisherTest {

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private Queue statusPedidoQueue;

  @InjectMocks
  private PedidoPublisher pedidoPublisher;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldSendProducaoDTOAsJson() throws JsonProcessingException {
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(statusPedidoQueue.getName()).thenReturn("testQueue");

    pedidoPublisher.send(producaoDTO);

    verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString());
  }

  @Test
  public void shouldConvertEventToJSON() throws JsonProcessingException {
    ProducaoDTO producaoDTO = new ProducaoDTO();

    String json = pedidoPublisher.convertIntoJson(producaoDTO);

    assertNotNull(json);
    assertTrue(json.contains("\"id\":null")); // assuming id is a field in EventoPedidoDTO
  }



}
