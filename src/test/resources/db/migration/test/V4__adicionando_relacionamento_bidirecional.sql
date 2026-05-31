ALTER TABLE tabela_tarifaria
ADD COLUMN id_faixas BIGINT
REFERENCES tabela_faixa_tarifaria(id) ON DELETE CASCADE;

UPDATE tabela_tarifaria
SET id_faixas = (SELECT id FROM tabela_faixa_tarifaria LIMIT 1);

ALTER TABLE tabela_tarifaria
ALTER COLUMN id_faixas SET NOT NULL;