# 🎫 My Event - Sistema de Gerenciamento de Eventos

- ### API desenvolvida para o sistema de gerenciamento de eventos da ``My Event``.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.4**
    - Spring Web
    - Spring Security
    - Spring Data JPA
    - Spring Validation
    - Spring Mail + FreeMarker
    - Spring Authorization Server
- **PostgreSQL** + Flyway
- **MapStruct** para mapeamento de DTOs
- **Lombok** para redução de boilerplate
- **Feign Client** para integração entre serviços
- **Envers** para auditoria de entidades
- **JWT** para autenticação com tokens
- **JUnit 5 + Mockito** para testes

---

## 🧪 Testes
- **Execute os testes com:**

```bash
   ./gradlew test
```

---

## 🗂️ Migrações com Flyway

- Scripts SQL são versionados e executados automaticamente na inicialização do projeto. Armazene-os em:
```src/main/resources/db/migration/```

---

## 🔄 Auditoria
O projeto utiliza Spring Envers para manter o histórico de alterações nas entidades anotadas com ``@Audited``.

---

## 📦 Estrutura do Projeto

```bash

src/
├── main/
│   ├── java/
│   │   └── com/myevent/
│   │       │──api/
│   │       │  ├── controller/
│   │       │  ├── exception/
│   │       │  ├── converter/
│   │       │  └── dto/
│   │       │ 
│   │       ├── common/
│   │       │  ├── anotations/
│   │       │  ├── email/
│   │       │  ├── exception/
│   │       │  └── listener/
│   │       │  └── util/
│   │       │ 
│   │       ├── core/
│   │       │  ├── configuration/
│   │       │  ├── jackson/
│   │       │  ├── security/
│   │       │ 
│   │       ├── domain/
│   │       │  ├── entity/
│   │       │  ├── enums/
│   │       │  ├── repository/
│   │       │  └── service/
│   │       │
│   │       ├── infraestruct/
│   │       │  ├── initializer/
│   │       │  ├── mail/
│   │
│   └── resources/
│       ├── application.properties
│       ├── templates/             # Templates de e-mail (FreeMarker)
│       └── db/migration/          # Migrations Flyway
├── test/
│   └── java/
│       └── com/myevent/

