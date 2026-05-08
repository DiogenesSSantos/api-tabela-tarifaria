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

*apenas rode o comando no seu terminal (autoconfigurado para teste, não produção)*
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