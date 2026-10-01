# Nex Order API

REST API for a company management system built with Spring Boot, following a layered architecture.

## 🚀 Technologies

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (development)
- **Maven**

## Running with Docker

1. Copy the example file and set your database credentials:
   ```bash
   cp .env.example .env
   ```
2. Build and start the app:
   ```bash
   docker compose up --build
   ```
3. API: http://localhost:8080 · Swagger: http://localhost:8080/swagger


## 📂 Project Structure and Main Folders

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
Root folder of the Java backend project, built with Spring Boot and Maven.

### **`backend/src/main/java/com/aceleradev/backend/`**
Contains the main source code of the Spring Boot application, organized in layers:

- **`BackendApplication.java`** - Main application class, the Spring Boot entry point (`main`)

- **📦 `entities/`** - JPA entities that represent the database tables
  - `Client.java` - Client entity with JPA annotations (@Entity, @Id, @GeneratedValue)

- **🗄️ `repositories/`** - Interfaces extending JpaRepository for database operations
  - `ClientRepository.java` - Repository for Client CRUD operations

- **💼 `services/`** - Business logic layer with dependency injection
  - `ClientService.java` - Client-related services

- **🌐 `resources/`** - REST controllers that expose the API endpoints
  - `ClientResource.java` - REST endpoints for Client

- **⚙️ `config/`** - Application configuration classes (database seeding, beans, etc.)

### **`backend/src/main/resources/`**
Contains the application's configuration files and resources:
- **`application.properties`** / **`application.yml`** - Application configuration files (database, ports, profiles, etc.)
- **`application-test.properties`** - Settings specific to the test environment
- **`static/`** - Static files (CSS, JS, images), if needed
- **`templates/`** - View templates (e.g., Thymeleaf), if used

### **`backend/src/test/java/com/aceleradev/backend/`**
Contains the application's automated tests:
- **`BackendApplicationTests.java`** - Base test class of the application

### **`backend/.mvn/`**
Files related to the Maven Wrapper, which allows running the project without a globally installed Maven:
- **`maven-wrapper.properties`** - Maven Wrapper settings

### **`backend/pom.xml`**
Main Maven configuration file of the project. Defines dependencies, plugins, and build settings.

### **`backend/Gestao-De-Empresas/`**
Additional Maven project (module) with its own `src` structure and `pom.xml`. It may be an old module, an experiment, or a subproject related to company management.

### **`backend/untitled/`**
Another separate Maven project, possibly created for tests or experiments. It also has its own `src` structure and `pom.xml`.

### **Root Configuration Files:**
- **`.gitignore`** / **`.gitattributes`** - Git configuration files to ignore files/folders and adjust commit attributes
- **`mvnw`** / **`mvnw.cmd`** - Maven Wrapper scripts to run Maven from the command line on Linux/Mac (`mvnw`) or Windows (`mvnw.cmd`)
- **`README.md`** - Main documentation file of the project
