# Invillia Reinvent - Challenge

##Banco de Dados
Na raiz do projeto acessar o diretório docker.
executar o seguinte comando
docker-compose up --build db

##APLICAÇÃO 
Para executar a aplicação na raiz do projeto
./gradlew bootRun -Dspring.profiles.active=<PROFILE>

## Primeira chamada deve ser o POST /shopping-cart/<user-id>/items/<SKU>`


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
