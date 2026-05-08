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


    String BAD_REQUEST = """
            
            {
              "statusCode": 400,
              "mensagem": "Campo formato inválido.",
              "mensagemUsuario": "Algum campo do JSON  está com o formato inválido, revise a documentação.",
              "classException": "HttpMessageNotReadableException",
              "timeStamp": "2026-05-08T14:57:29.4830233"
            }
            """;

}
