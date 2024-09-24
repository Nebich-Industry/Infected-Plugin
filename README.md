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
Documentation de l'image Docker : https://github.com/itzg/docker-minecraft-server?tab=readme-ov-file
Documentation de Paper : https://docs.papermc.io/

## Prérequis pour lancer le projet :
- Installer un JDK 21. Par exemple sur Ubuntu : 
```bash
sudo apt install openjdk-21-jdk -y
```

Configurer correctement le JAVA_HOME
```bash
export JAVA_HOME=/usr/lib/jvm/java-1.21.0-openjdk-amd64
```

On peut lister les JDK disponibles avec la commande suivante :
```bash
update-java-alternatives --list
```
On peut changer de JDK avec la commande suivante :
```bash
sudo update-java-alternatives --set /usr/lib/jvm/java-1.21.0-openjdk-amd64
```

Il faut aussi que le serveur est un monde qui soit dans le dossier des mondes et que le nom du monde commence par world_infected_xxx.