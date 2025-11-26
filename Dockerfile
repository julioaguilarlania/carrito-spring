FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE

WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]

# docker build --build-arg JAR_FILE=target/*.jar -t carrito-spring:version .
# docker run --name backend -p ####:8080 -d carrito-spring:version