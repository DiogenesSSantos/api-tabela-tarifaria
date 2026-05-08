#  api-tabela-tarifaria
API-REST para gerenciar e calcular tarifas de água com base em categorias de consumidores e faixa de consumo.

---

## 🔗 Sumário
- [📖 Sobre o Projeto](#-sobre-o-projeto)
- [🛠 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [✨ Funcionalidades](#-funcionalidades)
- [📐 Arquitetura do Projeto](#-arquitetura-do-projeto)
- [🚀 Como Rodar o Projeto](#-como-rodar-o-projeto)
- [📚 Documentação da API](#-documentação-da-api)

---

## 📖 Sobre o Projeto
Este projeto implementa uma API REST para gerenciar e calcular tarifas de água com base em categorias de consumidores e faixas de consumo.
A solução é totalmente parametrizável: todas as faixas e valores ficam no banco de dados, permitindo ajustes sem necessidade de alterar o código.


## ✨ Funcionalidades
- **Criar tabelas tarifárias**
- **Lista tabelas tarifárias**
- **Excluir tabelas tarifárias**
- **Cadastro em lotes de tabelas tarifárias**
- **Calcúlo do valor a pagar**

## 🛠 Tecnologias Utilizadas
- **Java 21** → Linguagem consolidada no mercado.  
  👉 [Documentação oficial](https://docs.oracle.com/en/java/)
- **Spring-Framework + Spring boot** →  framework open‑source robusto para a plataforma Java.

  👉 [Documentação oficial](https://spring.io/why-spring)

- **ORM/Migrations: JPA + Flyway** → Banco de dados em memória.  
  👉 [Documentação oficial](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html)

- **PostgresSQL data base** → Banco de dados em relacional para persistencia de dados.  
  👉 [Documentação oficial](https://www.postgresql.org/docs/current/)
- **Swagger** → Para documentação de api um UI-UX.  
  👉 [Documentação oficial](https://springdoc.org)

- **Docker** → Containerização para um deploy simples e consistente.  
  👉 [Documentação oficial](https://docs.docker.com/)

- **Copilot** → inteligência artificial auxiliando no desenvolvimento (teste) 
  👉 [Documentação oficial](https://learn.microsoft.com/pt-br/copilot/?utm_source=copilot.com/)

---



---

## 📐 Arquitetura do Projeto
```tree
src/
 ├── main/
 │   ├── java/
 │   │   └── com/github/diogenesssantos/api
 │   │       ├── config/
 │   │       |        └── ConfigOpenAPI
 │   │       |
 │   │       ├── controller/      
 │   │       |            └── TabelaTarifariaController
 |   |       |            └── TarifaController
 |   |       |       
 |   |       |       
 |   |       ├── docs/
 |   |       |      └── TabelaTarifariaDocumentacaoOpenAPI
 |   |       |      └── CalculoDocumentacaoOpenAPI       
 |   |       |
 |   |       |
 |   |       ├── dtos/
 |   |       |      └──calculos/
 |   |       |      |         └── CalculoRequestDTO  
 |   |       |      |         └── CalculoResponseDTO
 |   |       |      |         └── DetalheFaixaDTO
 |   |       |      |         └── FaixaDTO
 |   |       |      |
 |   |       |      └──tabelatarifaria/   
 |   |       |                       └── CategoriaRequestDTO
 |   |       |                       └── FaixaRequestDTO
 |   |       |                       └── TabelaTarifaRequestDTO
 |   |       |
 |   |       |
 |   |       ├── exception/
 |   |       |           └── CalculoRequestException
 |   |       |           └── CategoriaNomeException
 |   |       |           └── CategoriaNomeInvalidoException
 |   |       |           └── FaixaNaoCobreCOnsumoException
 |   |       |           └── FaixaTarifariaException
 |   |       |           └── FaixaTarifariaValidacaoCampos
 |   |       |           └── TabelaTarifariaException
 |   |       |           └── TabelaTarifariaNaoLocalizadaException                 
 |   |       |
 |   |       |
 |   |       ├── exceptionhandler/
 |   |       |                  └── ApiExceptionHandler
 |   |       |                  └── Error
 |   |       |      
 |   |       |
 |   |       ├── model/
 |   |       |       └── Categoria
 |   |       |       └── FaixaTarifaria
 |   |       |       └── TabelaTarifaria   
 |   |       |
 |   |       |
 |   |       ├── repository/
 |   |       |            └── FaixaTarifariaRepository
 |   |       |            └── TabelaTarifariaRepository
 |   |       |
 |   |       ├── service/
 |   |       |         └── CalculoService
 |   |       |         └── TabelaTarifariaService
 |   |       |                                 
 |   |       └── start        
 |   |    
 |   |
 │   └── resources/
 │       ├── 
 │       └── application.yml
 │
 └── test/
     └── java/....
```

---

## 🚀 Como Rodar o Projeto

### 🔧 Pré-requisitos
- **Docker** 
- **Java 21+**
- **Maven** (ou utilize `mvnw`)
- **PostgreSQL** (latest)

### ▶️ Rodando a aplicação



**Docker**

*apenas rode o comando no seu terminal (autoconfigurado com a api executando na porta ``8080`` e o postgresql na porta ``5432`` para teste local, não produção)*
```
docker run -p 8080:8080 -p 5432:5432 diogenesssantos/api-tabela-tarifaria-ras
```


### Como acessar
1. Certifique-se de que a aplicação está rodando (após seguir os passos em ["Como Rodar o Projeto"](#-como-rodar-o-projeto)).
2. Abra seu navegador e navegue até a seguinte URL:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

3. Fazendo requisição utilizando uma ferramenta de desenvolvimento (Postman, Insomnia, etc...) copie o link abaixo.

👉 [http://localhost:8080/](http://localhost:8080) e o determinado end-point abaixo.




## ✨ End-Points
| Método | Nome do endpoint             | Descrição                                                                   |
|--------|------------------------------|-----------------------------------------------------------------------------|
| POST   | /api/calculos                | Calcular tarifa  a parti de uma requisição JSON.                            | 
| POST   | /api/tabelas-tarifarias      | Cria uma nova tabela tarifaria a parti de uma requisição JSON.              |
| POST   | /api/tabelas-tarifarias/lote | Cria novas tabelas tarifarias a parti conjunto [ ] de uma requisições JSON. |
| GET    | /api/tabelas-tarifarias      | Buscar todas as tabelas tarifarias.                                         |
| GET    | /api/tabelas-tarifarias/{id} | Buscar tabela tarifaria a parti de um ``id``.                               |


---
##  Exemplos JSON para requisições, copie e utilize nas chamadas, teste comportamento errôneos  para ver as tratativas de  errors.

**POST /api/calculos** 
---
*Json da requisição*
```
{
  "categoria": "PUBLICO",
  "consumo": 7945
}
```
*Faz o calculo do consumo pela categoria solicitada e suas respectiveis faixas.*

---




**POST api/tabelas-tarifarias**
---
*Json da requisição*
```
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
```
*Salva uma tabela tarifaria.*


---

**POST api/tabelas-tarifarias/lote**
---
*Json da requisição*
```
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
          ....    
]
```
*Salva um conjunto de  tabelas tarifarias.*

---


**GET api/tabelas-tarifarias**
---
*Resposta JSON*
```
{
 [
    {
        "ativo": true,
        "dataVigencia": "2029-03-01",
        "id": 20,
        "nome": "Tabela 2030"
    },
    {
        "ativo": true,
        "dataVigencia": "2029-03-01",
        "id": 23,
        "nome": "Tabela 2030"
    }, 
        .....
  ]  
}
```
---

**GET api/tabelas-tarifarias/{id}**
---
*Resposta JSON*
```
{
    "ativo": true,
    "dataVigencia": "2029-03-01",
    "id": 20,
    "nome": "Tabela 2030" 
}

```
*Buscar uma tabela tarifaria por id.*

---

**DELETE api/tabelas-tarifarias/{1}**
---
*Resposta JSON*
```
no-content

```
*Deleta uma tabela pelo id.*

---







---

##  Melhorias e implementações futuras
- **Implementação atualizar tabela tarifarias por id.**
- **Implementação Autenticação para criação das tabelas tarifarias.**
- **Implementação de valor fixo para consumos acimas da ultima faixa da tabela tarifaria correspondente.**
- **cobertura completa de testes.**

---


Swagger para documentação interativa — facilita avaliação do desafio.
---
## 📚 Documentação da API
A documentação interativa da API foi gerada com **Swagger** e pode ser acessada após a inicialização da aplicação.

Ela permite que você **visualize e teste todos os endpoints disponíveis**.


### O que você pode fazer na documentação

- **Visualizar Endpoints:** Veja todos os endpoints da API, com seus métodos (GET, POST), parâmetros e descrições.
- **Testar Requisições:** Use a funcionalidade *Try it out* para enviar requisições e ver as respostas em tempo real.
- **Consultar Schemas:** Entenda a estrutura das entidades (DTOs) utilizadas nas requisições e respostas.  