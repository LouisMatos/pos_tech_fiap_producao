package br.com.postechfiap.jlappproducao.infra.db.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.postechfiap.jlappproducao.infra.db.schema.ProducaoSchema;

@Repository
public interface ProducaoRepository extends JpaRepository<ProducaoSchema, Long> {

  Optional<ProducaoSchema> findByNumeroPedido(String numeroPedido);

}
