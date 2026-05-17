# --- Build Stage ---
# Utiliza una imagen de Maven con Java 21 para construir el proyecto
FROM maven:3.9-eclipse-temurin-21 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el wrapper de Maven para poder usar ./mvnw
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descarga las dependencias de Maven. Se hace en un paso separado para aprovechar el cache de Docker
RUN ./mvnw dependency:go-offline

# Copia el resto del código fuente de la aplicación
COPY src ./src

# Empaqueta la aplicación en un JAR, saltando los tests
RUN ./mvnw package -DskipTests


# --- Runtime Stage ---
# Utiliza una imagen ligera de Java 21 para ejecutar la aplicación
FROM eclipse-temurin:21-jre-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR construido desde la etapa anterior
COPY --from=builder /app/target/atlantis-fest-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
