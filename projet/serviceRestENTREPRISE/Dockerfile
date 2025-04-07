# Étape 1 : Builder l'application avec Maven
FROM openjdk:17-jdk-alpine AS build

# Dossier de travail dans le conteneur
WORKDIR /app

# Installer Maven
RUN apk update && apk add maven

# Copier le pom.xml (et download les deps)
COPY code/pom.xml /app/pom.xml
RUN mvn dependency:go-offline

# Copier le code source
COPY code/src /app/src

# Build du projet (skip tests pour rapidité)
RUN mvn clean package -DskipTests

# Étape 2 : Image légère pour lancer le JAR
FROM openjdk:17-jdk-alpine

# Dossier de travail pour l'exécution
WORKDIR /app

# Copier le jar depuis le build
COPY --from=build /app/target/code-0.0.1-SNAPSHOT.jar /app/servicerestentreprise.jar

# Exposer le port 8082
EXPOSE 8082

# Lancer l’application
ENTRYPOINT ["java", "-jar", "/app/servicerestentreprise.jar"]
