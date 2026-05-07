CREATE TABLE tabela_faixa_tarifaria (
  id SERIAL PRIMARY KEY,
  tabela_id INT NOT NULL REFERENCES tabela_tarifaria(id) ON DELETE CASCADE,
  categoria_id INT NOT NULL REFERENCES tabela_categoria(id),
  inicio INT NOT NULL,
  fim INT NOT NULL,
  valor_unitario NUMERIC(12,2) NOT NULL,
  ordem INT NOT NULL,
  CONSTRAINT inicio_menor_fim CHECK (inicio < fim)
);