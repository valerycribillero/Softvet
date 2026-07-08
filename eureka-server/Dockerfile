# ============================================================
# Dockerfile genérico para cualquiera de los microservicios
# Spring Boot + Maven de este proyecto.
#
# CÓMO USARLO:
#   Copia este archivo dentro de CADA carpeta de servicio
#   (api-gateway/, eureka-server/, autenticacion/, pagos/, etc.)
#   renombrado exactamente como "Dockerfile" (sin extensión).
#   Es el MISMO archivo para los 11 servicios, no hay que
#   modificar nada dentro de él.
# ============================================================

# Etapa 1: compila el proyecto con Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Etapa 2: imagen final, liviana, solo con el JRE y el .jar compilado
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]