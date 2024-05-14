package br.com.postechfiap.jlappproducao.infra.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.postechfiap.jlappproducao.infra.db.schema.ProducaoSchema;

public interface ProducaoRepository extends MongoRepository<ProducaoSchema, String> {

}
