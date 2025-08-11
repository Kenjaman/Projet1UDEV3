# Projet MyNetflix (UDEV3)

Ce dépôt contient un projet étudiant visant à créer une application de type Netflix. Le projet a été implémenté en utilisant deux architectures web distinctes :
1.  Une application avec un frontend en **JavaScript** communiquant avec un backend (API) en **PHP**.
2.  Une application web monolithique développée en **Java EE** (JSP/Servlets).

## Structure du Dépôt

Le projet est divisé en plusieurs répertoires principaux :

-   `APINetFlix/` : Le backend de l'API RESTful en PHP.
-   `AppliJS/` : Le frontend en JavaScript qui consomme l'API PHP.
-   `JSP/` : L'application web complète en Java EE.
-   `DiagrammeJSP/` : Contient des diagrammes de conception pour l'application Java.

---

### 1. API PHP (`APINetFlix/`)

Ce répertoire contient une API en PHP qui sert de backend pour l'application JavaScript. Elle expose plusieurs points d'accès (endpoints) pour récupérer des données depuis une base de données.

-   **Fichier principal de l'API** : `api.php`
-   **Documentation et tests** : `ws_netflix.php` offre une interface web simple pour visualiser et tester les différentes routes de l'API.
-   **Fonctionnalités** :
    -   Lister les séries, saisons, épisodes, personnes (acteurs, réalisateurs).
    -   Filtrer les données (par exemple, récupérer les saisons d'une série spécifique).
    -   Retourne les données au format JSON.

### 2. Application JavaScript (`AppliJS/`)

Il s'agit d'une application frontend "client-side" qui utilise l'API PHP pour afficher les données de manière dynamique.

-   **Fichiers principaux** :
    -   `index.html` : Page d'accueil.
    -   `liste.html` : Affiche la liste des séries et permet de naviguer vers les saisons et épisodes.
    -   `fiche.html` : Probablement destinée à afficher les détails d'un élément.
-   **Logique applicative** : La logique pour appeler l'API et manipuler le DOM se trouve dans `includes/scripts/`.
-   **Dépendance** : Pour fonctionner, cette application doit pouvoir joindre l'API PHP (l'URL est configurée en dur sur `http://localhost/apinetflix/`).

### 3. Application Java EE (`JSP/G7Netflix/`)

C'est une implémentation alternative et autonome de l'application, développée avec les technologies Java EE.

-   **Technologie** : Java 12, Servlets, JSP, JSTL, et Maven pour la gestion des dépendances.
-   **Architecture** :
    -   Application web packagée en `.war`, à déployer sur un serveur d'applications compatible (ex: Apache Tomcat).
    -   Elle se connecte directement à une base de données **MySQL** (via un connecteur JDBC).
    -   Les vues sont gérées par des pages JSP (`.jsp`) et la logique métier par des Servlets Java.
-   **Structure** : Le projet suit la structure standard des projets web Maven.

---

## Auteurs

Ce projet a été développé par un groupe d'étudiants :

-   Alexandre CARRALOU
-   Jonathan NICAUD
-   Kénan ROUX
-   Michel GILLET (Auteur des fichiers de l'API PHP)
