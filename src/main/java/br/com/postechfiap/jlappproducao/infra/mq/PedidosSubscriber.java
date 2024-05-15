package br.com.postechfiap.jlappproducao.infra.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;
import br.com.postechfiap.jlappproducao.usecase.producao.ProducaoUseCase;

@Component
public class PedidosSubscriber {

  private final ProducaoUseCase producaoUseCase;

  private final Logger log;

  private boolean isProcessado = false;

  public PedidosSubscriber(ProducaoUseCase producaoUseCase, Logger log) {
    this.producaoUseCase = producaoUseCase;
    this.log = log;
  }


  @RabbitListener(queues = "${mq.queues.cozinha}")
  public void receive(String message) {


    log.info("Pedido para produção recebido da fila: {}", message);

    isProcessado = producaoUseCase.processarPedido(message);

    if (isProcessado) {
      log.info("Pedido para produção processado com sucesso: {}", message);
    } else {
      log.error("Erro ao processar pedido para produção: {}", message);
    }


  }

}
