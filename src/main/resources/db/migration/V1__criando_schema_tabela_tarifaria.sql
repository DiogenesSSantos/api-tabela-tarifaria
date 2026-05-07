CREATE TABLE tabela_tarifaria (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  data_vigencia DATE NOT NULL,
  ativo BOOLEAN DEFAULT TRUE
);