# Gestion JO - Plateforme de Gestion des Jeux Olympiques

Plateforme developpee avec Spring Boot 3 / Java 17 dans le cadre de l'examen Web Services (JO de Dakar). Elle expose une API REST complete pour les applications web/mobiles, ainsi qu'un Web Service SOAP pour le systeme d'information historique, avec documentation OpenAPI/Swagger.

## Architecture du projet

- entity/ : Athlete, Discipline, Epreuve, Resultat, Pays, enums (Sexe, TypeMedaille)
- repository/ : interfaces Spring Data JPA (acces base de donnees)
- dto/ : objets de transfert (requetes/reponses de l'API REST)
- service/ : logique metier (CRUD, medailles, dashboard, recherche multicritere)
- controller/ : controleurs REST
- specification/ : recherche multicritere dynamique
- exception/ : gestion centralisee des erreurs (404, 400...)
- config/ : configuration du Web Service SOAP (Spring-WS)
- soap/ : endpoints SOAP (AthleteEndpoint, MedailleEndpoint)
- resources/xsd/gestion-jo.xsd : contrat du Web Service SOAP

## Technologies

- Spring Boot 3.3 (Web, Data JPA, Validation, Web Services)
- MySQL (via XAMPP)
- springdoc-openapi (Swagger UI)
- Spring-WS + JAXB (Web Service SOAP)
- Lombok

## Prerequis

- JDK 17+
- Maven 3.8+
- MySQL (ex. via XAMPP), avec une base creee : jo_db (creee automatiquement au demarrage)

## Demarrer le projet

1. Demarrer MySQL (ex. via le panneau de controle XAMPP).
2. Adapter si besoin src/main/resources/application.properties (utilisateur/mot de passe MySQL).
3. Compiler et lancer : mvn spring-boot:run
4. L'API demarre sur http://localhost:8080

## Documentation API REST (Swagger)

Une fois l'application demarree : http://localhost:8080/swagger-ui.html

## Web Service SOAP

- WSDL : http://localhost:8080/ws/gestionJo.wsdl
- Operations disponibles : getAthlete (consultation d'un athlete), getTableauMedailles (tableau des medailles)

## Fonctionnalites implementees

1. Gestion des athletes : CRUD complet (PUT/PATCH/DELETE/GET) + recherche multicritere
2. Gestion des disciplines : CRUD + consultation des athletes d'une discipline
3. Gestion des epreuves : CRUD + recherche par date et discipline
4. Gestion des resultats : enregistrement + attribution automatique des medailles + podium
5. Tableau des medailles : classement or > argent > bronze
6. Tableau de bord : statistiques globales (athletes, pays, medailles, classement par points)
7. Web Service SOAP : consultation athlete et tableau des medailles

## Auteur

Oumou Khairy Gaye