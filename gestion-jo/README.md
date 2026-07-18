# Gestion JO – Plateforme de Gestion des Jeux Olympiques

Squelette de départ (Spring Boot 3 / Java 17) pour le projet Web Services : API REST + Web Service SOAP + documentation OpenAPI/Swagger.

## Architecture du dossier

```
src/main/java/sn/jo/gestion/
 ├── entity/       → Athlete, Discipline, Epreuve, Resultat, Pays, enums (Sexe, TypeMedaille)
 ├── repository/   → interfaces Spring Data JPA (accès DB)
 ├── service/      → (à créer) logique métier : médailles, dashboard, recherche multicritère
 ├── controller/   → (à créer) contrôleurs REST (@RestController)
 ├── dto/          → (à créer) objets de transfert (requêtes/réponses)
 └── config/       → (à créer) config SOAP (Spring-WS), OpenAPI, CORS...
```

## Prérequis

- JDK 17+
- Maven 3.8+ (ou utilise le wrapper `./mvnw` une fois généré)
- PostgreSQL installé, avec une base créée :
  ```sql
  CREATE DATABASE jo_db;
  ```

## Démarrer le projet

1. Adapte `src/main/resources/application.properties` avec ton user/mot de passe PostgreSQL.
2. Compile et lance :
   ```bash
   mvn spring-boot:run
   ```
3. L'API démarre sur `http://localhost:8080`.
4. La doc Swagger sera accessible sur `http://localhost:8080/swagger-ui.html` (une fois les contrôleurs créés).

> Remarque : les tables sont créées automatiquement au démarrage grâce à `spring.jpa.hibernate.ddl-auto=update`.

## Ce qui est déjà en place

- **Entités JPA** : Athlete, Discipline, Epreuve, Resultat, Pays + enums Sexe et TypeMedaille.
- **Repositories** de base pour chaque entité, avec quelques méthodes de recherche (par discipline, par date, etc.).
- `Resultat.attribuerMedaille()` : logique simple d'attribution automatique de médaille selon le classement (1 → OR, 2 → ARGENT, 3 → BRONZE).

## Prochaines étapes (à faire)

1. Créer les DTOs (ne jamais exposer directement les entités JPA dans l'API).
2. Créer les contrôleurs REST CRUD pour Athlete, Discipline, Epreuve.
3. Créer le service Resultat (enregistrement + attribution médailles + podium).
4. Créer l'endpoint Tableau des médailles (tri par or > argent > bronze).
5. Créer les endpoints Dashboard (stats).
6. Créer le Web Service SOAP avec Spring-WS (XSD + endpoint).
7. Générer/valider la doc Swagger.
8. Écrire les tests et exporter la collection Postman.

## Technologies

- Spring Boot 3.3 (Web, Data JPA, Validation, Web Services)
- PostgreSQL
- springdoc-openapi (Swagger UI)
- Lombok
