-- ================================================
-- PASSO 1: Suspende o NOT NULL temporariamente
-- ================================================
ALTER TABLE tabela_tarifaria ALTER COLUMN id_faixas DROP NOT NULL;

-- ================================================
-- PASSO 2: Insere tabela_categoria
-- ================================================
INSERT INTO tabela_categoria (nome) VALUES
('COMERCIAL'),
('INDUSTRIAL'),
('PARTICULAR'),
('PUBLICO');

-- ================================================
-- PASSO 3: Insere tabela_tarifaria (id_faixas null por ora)
-- ================================================
INSERT INTO tabela_tarifaria (nome, data_vigencia, ativo) VALUES
('Tabela Comercial 2024',   '2024-01-01', true),
('Tabela Industrial 2024',  '2024-01-01', true),
('Tabela Residencial 2024', '2024-01-01', true),
('Tabela Pública 2024',     '2024-01-01', true),
('Tabela Comercial 2025',   '2025-01-01', true),
('Tabela Industrial 2025',  '2025-01-01', true),
('Tabela Residencial 2025', '2025-01-01', true),
('Tabela Pública 2025',     '2025-01-01', true),
('Tabela Especial Verão',   '2024-12-01', false),
('Tabela Especial Inverno', '2024-06-01', false);

-- ================================================
-- PASSO 4: Insere tabela_faixa_tarifaria
-- Regra: próxima faixa = fim_anterior + 1
-- ================================================

-- Tabela Comercial 2024 (id=1)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(1, 'COMERCIAL', 0,   100, 2.50, 1),
(1, 'COMERCIAL', 101, 300, 2.20, 2),
(1, 'COMERCIAL', 301, 500, 1.90, 3),
(1, 'COMERCIAL', 501, 999, 1.60, 4);

-- Tabela Industrial 2024 (id=2)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(2, 'INDUSTRIAL', 0,    500,  1.80, 1),
(2, 'INDUSTRIAL', 501,  1000, 1.50, 2),
(2, 'INDUSTRIAL', 1001, 3000, 1.20, 3),
(2, 'INDUSTRIAL', 3001, 9999, 0.90, 4);

-- Tabela Residencial 2024 (id=3)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(3, 'PARTICULAR', 0,   50,  3.10, 1),
(3, 'PARTICULAR', 51,  150, 2.80, 2),
(3, 'PARTICULAR', 151, 300, 2.50, 3),
(3, 'PARTICULAR', 301, 500, 2.20, 4);

-- Tabela Pública 2024 (id=4)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(4, 'PUBLICO', 0,    200,  1.20, 1),
(4, 'PUBLICO', 201,  500,  1.00, 2),
(4, 'PUBLICO', 501,  1000, 0.80, 3),
(4, 'PUBLICO', 1001, 9999, 0.60, 4);

-- Tabela Comercial 2025 (id=5)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(5, 'COMERCIAL', 0,   100, 2.80, 1),
(5, 'COMERCIAL', 101, 300, 2.50, 2),
(5, 'COMERCIAL', 301, 500, 2.10, 3),
(5, 'COMERCIAL', 501, 999, 1.80, 4);

-- Tabela Industrial 2025 (id=6)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(6, 'INDUSTRIAL', 0,    500,  2.00, 1),
(6, 'INDUSTRIAL', 501,  1000, 1.70, 2),
(6, 'INDUSTRIAL', 1001, 3000, 1.40, 3),
(6, 'INDUSTRIAL', 3001, 9999, 1.10, 4);

-- Tabela Residencial 2025 (id=7)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(7, 'PARTICULAR', 0,   50,  3.50, 1),
(7, 'PARTICULAR', 51,  150, 3.10, 2),
(7, 'PARTICULAR', 151, 300, 2.80, 3),
(7, 'PARTICULAR', 301, 500, 2.40, 4);

-- Tabela Pública 2025 (id=8)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(8, 'PUBLICO', 0,    200,  1.40, 1),
(8, 'PUBLICO', 201,  500,  1.20, 2),
(8, 'PUBLICO', 501,  1000, 1.00, 3),
(8, 'PUBLICO', 1001, 9999, 0.75, 4);

-- Tabela Especial Verão (id=9)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(9, 'COMERCIAL',  0,   200,  2.30, 1),
(9, 'INDUSTRIAL', 201, 600,  1.60, 2),
(9, 'PARTICULAR', 601, 900,  2.90, 3),
(9, 'PUBLICO',    901, 9999, 0.70, 4);

-- Tabela Especial Inverno (id=10)
INSERT INTO tabela_faixa_tarifaria (tabela_id, categoria_id, inicio, fim, valor_unitario, ordem) VALUES
(10, 'COMERCIAL',  0,   200,  2.10, 1),
(10, 'INDUSTRIAL', 201, 600,  1.40, 2),
(10, 'PARTICULAR', 601, 900,  2.70, 3),
(10, 'PUBLICO',    901, 9999, 0.65, 4);

-- ================================================
-- PASSO 5: Atualiza id_faixas com a primeira faixa
-- ================================================
UPDATE tabela_tarifaria t
SET id_faixas = (
    SELECT f.id
    FROM tabela_faixa_tarifaria f
    WHERE f.tabela_id = t.id
    ORDER BY f.ordem
    LIMIT 1
);

-- ================================================
-- PASSO 6: Restaura o NOT NULL
-- ================================================
ALTER TABLE tabela_tarifaria ALTER COLUMN id_faixas SET NOT NULL;