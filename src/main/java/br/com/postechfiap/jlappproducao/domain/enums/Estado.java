package br.com.postechfiap.jlappproducao.domain.enums;

import br.com.postechfiap.jlappproducao.shared.exception.UnprocessableEntityException;

public enum Estado {

  RECEBIDO(1), //
  EM_PREPARACAO(2), //
  PRONTO(3), //
  FINALIZADO(4); //

  private int estado;

  Estado(int estado) {
    this.estado = estado;
  }

  public boolean foiRecebido() {
    return RECEBIDO.getValorEstado() == this.estado;
  }

  public boolean estaEmPreparacao() {
    return EM_PREPARACAO.getValorEstado() == this.estado;
  }

  public boolean estaPronto() {
    return PRONTO.getValorEstado() == this.estado;
  }

  public boolean estaFinalizado() {
    return FINALIZADO.getValorEstado() == this.estado;
  }

  public int getValorEstado() {
    return this.estado;
  }

  public void validarTransicaoPara(Estado novo) {
    if (novo == null || novo.getValorEstado() != this.estado + 1) {
      throw new UnprocessableEntityException(
          "Transição de estado inválida: " + this + " -> " + novo);
    }
  }

}
