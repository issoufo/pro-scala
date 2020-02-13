### Contenu des packages :
```
    -- commandAndDirection : contient les énumurations de directions et instructions. Le package de test portent le même nom.  
    -- model : contient le modèle(case classe) des objets
    -- upem.scala : contient les fichiers permettant de construire la pélouses et les tondeuses à partir de données d'un fichier. 
        Le package de test portent le même nom. 
```

### Implémentation :
```
Avant tout il est important de noter :
 1. Que les lignes vides dans le fichier ne sont pas prises en compte. 
 2. Il faut qu'il y aie autant de tondeuses que d'instructions
    sinon le format du fichier est considéré mauvais. Par exemple le format du fichier ci-dessous est mauvais car 
    la 2ème tondeuse n'a aucune instruction donc ne fait rien et j'ai supposé qu'il est inutile d'avoir une tondeuse qui fait rien sur 
    une pélouse.

    5 5
  
    1 2 N
    
    GAGAGAGAA
    3 3 E
    
```
   ##### HandleFile :
```
Contient 2 fonctions publiques et plusieurs privés permettant de vérifier l'intégrité de données. Ci-dessous les 2 fonctions publiques :
    -- La fonction buildLawn : permet de lire et récupérer le contenu du fichier et constuit la pelouse à partir de données de la première
        ligne du fichier. Cette ligne doit être composée de seulement 2 élements qui doivent être des nombres entiers positifs sinon aucune 
        pelouse n'est construite et donc aucune tondeuse aussi.  
    -- buildMowerWithInstructions : permet de récupérer les lignes paires(impaires en supprimant la première ligne) et impaires(paires en supprimant la première ligne) 
        du fichier puis construire des tondeuses et associe à chaque tondeuse ses instructions via une LinkedHashMap. 
        Les coordonnées d'une tondeuses ne doivent pas être négatives et doivent être supérieures ou égales à celle de
        la pélouse et chaque instruction d'une tondeuse doit être valide c'est-à-dire qu'elle doit être un élement de l'énumération Command 
        ou Direction. Une tondeuse et ses instructions sont ignorées si une de ses coordonnées est négative ou supérieure à celle de la pélouse ou 
        si une de ses instructions est invalide ou s'il manque une information pour la construire(par exemple une tondeuse composée seulement
        de 2 entiers positifs(x et y) donc sans direction). 
        Par exemple dans le fichier ci-dessous la 2ème tondeuse n'est pas prise en compte car son ordonnée y est supérieure à celle de la pélouse
        
        5 5
        
        1 2 N
        
        GAGAGAGAA
        3 6 E
        
        AADAADADDA   
```

   ##### HandleMower :
```
Contient une fonction publique et 2 privés(une permettant de calculer la nouvelle direction d'une tondeuse donnée et 
une permettant de calculer les nouvelles coordonnées d'une tondeuse donnée.). Ci-dessous la fonction publique :
    -- La fonction move : permet de modifier la direction ou la position d'une tondeuse.  
```

   ##### Main :
```
Est le point d'entrée de l'application. Il utilise les fonctions publiques de HandleFile et HandleMower pour obtenir le résultat et l'afficher.
```

### Compiler/Exécuter/Tester :
Récupérer d'abord le projet en faisant "git clone https://github.com/issoufo/pro-scala.git" ou le télécharger au format ZIP

   ##### Première méthode : En ligne de commande
```
    1. Il faut tout d'abord installer SBT si ce n'est déjà fait.
    2. Se rendre dans le répertoire du projet
    3. Exécuter la commande "sbt" ce qui ouvrira la console sbt
    4. Taper ~run ou run selon que l'on veut une réexécution automatique ou non du sbt à chaque modification dans un fichier donné.
```

   ##### Deuxième méthode : Avec l'IDE IntelliJ
```
    1. Il faut tout d'abord importer le projet dans IntelliJ en ouvrant le build.sbt depuis la barre de menus File->Open->cheminVersRepertoireDuProjet/projetScala/build.sbt 
    2. Il suffit de se placer dans le fichier Main.scala et cliquer sur le bouton exécuter(Run) situé en haut à droite juste en dessous de la barre de menus
        ou faire clique droit dans le fichier Main.scala et choisir l'option Run 'Main'
```
