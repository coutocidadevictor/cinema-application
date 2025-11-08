# 🎬 Cinema Application

Projeto desenvolvido como atividade para o **Senac**.

O sistema é um **site para cadastro e análise de filmes**, com front-end dinâmico que consome uma **API REST desenvolvida em Spring Boot**.  

No back-end, implementamos **endpoints para todos os verbos HTTP (GET, POST, PUT e DELETE)**, tanto para filmes quanto para análises. Esses endpoints são usados pelo front-end para:

- Obter e exibir dados na página (GET)
- Enviar dados ao salvar novos registros ou editar existentes (POST/PUT)
- Remover registros (DELETE)

O front-end utiliza **jQuery** para consumir a API e atualizar a interface de forma dinâmica.

---

## 🚀 Tecnologias utilizadas

- **Java 17+**
- **Spring Boot**
- **Thymeleaf**
- **CSS / JS**
- **jQuery**
- **MySQL**
- **Maven**

---
  
## 🗄️ Banco de dados

O projeto utiliza **MySQL** como banco de dados.

O script de criação das tabelas está disponível no arquivo: `cinema_api.sql`

Basta executar esse script em seu servidor MySQL antes de iniciar a aplicação.

As configurações de conexão podem ser ajustadas no arquivo: `src/main/resources/application.properties`

---

## ⚙️ Execução do projeto

### 🔧 1. Configuração do banco de dados
- Crie um banco de dados no MySQL.
- Execute o script `cinema_api.sql`.
- Atualize `application.properties` com o nome do banco, usuário e senha.

### ▶️ 2. Compilar e executar

No terminal, dentro da pasta do projeto:

`mvn clean package`

O comando acima gera o arquivo .war na pasta:
`target/cinema-application.war`

Esse .war pode ser implantado em um servidor como Apache Tomcat.
