# Projet mode open source pour les auditeurs du cycle C informatique Cnam Liban GLG204 et SMB215

Ce projet est un systeme de communication entre 3 entitees : Un vendeur qui ordonne des marchendises d'un entrepôt qui a son tour les ordonne du fabricant en cas ou il n'a pas de stock suffisant. Les 3 entitees son capable de mettre a jour et suivre la trace des ordres.

Le projet utilise MongoDb comme base de donnee, des services web Java JAX-RS comme backend et Angular 4 comme frontend.


# Pour demarrer le projet frontend:
1 - Installer nodejs
2 - Installer angular cli
3 - Naviger dans le repertoire frontend
4 - npm install
5 - ng serve

# Pour demarrer la base de donnee MongoDb (Linux et OsX)
1 - export PATH=/usr/local/Cellar/mongodb/3.4.7/bin:$PATH
2 - sudo mongod --dbpath /data/db

# Pour demarrer les services web (backend)
1 - Installer netbeans
2 - Installer tomcat
3 - Ouvrir le repertoire 'backend' dans netbeans
4 - Build with dependencies pour installer tout les librairies necessaires
5 - Run with tomcat

# Pour tester les services web
1 - Installer 'Postman'
2 - Importer les fichier Json des test du repertoire 'tests' du projet dans postman