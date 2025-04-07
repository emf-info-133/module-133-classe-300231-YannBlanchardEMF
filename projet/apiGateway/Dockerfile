# Étape 1 : Build avec Maven
FROM openjdk:17-jdk-alpine AS build

# Dossier de travail
WORKDIR /app

# Installer Maven
RUN apk update && apk add maven

# Copier le POM
COPY src/pom.xml /app/pom.xml

# Télécharger les dépendances
RUN mvn dependency:go-offline

# Copier le code source
COPY src/src /app/src

# Build le JAR (skip tests pour rapidité)
RUN mvn clean package -DskipTests

# Étape 2 : Exécution du JAR
FROM openjdk:17-jdk-slim

# Dossier de travail
WORKDIR /app

# Copier le JAR depuis l'étape build
COPY --from=build /app/target/src-0.0.1-SNAPSHOT.jar /app/apigateway.jar

# Exposer le port du gateway
EXPOSE 8080

# Lancer l’app
ENTRYPOINT ["java", "-jar", "/app/apigateway.jar"]
