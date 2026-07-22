# 🏅 Gestion JO – Plateforme de Gestion des Jeux Olympiques

Plateforme numérique développée pour le CIO dans le cadre des Jeux Olympiques de Dakar. Elle expose une **API REST** complète destinée aux applications web/mobiles, ainsi qu'un **Web Service SOAP** pour le système d'information historique, avec documentation **OpenAPI/Swagger**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-XAMPP-blue)
![License](https://img.shields.io/badge/status-completed-success)

## 📑 Sommaire

- [Contexte](#-contexte)
- [Technologies](#-technologies)
- [Architecture du projet](#-architecture-du-projet)
- [Prérequis](#-prérequis)
- [Installation et démarrage](#-installation-et-démarrage)
- [Documentation API REST (Swagger)](#-documentation-api-rest-swagger)
- [Web Service SOAP](#-web-service-soap)
- [Fonctionnalités](#-fonctionnalités)
- [Endpoints REST](#-endpoints-rest)
- [Collection Postman](#-collection-postman)
- [Auteur](#-auteur)

## 🎯 Contexte

Dans le cadre des JO de Dakar, le CIO souhaite mettre à disposition des données en temps réel pour deux types de clients :
- Des **applications web/mobiles** consommant une API REST
- Un **système d'information historique** utilisant uniquement des Web Services SOAP

## 🛠 Technologies

| Catégorie | Techno |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 3.3 (Web, Data JPA, Validation, Web Services) |
| Base de données | MySQL (via XAMPP) |
| Documentation REST | springdoc-openapi (Swagger UI) |
| Web Service SOAP | Spring-WS + JAXB |
| Build | Maven |
| Autres | Lombok |

## 🏗 Architecture du projet

src/main/java/sn/jo/gestion/
- entity/         : Athlete, Discipline, Epreuve, Resultat, Pays, enums (Sexe, TypeMedaille)
- repository/     : interfaces Spring Data JPA
- dto/            : objets de transfert (requetes/reponses REST)
- service/        : logique metier (CRUD, medailles, dashboard, recherche multicritere)
- controller/     : controleurs REST
- specification/  : recherche multicritere dynamique (Spring Data Specification)
- exception/      : gestion centralisee des erreurs (404, 400...)
- config/         : configuration du Web Service SOAP (Spring-WS)
- soap/           : endpoints SOAP (AthleteEndpoint, MedailleEndpoint)

src/main/resources/
- application.properties
- xsd/gestion-jo.xsd : contrat du Web Service SOAP

## ✅ Prérequis

- JDK 17+
- Maven 3.8+
- MySQL (via XAMPP), base jo_db creee automatiquement au demarrage

## 🚀 Installation et démarrage

1. Cloner le depot :
   git clone https://github.com/oumoukhairygaye-prog/gestion-jo-olympics.git
   cd gestion-jo-olympics
2. Demarrer MySQL (via le panneau de controle XAMPP).
3. Adapter si besoin src/main/resources/application.properties (utilisateur/mot de passe MySQL).
4. Compiler et lancer : mvn spring-boot:run
5. L'API demarre sur http://localhost:8080

## 📘 Documentation API REST (Swagger)

Une fois l'application demarree : http://localhost:8080/swagger-ui.html

## 🧾 Web Service SOAP

- WSDL : http://localhost:8080/ws/gestionJo.wsdl
- Operations : getAthlete (consultation d'un athlete), getTableauMedailles (tableau des medailles)

## ⚙️ Fonctionnalités

1. Gestion des athletes : CRUD complet (PUT/PATCH/DELETE/GET) + recherche multicritere
2. Gestion des disciplines : CRUD + consultation des athletes d'une discipline
3. Gestion des epreuves : CRUD + recherche par date et discipline
4. Gestion des resultats : enregistrement + attribution automatique des medailles + podium
5. Tableau des medailles : classement or > argent > bronze
6. Tableau de bord : statistiques globales (athletes, pays, medailles, classement par points)
7. Web Service SOAP : consultation athlete et tableau des medailles

## 🔗 Endpoints REST

| Ressource | Endpoints |
|---|---|
| Athletes | POST /api/athletes - PUT/PATCH/DELETE /api/athletes/{id} - GET /api/athletes?nom=&sexe=&paysId=&disciplineId= |
| Pays | POST/GET/DELETE /api/pays |
| Disciplines | POST/PUT/DELETE/GET /api/disciplines - GET /api/disciplines/{id}/athletes |
| Epreuves | POST/PUT/DELETE/GET /api/epreuves - GET /api/epreuves?date=&disciplineId= |
| Resultats | POST /api/resultats - GET /api/resultats/epreuve/{id} - GET /api/resultats/epreuve/{id}/podium |
| Medailles | GET /api/medailles/tableau |
| Dashboard | GET /api/dashboard/total-athletes - /total-pays - /total-medailles - /classement-points - /medailles-par-pays |

## 🧪 Collection Postman

Une collection Postman complete (gestion-jo-postman-collection.json) est disponible a la racine du depot, couvrant l'ensemble des endpoints ci-dessus.

## 👤 Auteur

**Oumou Khairy Gaye**