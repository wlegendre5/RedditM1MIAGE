# RedditM1MIAGE

Réalisé par DELANOU Quentin et LEGENDRE William, étudiants en M1 MIAGE à l'Université de Nantes

URL de l'application : https://reddit-m1miage.appspot.com/

Sujet du projet : 

TinyReddit est une application sociale de partage signet. Un utilisateur poste un signet. Les autres peuvent voter pour ou contre le signet. Reddit affiche les signets selon leur “karma” : leur score.

Nous nous limiterons aux fonctionnalités suivantes:
--> Poster un message 
--> Voter pour ou contre un message
--> Consulter ses messages, et les messages pour lesquels on a voté.
--> Lister les messages les mieux classés.

Notre application se base sur l'API REST TopicEndpoint que nous avons conçue, on y accède à partir du lien suivant :

https://apis-explorer.appspot.com/apis-explorer/?base=https://reddit-m1miage.appspot.com/_ah/api

Les performances temporelles des servlets proposés sont affichées à millisecondes à la fin de l'exécution.

Les principales fonctionnalités attendues ont été implémentées au sein de la solution. 
Au vu des difficultés rencontrées durant la phase de développement, nous identifions divers axes d'amélioration :
- Implémentation d'une authentification basée sur le compte Google
- Amélioration de l'interface
- Ajout de commentaires sur les topics
- Lier les méthodes de l'API à l'utilisateur authentifié

Réalisé avec Google App Engine et Google Cloud Endpoints.
________________________________________________________

Temps de réponse pour la récupération des topics sur lesquels l'user authentifié a voté :
(Pour tester cette fonctionnalité avec les jeux de données générés par nos servlets, veuillez vous authentifier en tant que user "u[1-100]" !)


WRITE (Génération de 1000 topics) :
Avec 100 voters : 3984 milliseconds
Avec 1000 voters : 43996 milliseconds
Avec 5000 voters : 211765 milliseconds

VARIATION :
1000 - 100 : 40012 milliseconds
5000 - 1000 : 167769 milliseconds

~~~~~~~~~~

READ (Récupération des topics pour lesquels un user a voté à partir du servlet contenu dans le lien "Mes topics upvote")
Avec 100 voters : 94 milliseconds
Avec 1000 voters : 5988 milliseconds
Avec 5000 voters : 12653 milliseconds

VARIATION
1000 - 100 : 4653 milliseconds
5000 - 1000 : 6665 milliseconds