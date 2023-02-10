# Web Crawler ZG

Web Crawler ZG é uma aplicação desenvolvia em Groovy com o objetivo de fazer a raspagem de dados do site do Governo, salvar esses dados e enviar e-mails com os mesmos anexados.

## Funcionalidades do projeto

- `Raspagem de dados`: Realiza a raspagem de dados de três URLs diferentes através a biblioteca Jsoup e HttpBuilder, armazena os arquivos na pasta Downloads.
- `Enviar e-mails`: A partir de uma lista de e-mails, envia os arquivos adquiridos na raspagem, como anexo através da biblioteca Javax Mail.
- `CRUD e-mails`: A lista de e-mail é persistida no PostgreSQL e possui um CRUD para manipulação.

## Tecnologias utilizadas

- `Groovy`
- `PostgreSQL`
- `Gradle`

## Abrir e rodar o projeto

**Executar pela IDE**
- Tenha o Java 8, Groovy 2.4 e PostgreSQL instalados.
- Clone esse repositório.
- Execute o arquivo webCrawler.sql no PostgresSQL.
- Configure o acesso ao banco de dados na classe Connection dentro do package Models.
- A classe responsável por iniciar a aplicação é a Main.
- O projeto vêm com um e-mail configurado, não sei exatamente por quanto funcionará esse e-mail, caso pare de funcionar ou deseje usar outro e-mail, basta editar o login e password na classe SendEmail, caso não seja um e-mail da Microsoft, outras configurações do SMTP terão que ser alteradas.
- Manipule os e-mails pelo CRUD, a aplicação não possui nenhum e-mail cadastrado inicialmente.

<div align="center">
    <img src="https://i.ibb.co/QJwJZVf/Screenshot-from-2023-02-10-20-29-28.png" />
    <img src="https://i.ibb.co/3mQgC8v/Screenshot-from-2023-02-10-20-30-14.png" />
</div>