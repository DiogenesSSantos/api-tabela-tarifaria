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

## 📐 Arquitetura do Projeto - em construção.
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



**docker**
```
docker run -p 8080:8080 diogenesssantos/facilittecnologia:1.0
```

**docker-compose**
```
    version: "3.9"
services:
  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tarifadb
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: pass
    depends_on:
      - db

  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: tarifadb
      POSTGRES_USER: user
      POSTGRES_PASSWORD: pass
    ports:
      - "5432:5432"

```


### Como acessar
1. Certifique-se de que a aplicação está rodando (após seguir os passos em ["Como Rodar o Projeto"](#-como-rodar-o-projeto)).
2. Abra seu navegador e navegue até a seguinte URL:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

3. Fazendo requisição utilizando uma ferramenta de desenvolvimento (Postman, Insomnia, etc...) copie o link abaixo.

👉 [http://localhost:8080/](http://localhost:8080) e o determinado end-point abaixo.


> **⚠️ Atenção**
>
> Obrigatório fazer o login para acessar os end-points, necessitando do token JWT para acesso aos end-points.
---

**Faça uma requisição post http://localhost:8080/api/auth/login com o body abaixo, depois copie o JWT e 
faça as requisições necessárias no end-points /tarefas.**
```
{
  "nome": "facilit",
  "senha": "123"
}
```

## ✨ End-Points
| Método | Nome do endpoint     | Descrição                                                                            |
|--------|----------------------|--------------------------------------------------------------------------------------|
| POST   | /api/auth/login      | Login na api, obrigatório para todos end-points o token JWT.                         | 
| POST   | /tarefas             | Cria uma nova tarefa.                                                                |
| GET    | /tarefas             | Buscar todas tarefas registradas.                                                    | 
| GET    | /tarefas/id/{id}     | Consulte informações de uma tarefa através de um `id`.                              | 
| GET    | /tarefas/titulo      | Consulte informações de uma tarefa através de um `titulo`.                          |  
| GET    | /tarefas/descricao   | Consulte informações de uma tarefa através de uma `descricao`.                      |  
| ~~GET~~| ~~/tarefas/status~~  | ~~Buscar todas tarefas registrados por um `status`.~~                                |  
| PATCH  | /tarefas/id/{id}     | Atualiza uma ou mais informação de uma determinada tarefa pelo `id`.                | 
| PATCH  | /tarefas/titulo      | Atualiza uma ou mais informação de uma determinada tarefa pelo `titulo`.            | 
| PATCH  | /tarefas/descricao   | Atualiza uma ou mais informação de uma determinada tarefa pela `descricao`.         | 
| PATCH  | /tarefas/status/{id} | Atualiza campo status uma determinada tarefa pelo `id`, passando status atualizado. |

---
##  Decisões técnicas
- **Spring Boot por produtividade e ecossistema.**
- **H2 para o desafio; mas fácil troca para SQLite/Postgres ou mysql(roda os teste em um mysql com test-container).**
- **JWT para autenticação stateless e compatibilidade com front-ends.**
- **Lombok para reduzir boilerplate.**

---
##  Limitações conhecidas
- **H2 em memória não é adequado para produção; dados são voláteis.**
- **Autenticação simples (usuário fixo) — não há cadastro/recuperação de senha.**
- **Sem controle de permissões por usuário (qualquer usuário tem acesso a todas as tarefas, correto cada um visualizar a sua respectiva tarefa).**

---

##  Utilização de IA (Copilot) no projeto ##


- Configuração de ambiente, sem comprometer dados sensíveis. 
- Criação de casos de teste.
- Autocomplete e geração de código para teste com rapidez sem substituir a análise critica e testabilidade.
- Leitura de problemas de stack trace, complexas para melhor entendimento e resolucionar o problema.
- Refatoração e melhoria de legibilidade


---



##  Melhorias
- **Banco de dados mais seguro, robusto e independente (Mysql ou Postgres).**
- **Endpoint para atribuir tarefa a usuário e histórico de mudanças e por quem foi alterada a tarefa.**
- **Autenticação para apenas administradores e cargo de liderança posso criar tarefas.**

---

##   Importar collection Postman a partir do OpenAPI 

```
Importar collection Postman a partir do OpenAPI

1. Inicie a API localmente.
2. No Postman: Import → Link → cole http://localhost:8080/v3/api-docs → Import.
3. Execute POST /api/auth/login, copie o token e cole em `jwt`.
4. Teste os endpoints protegidos com header Authorization: Bearer {{jwt}}.
5. Para importar manualmente, copie o arquivo da raiz do projeto docs/openapi e use Import → Upload Files.

```
---


Swagger para documentação interativa — facilita avaliação do desafio.
---
## 📚 Documentação da API
A documentação interativa da API foi gerada com **Swagger** e pode ser acessada após a inicialização da aplicação.

Ela permite que você **visualize e teste todos os endpoints disponíveis**.


### O que você pode fazer na documentação

- **Visualizar Endpoints:** Veja todos os endpoints da API, com seus métodos (GET, POST, PATCH), parâmetros e descrições.
- **Testar Requisições:** Use a funcionalidade *Try it out* para enviar requisições e ver as respostas em tempo real.
- **Consultar Schemas:** Entenda a estrutura das entidades (DTOs) utilizadas nas requisições e respostas.  