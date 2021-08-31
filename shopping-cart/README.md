# Invillia Reinvent - Challenge

Agora é a hora de colocarmos em prática tudo o que relembramos em nossas sessões e o que pudemos estudar à mais durante esse tempo.

Para tal propomos aqui um desafio.


## Challenge

Devemos construir um componente dessa e-store que viemos evoluindo durante o programa. 

Este componente é uma aplicação REST que tem por objetivo servir operações sobre um carrinho de compra.

Para esse desafio solicitamos que atendam:
* Criem uma aplicação **funcional**
* As operações implementadas respeitem a convenção **REST** (Methods e Response)
* Seja implementado usando **Java 11+** e **Spring Boot 2.5.3+**
* Seja implementado um código **legível** e **organizado**
* Atendam as **User Stories** abaixo descritas e os **contratos** estabelecidos
* Utilizar uma base de dados para o armazenamento das informações
* README atualizado com instruções de execução


## Epic

Continuamos a desenvolver a nossa e-store. Agora precisamos construir o carrinho de compras (shopping-cart).

### US 1

Eu, como comprador, quero ter um único carrinho de compras onde eu possa adicionar produtos.

### US 2

Eu, como comprador, quero adicionar um ou mais de um item de cada produto.

### US 3

Eu, como comprador, quero listar os itens no meu carrinho de compra.

### US 4

Eu, como comprador, quero incrementar a quantidade de um produto já no carrinho.

### US 5

Eu, como comprador, quero remover todos os items de um mesmo produto do carrinho.

### US 6

Eu, como comprador, quero decrementar a quantidade de um produto já no carrinho.

### US 7

Eu, como comprador, quero calcular o valor total dos itens no meu carrinho.

### US 8

Eu, como comprador, quero esvaziar meu carrinho.


## Contratos de Interface

### Adicionar itens
`POST /shopping-cart/<user-id>/items/<SKU>`

Body
```
{
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1 //optional, default value 1
}
```

Response
`CREATED 201`

```
{
    "sku":"ASDFGHJK",
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1
}
```

### Editar itens
`PUT /shopping-cart/<user-id>/items/<SKU>`

Body
```
{
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1 //optional, default value 1
}
```

Response
`OK 200`

```
{
    "sku":"ASDFGHJK",
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1
}
```

### Editar a quantidade de itens
`PATCH /shopping-cart/<user-id>/items/<SKU>?quantity=<new quantity>`

Body *None*

Response
`OK 200`

```
{
    "sku":"ASDFGHJK",
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1
}
```

### Remover um produto do carrinho
`DELETE /shopping-cart/<user-id>/items/<SKU>`

Body *None*

Response
`OK 200`

```
{
    "sku":"ASDFGHJK",
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1
}
```

### Recuperar um produto do carrinho de compra
`GET /shopping-cart/<user-id>/items/<SKU>`

Body *None*

Response
`OK 200`

```
{
    "sku":"ASDFGHJK",
    "price":"10.00",
    "name":"Bananas",
    "quantity": 1
}
```

### Recuperar o carrinho de compra
`GET /shopping-cart/<user-id>`

Body *None*

Response
`OK 200`

```
{
    "items": [
        {
            "sku":"ASDFGHJK",
            "price":"10.00",
            "name":"Bananas",
            "quantity": 1
        },
        {
            "sku":"ASDFGHJZ",
            "price":"20.00",
            "name":"Maçãs",
            "quantity": 2
        }
    ],
    "total":"50.00"
}
```

### Remover o carrinho de compra
`DELETE /shopping-cart/<user-id>`

Body *None*

Response
`OK 200`

```
{
    "items": [
        {
            "sku":"ASDFGHJK",
            "price":"10.00",
            "name":"Bananas",
            "quantity": 1
        },
        {
            "sku":"ASDFGHJZ",
            "price":"20.00",
            "name":"Maçãs",
            "quantity": 2
        }
    ],
    "total":"50.00"
}
```

### Error message

Response
Any **4XX** or **5XX** errors
```
{
    "resource":"<resource name>",
    "error_key":"<error type key>",
    "message":"<error message>",
    "resource_key":"<resource id>"
}
```

**Ex:**
Response
`RESOURCE NOT FOUND 404`
```
{
    "resource":"product",
    "error_key":"product.not.found",
    "message":"There is no product on shopping cart for the given SKU.",
    "resource_key":"ASDFGHJK"
}
```

## Passos para entrega do challenge

1. Dar fork neste repositório.
2. Implementar desafio.
4. Propor um pull request.


## Extras

Caso consigam completar os requisitos aqui estabelecidos e se sintam confortáveis em ir um pouco além, podem ficar à vontade em acrescentar qualquer melhoria além destes. Como por exemplo:

* Testes de Unidade
* Docker
* Logs
* API Doc (Swagger Open API)
* Autenticação
* Qualquer lib ou framework adicional
* Testes de componente (API)



## Desenvolvendo o Projeto

### Tecnologias utilizadas
- Swagger
- MySql
- Dbeaver-ce
- Intellij
- Docker 
- Spring Data JPA MySql
- JPA Mapeamento Relacional
- Gradle

### Swagger
Principal fonte para o Swagger:
    
    https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger
    
Acesso:

    http://localhost:8080/swagger-ui.html

### Docker MySql
Gerando container Docker com criação de base:

    docker run -p 3306:3306 -v /tmp:/tmp --name mysql-poc --detach -e MYSQL_ROOT_PASSWORD="root" -e MYSQL_ROOT_HOST=% -e MYSQL_DATABASE=shoppingcart -d mysql/mysql-server:8.0 --lower_case_table_names=1 --init-connect='GRANT CREATE USER ON *.* TO 'root'@'%';CREATE USER admin@%` IDENTIFIED BY "root";GRANT ALL PRIVILEGES ON . TO admin@% WITH GRANT OPTION;FLUSH PRIVILEGES;'

Script para inserção do cliente no banco de dados:
    
    insert into shoppingcart.`user` (id_user, cpf, name)
    values (1, '99999999999', 'Erika');

Para conferir o funcionamento dos endpoints atualizando o banco de dados, utilizei consultas SQL abaixo:

    select * from  shoppingcart s
    
    select * from  product p
    
    select * from  user u

### Spring Data JPA MySql
Material de referência:

    https://spring.io/guides/gs/accessing-data-mysql/

### JPA Mapeamento Relacional
Material de referência:

    https://www.baeldung.com/jpa-one-to-one

    https://www.baeldung.com/hibernate-one-to-many

### Teste de Unidade

Ainda preciso estudar e entender melhor como montar os testes, mas o que consegui criar está funcionando.

### Considerações finais
O desafio proposto no Invillia Reinvent, foi a criação de um carrinho de compras para o usuário.
Ao desenvolver o que nos foi proposto, consegui entender melhor a comunicação das ferramentas, despertei meu 
interesse de estudar novas tecnologias de desenvolvimento e aprender muito mais do que o simples Java, como tinha em
mente no início do desafio.

Para desenvolver o proposto muitas coisas que não conhecia precisei pesquisar e aprender a utilizar e este 
aprendizado, assim como a experiência de participar do projeto, levarei para vida.
Mesmo que ainda iniciando na carreira de desenvolvimento, foi possível ver quão grandioso é este universo e quantas 
possibilidades temos de aprender coisas novas, de ver formas de pensar diferentes que levam a uma conclusão em 
comum e ainda conhecer pessoas de diversos locais com distintas realidades e com o mesmo objetivo que é entrar par o 
universo da TI.

Mesmo em pouco tempo de convívio termino este desafio levando em minha bagagem o conhecimento aprendido, as pessoas 
maravilhosas que conheci, as experiências de vida trocadas e a certeza que sim, existe sim empresas que se preocupam
com as pessoas e principalmente com aquelas que formam sua equipe de colaboradores.

Agradeço a empresa a oportunidade de participar do Reinvent e a todos os envolvidos pela dedicação e empenho para 
compartilhar conosco seus conhecimentos e por fazer tudo isso dar certo.

