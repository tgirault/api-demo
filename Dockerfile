# Étape 1 : Utiliser une image de base avec OpenJDK
FROM openjdk:22-jdk-slim

# Étape 2 : Définir le répertoire de travail à l'intérieur du conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans l'image
COPY target/api-demo-0.1.0.jar /app/api-demo-0.1.0.jar

# Étape 4 : Exposer le port sur lequel l'application fonctionne
EXPOSE 8080

# Étape 5 : Définir la commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "api-demo-0.1.0.jar"]