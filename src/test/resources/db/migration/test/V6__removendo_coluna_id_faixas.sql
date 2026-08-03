-- A coluna id_faixas (criada na V4) não é mapeada por nenhuma entidade e
-- seu NOT NULL impedia qualquer INSERT em tabela_tarifaria via JPA.
-- O relacionamento bidirecional real é feito pela FK tabela_id em tabela_faixa_tarifaria.
ALTER TABLE tabela_tarifaria DROP COLUMN id_faixas;
