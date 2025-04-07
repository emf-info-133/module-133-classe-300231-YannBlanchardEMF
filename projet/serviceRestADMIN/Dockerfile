# Étape 1 : Utiliser une image Maven pour construire l'application
FROM openjdk:17-jdk-alpine AS build

# Définir le répertoire de travail
WORKDIR /app

# Installer Maven
RUN apk update && apk add maven

# Copier le pom.xml pour installer les dépendances
COPY main/pom.xml /app/pom.xml

# Pré-charger les dépendances
RUN mvn dependency:go-offline

# Copier le code source
COPY main/src /app/src

# Build de l'application sans exécuter les tests
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image Java optimisée pour exécuter l'application
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré depuis l'étape précédente
COPY --from=build /app/target/main-0.0.1-SNAPSHOT.jar /app/servicerestadmin.jar

# Exposer le port sur lequel ton app écoute
EXPOSE 8083

# Lancer l’application
ENTRYPOINT ["java", "-jar", "/app/servicerestadmin.jar"]
