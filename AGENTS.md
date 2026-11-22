# Serviço de Produção (pos_tech_fiap_producao)

Regras específicas deste serviço. As regras gerais do workspace estão em `../AGENTS.md`
(arquitetura em `../docs/ai/arquitetura.md`, contratos em `../docs/ai/contratos.md`).

## Comandos
- Um teste: `./mvnw -q test -Dtest=NomeDaClasseTest`
- Antes de concluir qualquer tarefa: `./mvnw -q verify` (inclui Checkstyle e JaCoCo, igual ao CI)
- Subir dependências locais: `docker compose up -d`

## Mensageria
- Publica em: `status-pedidos-mq` — `infra/mq/PedidoPublisher`
- Consome de: `cozinha-mq` — `infra/mq/PedidosSubscriber`
- Nomes das filas em `src/main/resources/application-*.yml` (`mq.queues.*`). Não fixe nomes no código.

## Regras
- Cobertura mínima do JaCoCo: 75%. Código novo vem com teste no pacote espelhado em `src/test`.
- Regra de negócio em `domain`/`usecase`; `infra` só adapta (controller, gateway, db, mq).
- Transições de `Estado` seguem a ordem RECEBIDO → EM_PREPARACAO → PRONTO → FINALIZADO.
