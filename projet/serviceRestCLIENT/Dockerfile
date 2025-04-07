# Étape 1 : Utiliser Maven pour builder l'application
FROM openjdk:17-jdk-alpine AS build

# Définir le répertoire de travail
WORKDIR /app

# Installer Maven
RUN apk update && apk add maven

# Copier uniquement le pom.xml pour les dépendances
COPY servicerestclient/pom.xml /app/pom.xml

# Télécharger les dépendances à l'avance
RUN mvn dependency:go-offline

# Copier tout le code source
COPY servicerestclient/src /app/src

# Build du projet (sans tests)
RUN mvn clean package -DskipTests

# Étape 2 : Image légère pour exécuter le .jar
FROM openjdk:17-jdk-slim

# Dossier de travail
WORKDIR /app

# Copier le jar depuis l'étape précédente
COPY --from=build /app/target/servicerestclient-0.0.1-SNAPSHOT.jar /app/servicerestclient.jar

# Exposer le port du service
EXPOSE 8081

# Lancer l'app
ENTRYPOINT ["java", "-jar", "/app/servicerestclient.jar"]
