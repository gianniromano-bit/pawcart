# PawCart

Pet supply e-commerce platform (CPAN-228 group project). Customers browse and order pet products; staff manage the catalog and stock across distribution centres.

## Team
- Gianni Romano: entities, repositories, seed data, Add Item form and validation
- Ikechukwu Ike: layout, home and info pages, list view (filter, sort, pagination), styling

## Stack
Spring Boot 3, Thymeleaf, Spring Data JPA, Spring Security, Spring Data REST (under `/api`), H2, Bootstrap 5.

## Run
```
./mvnw spring-boot:run
```
Open http://localhost:8080. Add an item at http://localhost:8080/items/new. H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:file:./data/pawcart`).

Sample data is loaded from `data.sql` on every start.
