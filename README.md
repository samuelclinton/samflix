![Samflix](https://github.com/samuelclinton/samflix/blob/main/img/logo.png)

O projeto Samflix proposto para a pós-graduação [FIAP](https://www.fiap.com.br/) em [Arquitetura e desenvolvimento Java](https://postech.fiap.com.br/curso/arquitetura-desenvolvimento-java) consiste em uma API de gerenciamento de vídeos. O projeto roda totalmente dockerizado em containers e pode ser executado utilizando o docker compose para testes e avaliações.

## Índice

1. [Tecnologias](#tecnologias)
2. [Ferramentas](#ferramentas)
3. [Instalação](#instalação)
4. [Documentação](#documentação)
5. [Jornada de desenvolvimento](#jornada)
6. [Autores](#autores)
7. [Licença](#licença)

## Tecnologias

As tecnologias utilizadas durante o desenvolvimento do projeto.

#### [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
Linguagem de programação escolhida para implementar a lógica do sistema, garantindo confiabilidade, segurança e escalabilidade.

#### [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
Utilizado como framework para agilizar o desenvolvimento da API, oferecendo recursos como injeção de dependências, mapeamento de URLs, tratamento de requisições HTTP e muito mais.

#### [Reactor project](https://projectreactor.io/docs)
O Reactor Project é uma biblioteca reativa para construir sistemas assíncronos e baseados em eventos no Java. Ele oferece suporte para programação reativa, permitindo o desenvolvimento de aplicativos altamente responsivos e escaláveis. O Reactor facilita a manipulação de fluxos de dados de forma eficiente, tornando-o uma escolha popular para sistemas que lidam com eventos em tempo real e grandes volumes de dados.

#### [Docker](https://www.docker.com/docs/)
Plataforma que facilita a criação, implantação e execução de aplicativos em contêineres. Com o Docker, é possível empacotar a aplicação e suas dependências em um contêiner para fácil distribuição e implantação.

#### [MongoDB](https://www.mongodb.com/docs/)
MongoDB é um banco de dados NoSQL flexível e escalável, ideal para aplicações que lidam com dados não estruturados. Sua abordagem documental e capacidade de escalar horizontalmente o tornam popular para aplicações modernas de grande volume de dados.

#### [Maven](https://maven.apache.org/guides/index.html)
Gerenciador de dependências utilizado para facilitar a configuração e o gerenciamento das bibliotecas e dependências do projeto.

#### [Git](https://git-scm.com/doc)
Sistema de controle de versão utilizado para rastrear alterações no código-fonte, facilitando o trabalho em equipe, o versionamento e a colaboração no projeto.

#### [Lombok](https://projectlombok.org/features/)
Biblioteca que permite reduzir a quantidade de código boilerplate, como getters, setters e construtores, através de anotações, melhorando a produtividade do desenvolvimento.

## Ferramentas

#### [Intellij IDEA](https://www.jetbrains.com/pt-br/idea/)
Ambiente de desenvolvimento integrado (IDE) utilizado para escrever, depurar e testar o código-fonte do projeto. O Intellij IDEA oferece recursos avançados de produtividade, facilitando o desenvolvimento em Java e agilizando o processo de construção da API.

#### [Postman](https://www.postman.com/)
Plataforma de colaboração e testes de API que permite enviar e receber solicitações HTTP, testar os endpoints da API, verificar as respostas e realizar a depuração de forma eficiente.

#### [GitHub](https://github.com/)
Plataforma de hospedagem de repositórios de controle de versão Git, utilizada para armazenar e gerenciar o código-fonte do projeto. O GitHub permite o trabalho colaborativo, controle de versões, rastreamento de alterações e facilita a integração com ferramentas de desenvolvimento.

## Instalação
Estes são os passos para a execução do projeto num ambiente local, utilizando o docker compose.
1. Clone este repositório git na sua máquina local
   ``` 
   git clone https://github.com/samuelclinton/samflix.git
   ```
   
2. Caso o Docker não esteja instalado no seu computador, siga os passos da [documentação oficial](https://docs.docker.com/engine/install/).
   
3. Não é necessário fazer a build de cada imagem dos serviços, eles já estão disponíveis no Docker Hub, porém caso queira, cada diretório tem seu Dockerfile que pode ser usado para criar uma imagem com o seguinte comando:
   ``` 
   docker build -t [nome-da-imagem] [caminho-ate-Dockerfile]
   ```
   
   É recomendado seguir o padrão de nome samflix, pois caso altere, o arquivo `compose.yml` precisará ser alterado também.
   
4. Abra um Powershell (ou equivalente no seu OS) no diretório que o arquivo `compose.yml` estiver localizado e execute o comando:
   ``` 
   docker compose up -d
   ```
   
5. Aguarde alguns segundos para que todos os containers sejam executados e após aguardar pode se verificar se os containers estão rodando com o comando:
   ``` 
   docker container ls -a
   ```
   
6. Com todos os serviços rodando o endereço da API será `localhost:8080`, porém as requisições do arquivo postman desse repositório já deve estar configuradas para que todos os testes possam ser feitos.

## Documentação
A documentação no padrão Open Api 3 (Swagger) estará disponível para consulta assim que os containers estiverem sendo executados corretamente no endereço `/docs.html`.

## Jornada

A jornada da fase 4 foi complicada, não tanto por fatores técnicos, mas por fatores familiares (festas de final de ano e viagens), então estou satisfeito com o que consegui fazer sozinho.

Comecei criando uma aplicação sem utilizar a Clean Architecture, fazendo como eu já sabia, porém, utilizando WebFlux ao invés do pacote web padrão do Spring Boot. Isso foi uma quebra de paradigma bem legal até, antes eu não entendia muito bem essa tecnologia e após bater cabeça com ela finalmente entendi o porquê de utilizar, e o projeto em sí (achei que era algo do Spring, mas descobri que é um projeto separado, na verdade).

Inicialmente eu ia criar uma aplicação mais completa com contas de usuários e streaming, porém depois de diversos contratempos por motivos familiares, resolvi apenas cumprir os requisitos funcionais e acabei simplificando a aplicação, mantendo os requisitos.

Chegava a hora então de aplicar o Clean Architecture na lógica que eu já havia desenvolvido, isso foi interessante por dois motivos.
Primeiro me mostrou a vantagem do Clean Architecture, caso eu tivesse desenvolvido desde o começo com essa arquitetura, e precisasse trocar, o fato das lógicas do negócio estarem isoladas facilitaria muito o refactor que tive que fazer.
Segundo que percebi que a Clean Architecture para projetos muito simples é desnecessária e complicada, gerando muitas classes e pacotes redundantes. Esse foi um dos motivos que decidi por não criar controllers exclusivos da clean architecture e usar os contoladores do Spring diretamente, vi as duas abordagens e seria apenas criar uma classe de proxy que faria a chamada de alguns métodos dos use cases, achei que caso precisasse ter um refactor mais para frente, seria um trabalho bem fácil e decidi seguir nesse caminho.

Finalmente, com tudo funcionando e com a cobertura de testes em 89% achei que os requisitos estavam todos prontos, disponibilizei as imagens do projeto em um repositório no [Docker Hub](https://hub.docker.com/u/samuelclinton) pra facilitar a subida do projeto com apenas um comando `compose up`, sem necessidade de buildar o serviço localmente via Maven. Deixei assim mesmo os `Dockerfile` para referência e para caso seja necessário gerar uma nova imagem.

## Autores

- [Samuel Clinton](https://www.linkedin.com/in/samuelclinton)

## Licença

[Licença MIT](https://opensource.org/license/mit/): permite o uso, a modificação e a distribuição do software sem restrições significativas.
