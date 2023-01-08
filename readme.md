# Le mauvais coin BACKEND

## INSTALLATION

1)clonner l'archive et l'ouvrir dans un IDE depuis la branche developp.  

2)verifier que le projet est bien en version Java 17.  

3)A l'aide de Uwamp ou autre logiciel permettant d'utiliser phpmyadmin créer en local une BDD mysql s'intitulant "le_mauvais_coin".  

4)Vérifier dans le fichier se trouvant dans src > main > ressources > application.properties que les parametres suivant soit à jour avec votre BDD:  

spring.flyway.url = jdbc:mysql://localhost:3306/le_mauvais_coin   (url de votre BDD)  
spring.flyway.user = root   (user de votre BDD)  
spring.flyway.password = root   (mdp du user de votre BDD)  

spring.datasource.url=jdbc:mysql://localhost:3306/le_mauvais_coin   (url de votre BDD)  
spring.datasource.username=root   (user de votre BDD)  
spring.datasource.password=root   (mdp du user de votre BDD)  

5) Executer la commande "mvn clean install" nb: pour utiliser les commandes mvn sous Windows il faut installer maven ou l'executer grace à un IDE telle que IntellJ ou Eclipse.  

6) Lancer le back-office avec la commande "mvn spring-boot:run"

# Le mauvais coin FRONTEND

## Installation

1) Clonner le répertoire depuis la branche developp

2) Installer Node JS sur le site officiel (https://nodejs.org/en/download/)

3) Lancer l'invite de commande (CMD)

4) Installer Angular avec la commande suivante 'npm install -g @angular/cli'

5) Naviguer vers le dossier du projet avec la cmd (ex : cd Documents, cd GitHub, cd T-WEB-501-LIL-5-1-jobboard-lucas.zielinski)

6) Installer les modules du projet avec la commande 'npm install'

7) Lancer le projet avec la commande 'npm start'

8) Accéder au site à l'adresse 'http://localhost:4200/'

# Le mauvais coin FONCTIONNALITÉS

En tant que simple visiteur du site :

    - Postuler à une annonce
    - S'incrire en tant qu'Utilisateur ou Entreprise

En tant qu'utilisateur :

    - Postuler à une annonce sans avoir à remplir le formulaire avec ses informations
    - Modifier son profil

En tant qu'entreprise :

    - Modifier son profil
    - Ajouter, supprimer, modifier une annonce

