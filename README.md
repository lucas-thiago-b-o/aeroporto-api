# Aeroporto Backend

Projeto de sistema de compra de passagem aérea utilizando:

- Java 17
- JUnit 4
- Spring Security
- Model Mapper
- Lombook
- Java JWT
- MySQL ou H2

## Getting started

Não há configurações especificas. Com o projeto em disposição, apenas defina no `application.properties` em qual ambiente deseeja trabalhar, pois há 2 applications de bancos de dados separados. Após isso rode os comando: `mvn clean` e depois `mvn install`, ou unicamente `mvn clean install` para que todas as dependências sejam baixadas para funcionamento do projeto.

## Test and Deploy

Todos os testes se encontrm na pasta ```test```, no root do diretório, devidamente separados. Para que se possa rodar os testes apenas use o comando: `mvn test` ou `mvn clean test`