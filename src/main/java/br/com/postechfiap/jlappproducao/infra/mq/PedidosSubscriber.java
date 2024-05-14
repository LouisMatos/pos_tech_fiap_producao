package br.com.postechfiap.jlappproducao.infra.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;

@Component
public class PedidosSubscriber {

  @Autowired
  private Logger log;


  @RabbitListener(queues = "${mq.queues.cozinha}")
  public void receive(String message) {

    log.info("Recebendo evento para a cozinha: {}", message);



  }

}
