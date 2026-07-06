# Skill Diagnostic System

## Opis projektu

Skill Diagnostic System to aplikacja backendowa służąca do diagnozowania poziomu wiedzy użytkownika w wybranym obszarze. Użytkownik wybiera kategorię, podkategorię oraz poziom trudności, a system generuje zestaw pytań umożliwiający sprawdzenie wiedzy. Liczba pytań w quizie zależy od wybranego poziomu trudności (łatwy – 3 pytania, średni – 5 pytań, trudny – 10 pytań). Po zakończeniu quizu obliczany jest wynik, a informacje o rozwiązanym teście oraz osiągniętym rezultacie są zapisywane w relacyjnej bazie danych. Dzięki historii wykonanych testów użytkownik może analizować swoje postępy w nauce oraz identyfikować obszary wymagające dalszego rozwoju.

## Funkcjonalności

- generowanie quizów na podstawie wybranej kategorii, podkategorii i poziomu trudności,
- losowanie pytań z bazy danych,
- dynamiczna liczba pytań zależna od poziomu trudności:
  - łatwy → 3 pytania,
  - średni → 5 pytań,
  - trudny → 10 pytań,
- weryfikacja odpowiedzi użytkownika,
- automatyczne obliczanie wyniku quizu,
- zapisywanie wyników i historii rozwiązanych testów w relacyjnej bazie danych,
- analiza postępów użytkownika w czasie na podstawie historii wyników.

## Technologie

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- REST API
- Maven
- Git
- Lombok

## Architektura projektu

Projekt został zbudowany w oparciu o architekturę warstwową:

- Controller – obsługa żądań HTTP
- Service – logika biznesowa
- Repository – komunikacja z bazą danych
- Entity – modele danych
- DTO – obiekty transferowe

## Uruchomienie projektu

### Wymagania:
- Java 21
- Maven
- PostgreSQL

### Kroki:
1. Sklonuj repozytorium:
   git clone https://github.com/rafalwalasek/Skill-Diagnostic-System.git

2. Utwórz bazę danych PostgreSQL

3. Uzupełnij konfigurację w application.properties

4. Uruchom aplikację:
   mvn spring-boot:run

## Endpointy API

### Statystyki pytań i kategorii

| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | /allQuestionCount | Zwraca liczbę wszystkich pytań w bazie |
| GET | /categoryQuestionCount | Zwraca liczbę pytań dla wybranej kategorii (`category`) |
| GET | /attemptsCount | Zwraca liczbę podejść dla wybranej kategorii (`category`) |

---

### Tematy i pytania

| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | /topics | Zwraca listę tematów i podtematów |
| GET | /questionsToDiagnostic | Zwraca losowe pytania do diagnostyki na podstawie `subtopicId` i `difficulty` |

---

### Diagnostyka i wyniki

| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | /attempts | Zwraca liczbę prób diagnostycznych dla `subtopicId` i `difficulty` |
| POST | /userResults | Przetwarza odpowiedzi użytkownika i zwraca wynik quizu |

## Struktura projektu

src/main/java
├── controller
├── service
├── repository
├── entity
|── dto

## Możliwości rozwoju

- dodanie Spring Security + JWT (uwierzytelnianie i autoryzacja użytkowników),
- testy jednostkowe i integracyjne (JUnit, Mockito),
- dokumentacja API przy użyciu Swagger / OpenAPI,
- konteneryzacja aplikacji (Docker + Docker Compose),
- panel administracyjny do zarządzania pytaniami i kategoriami,
- rozbudowa systemu statystyk i analityki postępów użytkownika,
- caching (np. Redis) w celu optymalizacji wydajności,
- wersjonowanie API (np. /api/v1/...),
- migracje bazy danych (Flyway lub Liquibase),
- wdrożenie CI/CD (GitHub Actions).