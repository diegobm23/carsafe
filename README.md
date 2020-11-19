# CarSafe

Uma simples API Rest para cadastrar e consultar clientes e apólices de seguros.

### Tecnologias utilizadas

- Spring Boot
- Apache Tomcat
- MongoDB
- Gradle
- IDE IntelliJ Community

### Para rodar a aplicação

- Instalar o [MongoDB](https://docs.mongodb.org/manual/installation/), e deixar rodando na porta padrão.
- Clonar o repositório em uma pasta.
- Abrir o projeto no [IntelliJ](https://www.jetbrains.com/pt-br/idea/download/#section=windows).
- No menu do Gradle, em Task > application > bootRun (clicar com o botão direito do mouse e ir em Run ou Debug se preferir debugar o código).

### WAR
- O arquivo .war se encontra versionado na pasta /build/libs.

### Para testar a aplicação

Na pasta /Teste Postman existem dois arquivos para serem importados no [Postman](https://www.postman.com/downloads/). Cada um desses arquivos é uma coleção de chamadas para todos os endpoints pedidos na descrição do teste (requisições POST, PUT, GET e DELETE).

Sugiro começar pelas chamadas de POST, pois elas vão popular a base de dados para que as chamadas GET tenham dados para consultar.
