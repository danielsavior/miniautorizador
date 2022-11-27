# Mini autorizador

> Projeto do mini autorizador de transações.

### Tecnologias utilizadas

Para realização desse projeto foram usadas as tecnologias listadas:

- Java
- Spring boot
- MySQL
- Docker
- Docker compose

## Instalando miniautorizador

Para instalar o miniautorizador, siga os seguintes passos:

1- Build o projeto:
```
mvn clean install -DskipITs
```
O comando acima faz o build do projeto pulando apenas os testes de integração, os testes unitários são executados.

2- Construa as imagens e suba os containers:
```
docker-compose up
```

## Usando miniautorizador

Consulte o contrato das apis acessando o swagger disponível em:

```
http://localhost:8080/autorizador/swagger-ui.html
```

De posse dessas informações faça os testes utilizando a ferramenta de sua preferência para realizar as requisições.