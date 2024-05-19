package br.com.postechfiap.jlappproducao.infra.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Queue statusPedidoQueue;


  public void send(ProducaoDTO producaoDTO) throws JsonProcessingException {
    String json = convertIntoJson(producaoDTO);
    rabbitTemplate.convertAndSend(statusPedidoQueue.getName(), json);
  }

  public String convertIntoJson(ProducaoDTO producaoDTO) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.writeValueAsString(producaoDTO);
  }

}
