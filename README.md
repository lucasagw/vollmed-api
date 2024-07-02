# Voll Med API

Bem-vindo à API Voll Med, desenvolvida para suportar o aplicativo móvel da clínica médica Voll Med. Esta API oferece operações CRUD, validações, paginação e ordenação.

## Índice

- [Introdução](#introdução)
- [Instalação](#instalação)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Configuração](#configuração)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Introdução

A API Voll Med fornece endpoints para gerenciar dados de pacientes, médicos, agendamentos e mais. Esta é a primeira versão oficial da API.

## Instalação

Para configurar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:
    ```sh
    git clone https://github.com/lucasagw/vollmed-api.git
    cd voll-med-api
    ```

2. Configure o banco de dados PostgreSQL e ajuste o arquivo `application.properties` conforme necessário:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/vollmed
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    spring.jpa.hibernate.ddl-auto=update
    spring.flyway.baselineOnMigrate=true
    ```

3. Compile e execute a aplicação:
    ```sh
    ./mvnw spring-boot:run
    ```

## Uso

A API oferece uma variedade de endpoints para gerenciar dados clínicos. Exemplos de uso dos endpoints estão descritos abaixo.

## Endpoints

### Pacientes

- **GET /pacientes**: Retorna uma lista de pacientes com paginação e ordenação.
- **GET /pacientes/{id}**: Retorna os detalhes de um paciente específico.
- **POST /pacientes**: Cria um novo paciente.
- **PUT /pacientes/{id}**: Atualiza os dados de um paciente existente.
- **DELETE /pacientes/{id}**: Remove um paciente do sistema.

### Médicos

- **GET /medicos**: Retorna uma lista de médicos com paginação e ordenação.
- **GET /medicos/{id}**: Retorna os detalhes de um médico específico.
- **POST /medicos**: Cria um novo médico.
- **PUT /medicos/{id}**: Atualiza os dados de um médico existente.
- **DELETE /medicos/{id}**: Remove um médico do sistema.

### Agendamentos

- **GET /agendamentos**: Retorna uma lista de agendamentos com paginação e ordenação.
- **GET /agendamentos/{id}**: Retorna os detalhes de um agendamento específico.
- **POST /agendamentos**: Cria um novo agendamento.
- **PUT /agendamentos/{id}**: Atualiza os dados de um agendamento existente.
- **DELETE /agendamentos/{id}**: Remove um agendamento do sistema.

## Configuração

### Variáveis de Ambiente

Certifique-se de configurar as variáveis de ambiente para conectar ao banco de dados e outras configurações necessárias.

## Contribuição

Contribuições são bem-vindas! Por favor, abra uma issue ou envie um pull request.

## Licença

Este projeto está licenciado sob os termos da [MIT License](LICENSE).
