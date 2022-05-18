# Controle de Estoque

### Tecnologias usadas:

1. Java 11
2. Spring boot 2.6.7
3. Maven
4. H2 Database
5. PostgreSQL
6. Liquibase
7. Lombok

### Profiles

> ***h2***
> > Profile default que utiliza o banco H2 em memória


> ***postgres***
> > Profile que utiliza banco postgres

### Endpoints

Endpoints utilizados no projeto, datalhes nas coleções que estão na pasta "postman".

| Method | Endpoint           | Input               | Output              | Descrição                                       |
|--------|--------------------|---------------------|---------------------|-------------------------------------------------|
| GET    | /produto/{id}      |                     | ProdutoDto          | Buscar produto por id                           |
| POST   | /produto           | ProdutoDto          | ProdutoDto          | Inserir/Alterar produto                         |
| DEL    | /produto           |                     |                     | Deletar produto                                 |
| DEL    | /produto/{id}      | Pageable            | Page<ProdutoDto>    | Lista paginada de ProdutoDto por Tipo           |
| GET    | /tipo-produto/{id} |                     | TipoProdutoDto      | Buscar tipo produto por id                      |
| POST   | /tipo-produto      | TipoProdutoDto      | TipoProdutoDto      | Inserir/Alterar tipo produto                    |
| DEL    | /tipo-produto      |                     |                     | Deletar tipo produto                            |
| POST   | /movimento         | MovimentoEstoqueDto | MovimentoEstoqueDto | Inserir movimento do estoque (entrada ou saída) |

### Build

> ***./mvnw spring-boot:run***
> > Profile com banco H2

> ***./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres***
> > Profile com banco postgres

### Tests

> ***mvn test***
> > 82% coverage