#Título: API Shopping Cart desenvolvida para o desafio Invillia Reinvent
 
Descrição: Shopping Cart é uma API backend funcional que respeita a convenção REST (Methods e Responde) nas operações que foram implementadas.  
 

##Badges: 

Status do Projeto: Concluído. 

Tabela de Conteúdos: 

Features: 
 
[x] Adicionar produtos no carrinho de compras;  
[x] Adicionar um ou mais item de cada produto;  
[x] Listar os itens;  
[x] Incrementar a quantidade de produtos existentes;  
[x] Remover todos os itens de um mesmo produto;  
[x] Decrementar a quantidade de produtos existentes;  
[x] Calcular o valor total dos itens;  
[x] Esvaziar carrinho. 

 

##Demonstração da aplicação: 

A aplicação segue o modelo sugerido pelo desafio. Durante o desenvolvimento foi necessário alterar o type das variáveis userId e sku, que compõem a chave primária, de String para Integer por uma questão de incompatibilidade de Length do MySQL que exige uma chave com menos de 1000 bytes. Portanto, as requisições enviadas para o Postman devem usar números em vez de letras/nomes.

Pré-requisitos de como rodar a aplicação/testes: 
 
Tecnologias utilizadas: JDK 16.0.2	 
                                        IDEA JetBrains Intellij Community Edition 2021.2 
                                        Gradle 7.2 
                                        Spring Boot 2.5.4 
                                        Docker Desktop 20.10.8 
                                        MySql 8.0.26 
                                        Postman 8.11.1 
                                        GIT 2.33.0 









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
