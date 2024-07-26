# Mode d'emploi
En phase de développement il est possible de lancer un serveur minecraft avec la commande suivante :
```bash
docker compose up
```

Et l'éteindre :
```bash
docker compose down
```

Pour que le plugin soit installé sur le serveur de développement il faut le builder avec la commande :
```bash
mvn clean package
```
Ensuite il faut placer le plugin dans le dossier plugins qui se trouve à l'intérieur du dossier server-data à la racine du projet.
