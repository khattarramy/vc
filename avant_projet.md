# Avant Projet
Dans ce document vous trouverez l'explication de nos choix dans le projet, les technologies utiliseées.

## Le projet
On a changé les idées du projet plusieurs fois car à chaque fois on affronte des obstacles;
1. La premiere fois, et après avoir mis le plan du projet et diviser le travail, le plan n'était pas bien detaillé, on a trouvé une manque d'information necessaire pour travailler en collaboration. Pour cela dans le deuxieme reunion on a bien detaillé chaque idée et on a ajouté des tables, fields au model.
2. On avait des problèmes dans la méthode de tracking où on suit chaque "item" dans un "order" au lieu de suivre l'order lui même, donc sauvegarder chaque item comme une ligne dans la database. On l'a corrigé, renouvellé le plan et on a mis le wireframe de l'application.

## Les technologies
### Le database:
- On a decidé premièrement d'utiliser le bigtable du google. Georges Harik a utilisé ses credits offerts du google pour l'activer, mais le problème était que ce service coûte par heure donc ses credits ont été fini en une semaine, voici l'issue qu'il a fait:
[Base de donnée BigTable](https://github.com/projets2017cl/vc/issues/88).

- Le deuxième choix était le firebase, qui est un ensemble de services d'hébergement pour n'importe quel type d'application, il utilise le NoSQL. Il offre des services d'authentification, de notfication, et un serveur de communication. Or c'est un back end complet qui fait tout notre travail donc on prefere de commencer à partir de zero.
- Donc on a finalement choisi d'utiliser le mangoDB.

### L'application mobile:
Comme on utilise Java le premier choix était une application mobile pour android seulement sur Android Studio. Mais pour l'application web on utilisera angular 2, on aura des components reutilisables de même on peut les utiliser dans Ionic 2. Pour cela on a decidé de developper en hybride pour utiliser le même code pour generer les applications web, Android et iOS.

### Le Backend:
Pour le developpement on travaille sur **netbeans**, on utilise le serveur Tomcat 7, et pou les RESTfuls web services on utilise JAX-RS.
