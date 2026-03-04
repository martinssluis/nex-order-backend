# Nex Order API

API REST para sistema de gestão de empresas desenvolvida com Spring Boot, seguindo arquitetura em camadas.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (desenvolvimento)
- **Maven**

## 📂 Estrutura do Projeto e Pastas Principais

```
├── 📁 backend
│   ├── 📁 .mvn
│   │   └── 📁 wrapper
│   │       └── 📄 maven-wrapper.properties
│   ├── 📁 Gestao-De-Empresas
│   │   ├── 📁 src
│   │   │   ├── 📁 main
│   │   │   │   ├── 📁 java
│   │   │   │   │   └── 📁 com
│   │   │   │   │       └── 📁 example
│   │   │   │   │           └── ☕ Main.java
│   │   │   │   └── 📁 resources
│   │   │   └── 📁 test
│   │   │       └── 📁 java
│   │   └── ⚙️ pom.xml
│   ├── 📁 src
│   │   ├── 📁 main
│   │   │   ├── 📁 java
│   │   │   │   └── 📁 com
│   │   │   │       └── 📁 aceleradev
│   │   │   │           └── 📁 backend
│   │   │   │               ├── 📁 config
│   │   │   │               │   └── ☕ TestConfig.java
│   │   │   │               ├── 📁 entities
│   │   │   │               │   └── ☕ Client.java
│   │   │   │               ├── 📁 repositories
│   │   │   │               │   └── ☕ ClientRepository.java
│   │   │   │               ├── 📁 resources
│   │   │   │               │   └── ☕ ClientResource.java
│   │   │   │               ├── 📁 services
│   │   │   │               │   └── ☕ ClientService.java
│   │   │   │               └── ☕ BackendApplication.java
│   │   │   └── 📁 resources
│   │   │       ├── 📁 static
│   │   │       ├── 📁 templates
│   │   │       ├── 📄 application-test.properties
│   │   │       ├── 📄 application.properties
│   │   │       └── ⚙️ application.yml
│   │   └── 📁 test
│   │       └── 📁 java
│   │           └── 📁 com
│   │               └── 📁 aceleradev
│   │                   └── 📁 backend
│   │                       └── ☕ BackendApplicationTests.java
│   ├── 📁 untitled
│   │   ├── 📁 src
│   │   │   ├── 📁 main
│   │   │   │   ├── 📁 java
│   │   │   │   │   └── 📁 com
│   │   │   │   │       ├── 📁 aceleradev
│   │   │   │   │       └── ☕ Main.java
│   │   │   │   └── 📁 resources
│   │   │   └── 📁 test
│   │   │       └── 📁 java
│   │   └── ⚙️ pom.xml
│   ├── ⚙️ .gitattributes
│   ├── ⚙️ .gitignore
│   ├── 📝 HELP.md
│   ├── 📄 mvnw
│   ├── 📄 mvnw.cmd
│   └── ⚙️ pom.xml
└── 📝 README.md
```

### **`backend/`**
Pasta raiz do projeto backend em Java com Spring Boot e Maven.

### **`backend/src/main/java/com/aceleradev/backend/`**
Contém o código-fonte principal da aplicação Spring Boot, organizado em camadas:

- **`BackendApplication.java`** - Classe principal da aplicação, ponto de entrada (`main`) do Spring Boot

- **📦 `entities/`** - Entidades JPA que representam as tabelas do banco de dados
  - `Client.java` - Entidade cliente com anotações JPA (@Entity, @Id, @GeneratedValue)

- **🗄️ `repositories/`** - Interfaces que estendem JpaRepository para operações de banco de dados
  - `ClientRepository.java` - Repositório para operações CRUD de Client

- **💼 `services/`** - Camada de lógica de negócio com injeção de dependências
  - `ClientService.java` - Serviços relacionados ao Client

- **🌐 `resources/`** - Controllers REST que expõem os endpoints da API
  - `ClientResource.java` - Endpoints REST para Client

- **⚙️ `config/`** - Classes de configuração da aplicação (database seeding, beans, etc)

### **`backend/src/main/resources/`**
Contém arquivos de configuração e recursos da aplicação:
- **`application.properties`** / **`application.yml`** - Arquivos de configuração da aplicação (banco de dados, portas, profiles etc)
- **`application-test.properties`** - Configurações específicas para o ambiente de teste
- **`static/`** - Arquivos estáticos (CSS, JS, imagens), se necessário
- **`templates/`** - Templates de visualização (por exemplo, Thymeleaf), se utilizados

### **`backend/src/test/java/com/aceleradev/backend/`**
Contém os testes automatizados da aplicação:
- **`BackendApplicationTests.java`** - Classe de testes base da aplicação

### **`backend/.mvn/`**
Arquivos relacionados ao Maven Wrapper, permitindo rodar o projeto sem Maven instalado globalmente:
- **`maven-wrapper.properties`** - Configurações do Maven Wrapper

### **`backend/pom.xml`**
Arquivo de configuração Maven principal do projeto. Define dependências, plugins e configurações de build.

### **`backend/Gestao-De-Empresas/`**
Projeto Maven adicional (módulo) com sua própria estrutura `src` e `pom.xml`. Pode ser um módulo antigo, um experimento ou um subprojeto relacionado à gestão de empresas.

### **`backend/untitled/`**
Outro projeto Maven separado, possivelmente criado para testes/investigações. Também possui sua própria estrutura `src` e `pom.xml`.

### **Arquivos de Configuração na Raiz:**
- **`.gitignore`** / **`.gitattributes`** - Arquivos de configuração do Git para ignorar arquivos/pastas e ajustar atributos de commits
- **`mvnw`** / **`mvnw.cmd`** - Scripts do Maven Wrapper para rodar o Maven via linha de comando em Linux/Mac (`mvnw`) ou Windows (`mvnw.cmd`)
- **`README.md`** - Arquivo de documentação principal do projeto
