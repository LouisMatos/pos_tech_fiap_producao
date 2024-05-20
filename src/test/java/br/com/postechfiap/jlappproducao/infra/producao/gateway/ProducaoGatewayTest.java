package br.com.postechfiap.jlappproducao.infra.producao.gateway;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.infra.db.repository.ProducaoRepository;
import br.com.postechfiap.jlappproducao.infra.db.schema.ProducaoSchema;
import br.com.postechfiap.jlappproducao.shared.exception.NotFoundException;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;

public class ProducaoGatewayTest {

  @Mock
  private ProducaoRepository producaoRepository;

  @Mock
  private Logger log;

  @InjectMocks
  private ProducaoGateway producaoGateway;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldInsertProducaoDTO() {
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoRepository.save(any(ProducaoSchema.class))).thenReturn(new ProducaoSchema());

    producaoGateway.inserir(producaoDTO);

    verify(producaoRepository, times(1)).save(any(ProducaoSchema.class));
    verify(log, times(1)).info(anyString());
  }

  @Test
  public void shouldThrowNotFoundExceptionWhenPedidoNotFound() {
    when(producaoRepository.findByNumeroPedido(anyString())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> producaoGateway.buscaPedidoNumeroPedido("123"));

    verify(producaoRepository, times(1)).findByNumeroPedido(anyString());
  }

  @Test
  public void shouldUpdateProducaoDTO() {
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoRepository.save(any())).thenReturn(new ProducaoSchema());

    producaoGateway.atualizar(producaoDTO);

    verify(producaoRepository, times(1)).save(any());
    verify(log, times(1)).info(anyString());
  }


  @Test
  public void shouldReturnProducaoDTOWhenPedidoIsFound() {
    String numeroPedido = "123";
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoRepository.findByNumeroPedido(anyString()))
        .thenReturn(Optional.of(new ProducaoSchema()));

    ProducaoDTO result = producaoGateway.buscaPedidoNumeroPedido(numeroPedido);

    assertNotNull(result);
    verify(log, times(0)).info(anyString());
  }

  @Test
  public void shouldThrowNotFoundExceptionWhenPedidoIsNotFound() {
    String numeroPedido = "123";
    when(producaoRepository.findByNumeroPedido(anyString())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class,
        () -> producaoGateway.buscaPedidoNumeroPedido(numeroPedido));

    verify(log, never()).info(anyString());
  }

}
