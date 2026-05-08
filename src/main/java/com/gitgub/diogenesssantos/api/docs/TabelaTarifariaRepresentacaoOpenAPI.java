package com.gitgub.diogenesssantos.api.docs;

public interface TabelaTarifariaRepresentacaoOpenAPI {


    String CORPO_EXEMPLO_GET_SUCESS = """
                      
            {
                "ativo": true,
                "dataVigencia": "2027-11-02",
                "id": 3,
                "nome": "Tabela 2028"
            }
              
          
            """;


    String CORPO_EXEMPLO_POST = """
            
            {
                "nome": "Tabela 2028",
                "dataVigencia": "2027-11-02",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 7, "valorUnitario": 9.50, "ordem": 1},
                      {"inicio": 8, "fim": 19, "valorUnitario": 14.75, "ordem": 2},
                      {"inicio": 20, "fim": 34, "valorUnitario": 21.00, "ordem": 3},
                      {"inicio": 35, "fim": 59, "valorUnitario": 28.30, "ordem": 4},
                      {"inicio": 60, "fim": 99, "valorUnitario": 35.60, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 42.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 4, "valorUnitario": 12.00, "ordem": 1},
                      {"inicio": 5, "fim": 14, "valorUnitario": 18.50, "ordem": 2},
                      {"inicio": 15, "fim": 29, "valorUnitario": 25.75, "ordem": 3},
                      {"inicio": 30, "fim": 49, "valorUnitario": 33.00, "ordem": 4},
                      {"inicio": 50, "fim": 74, "valorUnitario": 40.20, "ordem": 5},
                      {"inicio": 75, "fim": 99999, "valorUnitario": 55.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 9, "valorUnitario": 17.00, "ordem": 1},
                      {"inicio": 10, "fim": 24, "valorUnitario": 23.40, "ordem": 2},
                      {"inicio": 25, "fim": 44, "valorUnitario": 31.80, "ordem": 3},
                      {"inicio": 45, "fim": 69, "valorUnitario": 38.50, "ordem": 4},
                      {"inicio": 70, "fim": 99, "valorUnitario": 47.90, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 60.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 12, "valorUnitario": 8.75, "ordem": 1},
                      {"inicio": 13, "fim": 27, "valorUnitario": 15.30, "ordem": 2},
                      {"inicio": 28, "fim": 50, "valorUnitario": 22.60, "ordem": 3},
                      {"inicio": 51, "fim": 80, "valorUnitario": 30.10, "ordem": 4},
                      {"inicio": 81, "fim": 120, "valorUnitario": 39.80, "ordem": 5},
                      {"inicio": 121, "fim": 99999, "valorUnitario": 51.00, "ordem": 6}
                    ]
                  }
                ]
            }
            
            """;
    // Exemplo de sucesso para busca em lote
    String CORPO_EXEMPLO_GET_LOTE_SUCESS = """
            [
              {
                "nome": "Tabela 2028",
                "dataVigencia": "2027-11-02",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 7, "valorUnitario": 9.50, "ordem": 1},
                      {"inicio": 8, "fim": 19, "valorUnitario": 14.75, "ordem": 2},
                      {"inicio": 20, "fim": 34, "valorUnitario": 21.00, "ordem": 3},
                      {"inicio": 35, "fim": 59, "valorUnitario": 28.30, "ordem": 4},
                      {"inicio": 60, "fim": 99, "valorUnitario": 35.60, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 42.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 4, "valorUnitario": 12.00, "ordem": 1},
                      {"inicio": 5, "fim": 14, "valorUnitario": 18.50, "ordem": 2},
                      {"inicio": 15, "fim": 29, "valorUnitario": 25.75, "ordem": 3},
                      {"inicio": 30, "fim": 49, "valorUnitario": 33.00, "ordem": 4},
                      {"inicio": 50, "fim": 74, "valorUnitario": 40.20, "ordem": 5},
                      {"inicio": 75, "fim": 99999, "valorUnitario": 55.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 9, "valorUnitario": 17.00, "ordem": 1},
                      {"inicio": 10, "fim": 24, "valorUnitario": 23.40, "ordem": 2},
                      {"inicio": 25, "fim": 44, "valorUnitario": 31.80, "ordem": 3},
                      {"inicio": 45, "fim": 69, "valorUnitario": 38.50, "ordem": 4},
                      {"inicio": 70, "fim": 99, "valorUnitario": 47.90, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 60.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 12, "valorUnitario": 8.75, "ordem": 1},
                      {"inicio": 13, "fim": 27, "valorUnitario": 15.30, "ordem": 2},
                      {"inicio": 28, "fim": 50, "valorUnitario": 22.60, "ordem": 3},
                      {"inicio": 51, "fim": 80, "valorUnitario": 30.10, "ordem": 4},
                      {"inicio": 81, "fim": 120, "valorUnitario": 39.80, "ordem": 5},
                      {"inicio": 121, "fim": 99999, "valorUnitario": 51.00, "ordem": 6}
                    ]
                  }
                ]
              },
              {
                "nome": "Tabela 2029",
                "dataVigencia": "2028-06-15",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 5, "valorUnitario": 10.00, "ordem": 1},
                      {"inicio": 6, "fim": 15, "valorUnitario": 16.50, "ordem": 2},
                      {"inicio": 16, "fim": 30, "valorUnitario": 23.00, "ordem": 3},
                      {"inicio": 31, "fim": 55, "valorUnitario": 31.75, "ordem": 4},
                      {"inicio": 56, "fim": 90, "valorUnitario": 40.00, "ordem": 5},
                      {"inicio": 91, "fim": 99999, "valorUnitario": 50.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 6, "valorUnitario": 13.50, "ordem": 1},
                      {"inicio": 7, "fim": 18, "valorUnitario": 20.00, "ordem": 2},
                      {"inicio": 19, "fim": 35, "valorUnitario": 27.80, "ordem": 3},
                      {"inicio": 36, "fim": 55, "valorUnitario": 35.50, "ordem": 4},
                      {"inicio": 56, "fim": 80, "valorUnitario": 44.00, "ordem": 5},
                      {"inicio": 81, "fim": 99999, "valorUnitario": 58.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 8, "valorUnitario": 18.50, "ordem": 1},
                      {"inicio": 9, "fim": 22, "valorUnitario": 25.00, "ordem": 2},
                      {"inicio": 23, "fim": 40, "valorUnitario": 33.60, "ordem": 3},
                      {"inicio": 41, "fim": 65, "valorUnitario": 42.00, "ordem": 4},
                      {"inicio": 66, "fim": 95, "valorUnitario": 51.30, "ordem": 5},
                      {"inicio": 96, "fim": 99999, "valorUnitario": 63.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 10, "valorUnitario": 9.00, "ordem": 1},
                      {"inicio": 11, "fim": 25, "valorUnitario": 16.80, "ordem": 2},
                      {"inicio": 26, "fim": 45, "valorUnitario": 24.50, "ordem": 3},
                      {"inicio": 46, "fim": 75, "valorUnitario": 32.00, "ordem": 4},
                      {"inicio": 76, "fim": 110, "valorUnitario": 41.50, "ordem": 5},
                      {"inicio": 111, "fim": 99999, "valorUnitario": 54.00, "ordem": 6}
                    ]
                  }
                ]
              },
              {
                "nome": "Tabela 2030",
                "dataVigencia": "2029-03-01",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 9, "valorUnitario": 11.25, "ordem": 1},
                      {"inicio": 10, "fim": 22, "valorUnitario": 17.00, "ordem": 2},
                      {"inicio": 23, "fim": 40, "valorUnitario": 25.50, "ordem": 3},
                      {"inicio": 41, "fim": 70, "valorUnitario": 34.00, "ordem": 4},
                      {"inicio": 71, "fim": 110, "valorUnitario": 44.75, "ordem": 5},
                      {"inicio": 111, "fim": 99999, "valorUnitario": 57.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 3, "valorUnitario": 14.00, "ordem": 1},
                      {"inicio": 4, "fim": 12, "valorUnitario": 21.50, "ordem": 2},
                      {"inicio": 13, "fim": 28, "valorUnitario": 29.00, "ordem": 3},
                      {"inicio": 29, "fim": 50, "valorUnitario": 37.80, "ordem": 4},
                      {"inicio": 51, "fim": 85, "valorUnitario": 47.00, "ordem": 5},
                      {"inicio": 86, "fim": 99999, "valorUnitario": 61.50, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 11, "valorUnitario": 19.75, "ordem": 1},
                      {"inicio": 12, "fim": 26, "valorUnitario": 27.00, "ordem": 2},
                      {"inicio": 27, "fim": 48, "valorUnitario": 36.20, "ordem": 3},
                      {"inicio": 49, "fim": 72, "valorUnitario": 45.00, "ordem": 4},
                      {"inicio": 73, "fim": 100, "valorUnitario": 54.60, "ordem": 5},
                      {"inicio": 101, "fim": 99999, "valorUnitario": 67.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 8, "valorUnitario": 10.50, "ordem": 1},
                      {"inicio": 9, "fim": 20, "valorUnitario": 18.00, "ordem": 2},
                      {"inicio": 21, "fim": 38, "valorUnitario": 26.40, "ordem": 3},
                      {"inicio": 39, "fim": 65, "valorUnitario": 35.00, "ordem": 4},
                      {"inicio": 66, "fim": 100, "valorUnitario": 44.20, "ordem": 5},
                      {"inicio": 101, "fim": 99999, "valorUnitario": 57.50, "ordem": 6}
                    ]
                  }
                ]
              }
            ]
            """;

    String CORPO_EXEMPLO_POST_LOTE = """
            [
              {
                "nome": "Tabela 2028",
                "dataVigencia": "2027-11-02",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 7, "valorUnitario": 9.50, "ordem": 1},
                      {"inicio": 8, "fim": 19, "valorUnitario": 14.75, "ordem": 2},
                      {"inicio": 20, "fim": 34, "valorUnitario": 21.00, "ordem": 3},
                      {"inicio": 35, "fim": 59, "valorUnitario": 28.30, "ordem": 4},
                      {"inicio": 60, "fim": 99, "valorUnitario": 35.60, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 42.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 4, "valorUnitario": 12.00, "ordem": 1},
                      {"inicio": 5, "fim": 14, "valorUnitario": 18.50, "ordem": 2},
                      {"inicio": 15, "fim": 29, "valorUnitario": 25.75, "ordem": 3},
                      {"inicio": 30, "fim": 49, "valorUnitario": 33.00, "ordem": 4},
                      {"inicio": 50, "fim": 74, "valorUnitario": 40.20, "ordem": 5},
                      {"inicio": 75, "fim": 99999, "valorUnitario": 55.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 9, "valorUnitario": 17.00, "ordem": 1},
                      {"inicio": 10, "fim": 24, "valorUnitario": 23.40, "ordem": 2},
                      {"inicio": 25, "fim": 44, "valorUnitario": 31.80, "ordem": 3},
                      {"inicio": 45, "fim": 69, "valorUnitario": 38.50, "ordem": 4},
                      {"inicio": 70, "fim": 99, "valorUnitario": 47.90, "ordem": 5},
                      {"inicio": 100, "fim": 99999, "valorUnitario": 60.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 12, "valorUnitario": 8.75, "ordem": 1},
                      {"inicio": 13, "fim": 27, "valorUnitario": 15.30, "ordem": 2},
                      {"inicio": 28, "fim": 50, "valorUnitario": 22.60, "ordem": 3},
                      {"inicio": 51, "fim": 80, "valorUnitario": 30.10, "ordem": 4},
                      {"inicio": 81, "fim": 120, "valorUnitario": 39.80, "ordem": 5},
                      {"inicio": 121, "fim": 99999, "valorUnitario": 51.00, "ordem": 6}
                    ]
                  }
                ]
              },
              {
                "nome": "Tabela 2029",
                "dataVigencia": "2028-06-15",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 5, "valorUnitario": 10.00, "ordem": 1},
                      {"inicio": 6, "fim": 15, "valorUnitario": 16.50, "ordem": 2},
                      {"inicio": 16, "fim": 30, "valorUnitario": 23.00, "ordem": 3},
                      {"inicio": 31, "fim": 55, "valorUnitario": 31.75, "ordem": 4},
                      {"inicio": 56, "fim": 90, "valorUnitario": 40.00, "ordem": 5},
                      {"inicio": 91, "fim": 99999, "valorUnitario": 50.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 6, "valorUnitario": 13.50, "ordem": 1},
                      {"inicio": 7, "fim": 18, "valorUnitario": 20.00, "ordem": 2},
                      {"inicio": 19, "fim": 35, "valorUnitario": 27.80, "ordem": 3},
                      {"inicio": 36, "fim": 55, "valorUnitario": 35.50, "ordem": 4},
                      {"inicio": 56, "fim": 80, "valorUnitario": 44.00, "ordem": 5},
                      {"inicio": 81, "fim": 99999, "valorUnitario": 58.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 8, "valorUnitario": 18.50, "ordem": 1},
                      {"inicio": 9, "fim": 22, "valorUnitario": 25.00, "ordem": 2},
                      {"inicio": 23, "fim": 40, "valorUnitario": 33.60, "ordem": 3},
                      {"inicio": 41, "fim": 65, "valorUnitario": 42.00, "ordem": 4},
                      {"inicio": 66, "fim": 95, "valorUnitario": 51.30, "ordem": 5},
                      {"inicio": 96, "fim": 99999, "valorUnitario": 63.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 10, "valorUnitario": 9.00, "ordem": 1},
                      {"inicio": 11, "fim": 25, "valorUnitario": 16.80, "ordem": 2},
                      {"inicio": 26, "fim": 45, "valorUnitario": 24.50, "ordem": 3},
                      {"inicio": 46, "fim": 75, "valorUnitario": 32.00, "ordem": 4},
                      {"inicio": 76, "fim": 110, "valorUnitario": 41.50, "ordem": 5},
                      {"inicio": 111, "fim": 99999, "valorUnitario": 54.00, "ordem": 6}
                    ]
                  }
                ]
              },
              {
                "nome": "Tabela 2030",
                "dataVigencia": "2029-03-01",
                "categorias": [
                  {
                    "nome": "INDUSTRIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 9, "valorUnitario": 11.25, "ordem": 1},
                      {"inicio": 10, "fim": 22, "valorUnitario": 17.00, "ordem": 2},
                      {"inicio": 23, "fim": 40, "valorUnitario": 25.50, "ordem": 3},
                      {"inicio": 41, "fim": 70, "valorUnitario": 34.00, "ordem": 4},
                      {"inicio": 71, "fim": 110, "valorUnitario": 44.75, "ordem": 5},
                      {"inicio": 111, "fim": 99999, "valorUnitario": 57.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PARTICULAR",
                    "faixas": [
                      {"inicio": 0, "fim": 3, "valorUnitario": 14.00, "ordem": 1},
                      {"inicio": 4, "fim": 12, "valorUnitario": 21.50, "ordem": 2},
                      {"inicio": 13, "fim": 28, "valorUnitario": 29.00, "ordem": 3},
                      {"inicio": 29, "fim": 50, "valorUnitario": 37.80, "ordem": 4},
                      {"inicio": 51, "fim": 85, "valorUnitario": 47.00, "ordem": 5},
                      {"inicio": 86, "fim": 99999, "valorUnitario": 61.50, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "COMERCIAL",
                    "faixas": [
                      {"inicio": 0, "fim": 11, "valorUnitario": 19.75, "ordem": 1},
                      {"inicio": 12, "fim": 26, "valorUnitario": 27.00, "ordem": 2},
                      {"inicio": 27, "fim": 48, "valorUnitario": 36.20, "ordem": 3},
                      {"inicio": 49, "fim": 72, "valorUnitario": 45.00, "ordem": 4},
                      {"inicio": 73, "fim": 100, "valorUnitario": 54.60, "ordem": 5},
                      {"inicio": 101, "fim": 99999, "valorUnitario": 67.00, "ordem": 6}
                    ]
                  },
                  {
                    "nome": "PUBLICO",
                    "faixas": [
                      {"inicio": 0, "fim": 8, "valorUnitario": 10.50, "ordem": 1},
                      {"inicio": 9, "fim": 20, "valorUnitario": 18.00, "ordem": 2},
                      {"inicio": 21, "fim": 38, "valorUnitario": 26.40, "ordem": 3},
                      {"inicio": 39, "fim": 65, "valorUnitario": 35.00, "ordem": 4},
                      {"inicio": 66, "fim": 100, "valorUnitario": 44.20, "ordem": 5},
                      {"inicio": 101, "fim": 99999, "valorUnitario": 57.50, "ordem": 6}
                    ]
                  }
                ]
              }
            ]
            """;

    String NOT_FOUND_TABELA = """
            {
              "statusCode": 404,
              "mensagem": "Tabela tarifaria não encontrada.",
              "mensagemUsuario": "Não foi encontrada nenhuma tabela tarifaria com o ID informado.",
              "classException": "TabelaTarifariaNaoEncontradaException",
              "timeStamp": "2026-05-08T15:47:38.3091132"
            }
            """;

    String SUCCESS_LOTE_ = """
            [
              {
                "ativo": true,
                "dataVigencia": "2027-11-02",
                "id": 21,
                "nome": "Tabela 2028"
              },
              {
                "ativo": true,
                "dataVigencia": "2028-06-15",
                "id": 22,
                "nome": "Tabela 2029"
              },
              {
                "ativo": true,
                "dataVigencia": "2029-03-01",
                "id": 23,
                "nome": "Tabela 2030"
              }
            ]
         
            """;


    String BAD_REQUEST_FORMAT = """
            {
              "statusCode": 400,
              "mensagem": "Campo formato inválido.",
              "mensagemUsuario": "Algum campo do JSON está com o formato inválido, revise a documentação.",
              "classException": "HttpMessageNotReadableException",
              "timeStamp": "2026-05-08T14:57:29.4830233"
            }
            """;

    String BAD_REQUEST_CATEGORIA_NOME = """
            {
              "statusCode": 400,
              "mensagem": "A categoria não pode null ou vázio.",
              "mensagemUsuario": "Categoria não pode ser null, ou ter um nome em branco.",
              "classException": "CategoriaNomeException",
              "timeStamp": "2026-05-08T15:26:40.8499159"
            }
            """;

    String BAD_REQUEST_ORDEM = """
            {
              "statusCode": 400,
              "mensagem": "Erro no campo ordem da categoria INDUSTRIAL.",
              "mensagemUsuario": "As faixas não pode ter intervalos que se cruzem, corrigam o JSON.",
              "classException": "FaixaTarifariaValidacaoCamposException",
              "timeStamp": "2026-05-08T15:28:56.0656058"
            }
            """;

    String BAD_REQUEST_FIM_INICIO = """
            {
              "statusCode": 400,
              "mensagem": "Erro: fim deve ser maior que inicio na categoria INDUSTRIAL.",
              "mensagemUsuario": "As faixas não pode ter intervalos que se cruzem, corrigam o JSON.",
              "classException": "FaixaTarifariaValidacaoCamposException",
              "timeStamp": "2026-05-08T15:47:00.6107197"
            }
            """;

    String BAD_REQUEST_CONTINUIDADE = """
            {
              "statusCode": 400,
              "mensagem": "Erro no campo inicio da categoria: deve ser fim da faixa anterior + 1 INDUSTRIAL.",
              "mensagemUsuario": "As faixas não pode ter intervalos que se cruzem, corrigam o JSON.",
              "classException": "FaixaTarifariaValidacaoCamposException",
              "timeStamp": "2026-05-08T15:47:38.3091132"
            }
            """;
}

