<h1>API Shopping Cart desenvolvida para o desafio Invillia Reinvent</h1>

<h2>Shopping Cart é uma API backend funcional que respeita a convenção REST (Methods e Responde) nas operações que foram implementadas.</h2>

<h3>Badges:</h3>

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

<h3>Pré-requisitos de como rodar a aplicação/testes:</h3>

A aplicação segue o modelo sugerido pelo desafio. Durante o desenvolvimento foi necessário alterar o type das variáveis userId e sku, que compõem a chave primária, de String para Integer por uma questão de incompatibilidade de Length do MySQL que exige uma chave com menos de 1000 bytes. Portanto, <b>as requisições enviadas para o Postman devem usar números em vez de letras/nomes</b>.

<h3>Tecnologias utilizadas:</h3> JDK 16.0.2</br>IDEA JetBrains Intellij Community Edition 2021.2</br>Gradle 7.2</br>Spring Boot 2.5.4</br>Docker Desktop 20.10.8</br>MySql 8.0.26</br>Postman 8.11.1
