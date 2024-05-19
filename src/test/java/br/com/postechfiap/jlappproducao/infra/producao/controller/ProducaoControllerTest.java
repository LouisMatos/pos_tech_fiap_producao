package br.com.postechfiap.jlappproducao.infra.producao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import br.com.postechfiap.jlappproducao.domain.producao.dto.ProducaoDTO;
import br.com.postechfiap.jlappproducao.shared.logger.log.Logger;
import br.com.postechfiap.jlappproducao.usecase.producao.ProducaoUseCase;

public class ProducaoControllerTest {

  @Mock
  private ProducaoUseCase producaoUseCase;

  @Mock
  private Logger log;

  @InjectMocks
  private ProducaoController producaoController;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldReturnOkWhenStatusPedidoProducaoIsUpdated() {
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoUseCase.atualizar(any(), anyString())).thenReturn(producaoDTO);

    ResponseEntity<ProducaoDTO> response =
        producaoController.atualizarStatusPedidoProducao(producaoDTO, "123");

    assertEquals(200, response.getStatusCode().value());
    verify(log, times(1)).info(anyString(), anyString());
  }

  @Test
  public void shouldReturnProducaoDTOWhenStatusPedidoProducaoIsUpdated() {
    ProducaoDTO producaoDTO = new ProducaoDTO();
    when(producaoUseCase.atualizar(any(), anyString())).thenReturn(producaoDTO);

    ResponseEntity<ProducaoDTO> response =
        producaoController.atualizarStatusPedidoProducao(producaoDTO, "123");

    assertNotNull(response.getBody());
    assertEquals(producaoDTO, response.getBody());
  }
}
