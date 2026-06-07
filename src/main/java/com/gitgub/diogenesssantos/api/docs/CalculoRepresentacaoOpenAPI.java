package com.gitgub.diogenesssantos.api.docs;

public interface CalculoRepresentacaoOpenAPI {


    String CORPO_EXEMPLO = """
            
            {
              "categoria": "COMERCIAL",
              "consumo": 10
            }
            
            
            """;


    String SUCCESS = """
            
            {
                "categoria": "COMERCIAL",
                "consumoTotal": 10,
                "valorTotal": 150,
                "detalhamento": [
                  {
                    "faixa": {
                      "inicio": 0,
                      "fim": 10
                    },
                    "m3Cobrados": 10,
                    "valorUnitario": 15,
                    "subtotal": 150
                  }
                ]
            }
            
            """;


    String BAD_REQUEST_CONSUMO = """
            
            {
              "statusCode": 400,
              "mensagem": "Erro JSON campos inválidos.",
              "mensagemUsuario": "O corpo da requisição incorreto, observe os campos abaixo inválidos e corrigia seguindo a instrução.",
              "classException": "MethodArgumentNotValidException",
              "timeStamp": "2026-06-07T16:36:25.7163881",
              "errorsCampos": [
                {
                  "campo": "consumo",
                  "mensagem": "Consumo inválido. Não pode null."
                }
              ]
            }
            """;

    String BAD_REQUEST_CATEGORIA = """
            
            {
              "statusCode": 400,
              "mensagem": "Erro JSON campos inválidos.",
              "mensagemUsuario": "O corpo da requisição incorreto, observe os campos abaixo inválidos e corrigia seguindo a instrução.",
              "classException": "MethodArgumentNotValidException",
              "timeStamp": "2026-06-07T16:36:25.7163881",
              "errorsCampos": [
                {
                  "campo": "categoria",
                  "mensagem": "Categoria inválida. Valores aceitos: COMERCIAL, INDUSTRIAL, PARTICULAR, PUBLICO"
                }
              ]
            }
            """;

}
