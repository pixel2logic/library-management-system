# Library Management System

A Spring Boot web application for managing books and authors with full CRUD operations and entity relationships.

## Features

- **Author Management** - Create, read, and update author information
- **Book Management** - Create, read, and update book details
- **Entity Relationships** - One-to-Many relationship between Authors and Books
- **Custom Queries** - Inner join query to display books with author information
- **Data Validation** - Input validation and error handling
- **Pre-loaded Data** - Sample data with 10 authors and 10 books

## Tech Stack

- **Spring Boot 3.1.5** - Application framework
- **Spring Data JPA** - Database operations
- **H2 Database** - In-memory database
- **JSP & JSTL** - Server-side rendering
- **Maven** - Build tool
- **JUnit & Mockito** - Testing

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### Clone the repository
```bash
git clone https://github.com/pixel2logic/library-management-system.git
cd library-management-system
```

### Build and run
```bash
mvn clean install
mvn spring-boot:run
```

### Access the application
- Main app: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:librarydb`
  - Username: `sa`
  - Password: (leave empty)

## Project Structure

```
src/
├── main/
│   ├── java/com/bds/library/
│   │   ├── config/          # Data initialization
│   │   ├── controller/      # Web controllers
│   │   ├── dto/             # Data transfer objects
│   │   ├── entity/          # JPA entities
│   │   ├── repository/      # Data repositories
│   │   └── service/         # Business logic
│   ├── resources/
│   │   └── application.properties
│   └── webapp/WEB-INF/views/
│       ├── authors/         # Author JSP pages
│       └── books/           # Book JSP pages
└── test/                    # Unit tests
```

## Testing

Run all tests:
```bash
mvn test
```

Run specific test:
```bash
mvn test -Dtest=AuthorServiceTest
```

## License

This project is created for educational purposes.

## Author

Developed as part of BDS course assignment