IBuySu.com : Mode d’emploi
MERCI D’AVOIR INSTALLE IBUYSU.COM !
1.	Lancer IBuySu
IBuySu.com est un système d’achat/vente codé principalement en Java et disposant d’une base de données assurant la pérennité de son contenu. Les données de l’application sont mises à jour régulièrement lors des interactions avec l’utilisateur, notamment à la création de compte ou de produits. Il est donc nécessaire de lancer le programme sous la bonne configuration, afin de s’assurer que ces appels déterminants à la base de données puissent s’effectuer. 
Le fonctionnement de la base de données repose sur l’installation correcte du driver Connector/J de MySQL. Les fichiers .jar du driver se trouvent dans le fichier ./lib du projet. Il faut les ajouter au path de votre IDE. Idéalement, le programme est lancé sous Intellij, qui permet d’effectuer cette installation rapidement en quelques étapes :
- Allez dans le menu File puis Project Structure
-	Une page s’ouvre, allez dans l’onglet Librairies
- Cliquez sur +, puis sélectionner From Maven
-	Dans la barre de recherche qui s’ouvre, entrez la ligne suivante : 

mysql:mysql-connector-java:8.0.11

-	Appuyez sur OK
-	Avant de fermer la fenêtre, cliquez sur Apply
Une fois cette installation effectuée, vous pouvez lancer IBuySu.com. Dans le dossier ./IHM se trouve une classe Main, qui contient le main du programme. C’est elle qu’il faut exécuter. 
/!\  La base de données ne fonctionne pas sur les réseaux publiques tels qu’eduroam ou eduspot. Préférez un réseau wifi ou 4G/5G privé.
2.	Utiliser IBuySu
IBuySu.com propose plusieurs fonctionnalités telles que décrites dans la documentation. Ci-dessous les déroulements actuellement possibles (certaines fonctionnalités n’ont pas pu être implémentées et sont précisées dans la suite du mode d’emploi). 
a)	Au lancement de l’application
Vous arrivez sur le menu Utilisateur non connecté (c.f. illustration ci-dessous). 

![image](https://user-images.githubusercontent.com/72083970/145729614-7600f41b-7719-45e8-8ab1-dfc0c97c6e6f.png)

 
Tous les menus de IBuySu.com vous invitent à choisir une option sous la forme d’un entier désignant l’option désirée. Attention, tout autre format entraine une erreur. 
De plus, tout au long de votre expérience utilisateur, vous serez sans doute amené à remplir des formulaires. Les contraintes de format doivent impérativement être respectées : lorsqu’un un numéro ou un prix vous est demandé, soyez vigilant à entrer un entier.
En tant qu’Utilisateur non connecté, vous pouvez :
•	Rechercher : sélectionnez l’option Recherche. Cette option vous propose deux types de recherche : 
- Par catégorie
- Par mot clef
Le système vous proposera alors de choisir une catégorie puis éventuellement une sous-catégorie dans le premier cas, ou bien d’entrer directement un mot clef. 
Dans les deux cas, le résultat de la recherche vous est présenté sous forme de liste. Vous pourrez alors sélectionner le produit de votre choix. Cependant, pour effectuer un achat, il vous faudra être connecté en tant qu’Acheteur. 
•	Se connecter : sélectionner l’option Connexion. Cette option vous permet de vous connecter si vous avez déjà créé un compte précédemment. Votre email et votre mot de passe vous seront demandés. 

•	S’inscrire : en fonction de vos besoins sur IBuySu.com, vous pouvez choisir de vous inscrire en tant qu’Acheteur ou bien en tant que Vendeur. 
A noter : lors des entrées dans les formulaires, prenez garde aux formats. Les entrées vous demandant un numéro attendent un entier et déclencheront une erreur si ce format n’est pas respecté. De plus, il est préférable de choisir une adresse mail par compte si vous souhaitez en ouvrir plusieurs. 
En outre, une fois inscrit, vous êtes connecté automatiquement.
•	Quitter : arrête le programme. 

b)	En tant qu’Acheteur

Lorsque vous êtes connecté en tant qu’Acheteur, vous avez accès aux fonctionnalités suivantes :

![image](https://user-images.githubusercontent.com/72083970/145729621-37024bdc-6543-4171-a778-84a6059f318d.png)

 
Vous pouvez : 
•	Gérer mes achats (non implémentée) : cette option vous propose d’accéder à vos contrats en cours, afin de pouvoir valider la réception d’un article et de visualiser vos achats passés. 

•	Recherche : même principe que pour l’Utilisateur non connecté. Le résultat de la recherche vous permettra néanmoins de faire une proposition d’achat. Lorsque cette dernière est validée par le vendeur, un contrat sera créé et affiché dans Gérer mes achats. 


- Vente classique : vous n’avez rien à faire !
- Vente aux enchères (partiellement implémentée) : vous pourrez proposer un prix au Vendeur, qui sera enregistrée dans le système. Vous serez notifié dans Gérer mes achats si vous remportez l’enchère. 

•	Déconnexion : cette option vous permet de vous déconnecter, vous retournez alors au menu Utilisateur non connecté. Vos actions sont sauvegardées dans la base de données, elles seront donc récupérables à votre prochaine connexion, même si vous arrêtez le programme. 

•	Quitter : quitte le programme. 


c)	En tant que Vendeur
A la connexion en tant que Vendeur vous sont proposées les options suivantes :

 ![image](https://user-images.githubusercontent.com/72083970/145729636-6ca8f34b-d0a2-467d-b24c-03d39fc38154.png)

•	Gérer mes ventes (partiellement implémentée) : affiche vos ventes en cours, ainsi que les propositions d’achat. Vous pouvez les accepter ou les refuser. De plus, lorsqu’une vente a été conclue, c’est ici que vous pourrez valider vos contrats. 

•	Créer une vente : cette option vous propose de créer une vente. Remplissez le formulaire, n’hésitez pas à mettre en valeur votre produit ! Vous pouvez créer une Enchère ou une Vente simple. 

A noter : lorsque la photo vous est demandée, entrez une chaine de caractère. 

•	Recherche : option équivalente à celle de l’Utilisateur non connecté.

•	Déconnexion : cette option vous permet de vous déconnecter, vous retournez alors au menu Utilisateur non connecté. Vos actions sont sauvegardées dans la base de données, elles seront donc récupérables à votre prochaine connexion, même si vous arrêtez le programme. 

•	Quitter : quitte le programme. 
