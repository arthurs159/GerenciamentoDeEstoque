<h2>API REST DE CONTROLE DE ESTOQUE</h2>



API feita utilizando Java com Spring Boot.

Nesse projeto utilizei TDD para me guiar na construção da API.

Utilizei esse diagrama para criar o relacionamento entre as entidades. 

![Capturar](https://user-images.githubusercontent.com/77133022/148588286-3137cd95-3540-4428-a72d-5c0d8509625f.PNG)



```
Os end-point são

GET /company => Listar a empresa cadastrada.

GET /products => Listar os produtos.

GET /products/{id} => Buscar produtos por ID.

GET /products/name/{nomeDoProduto} => Buscar produto por nome.

GET /products/brand/{nomeDaMarca} => Listar todas os produtos de certa marca.

POST /products => adicionar produtos.

PATCH /products/{id}/add => adicionar quantidade em algum produto por id.

PATCH /products/{id}/remove => remover quantidade em algum produto por id.

```