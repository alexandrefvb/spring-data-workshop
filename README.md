#Workshops TQI 2014 - Spring data

Este repositório contém o material para o workshop sobre spring-data realizado na TQI.

O objetivo do workshop é incluir a persistência de dados no exemplo de enquetes apresentado no Workshop sobre [RESTful Webservices](https://github.com/alexandrefvb/restful-webservices-workshop).

Os objetos do domínio Enquete e Opcao foram mapeados como entidades JPA.

O repositório de enquetes que mantinha as enquetes em memória foi alterado para persistir os dados em um banco de dados Derby utilizando o Spring Data JPA.

É um exemplo completo sobre como construir serviços REST com persistência dos dados em banco de dados relacional.

##Rodando o projeto

Para rodar o projeto são necessários:
* JDK 1.7
* Maven 3.0+

Execute:
```mvn jetty:run``` na raiz de source/enquete-spring-data.

Os serviços implementados são os mesmos do workshop anterior com as seguintes alterações:
* Utilização de paginação para ilustrar o funcionamento do PagingAndSortingRepository do Spring Data
* Criação de serviço para atualizar enquetes (PUT)

