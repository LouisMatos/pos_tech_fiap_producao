package br.com.postechfiap.jlappproducao.infra.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

  @Value("${mq.queues.pedido.cozinha}")
  private String pedidoCozinhaQueue;

  @Value("${mq.queues.cozinha}")
  private String cozinhaQueue;

  @Value("${mq.queues.statuspedidos}")
  private String statusPedidoQueue;

  @Value("${mq.exchanges.direct}")
  private String directExchange;

  @Value("${mq.routing.key.pedido.cozinha}")
  private String pedidoCozinhaRoutingKey;

  @Value("${mq.routing.key.statuspedidos}")
  private String statusPedidoRoutingKey;

  @Value("${mq.routing.key.cozinha}")
  private String cozinhaRoutingKey;

  @Bean
  public Queue pedidoCozinhaQueue() {
    return new Queue(pedidoCozinhaQueue, true);
  }

  @Bean
  public Queue cozinhaQueue() {
    return new Queue(cozinhaQueue, true);
  }

  @Bean
  public Queue statusPedidoQueue() {
    return new Queue(statusPedidoQueue, true);
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(directExchange);
  }

  @Bean
  Binding pedidoCozinhaBinding(Queue pedidoCozinhaQueue, DirectExchange exchange) {
    return BindingBuilder.bind(pedidoCozinhaQueue).to(exchange).with(pedidoCozinhaRoutingKey);
  }

  @Bean
  Binding cozinhaBinding(Queue cozinhaQueue, DirectExchange exchange) {
    return BindingBuilder.bind(cozinhaQueue).to(exchange).with(cozinhaRoutingKey);
  }

  @Bean
  Binding statusPedidoBinding(Queue statusPedidoQueue, DirectExchange exchange) {
    return BindingBuilder.bind(statusPedidoQueue).to(exchange).with(statusPedidoRoutingKey);
  }

}
