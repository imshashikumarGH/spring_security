FROM eclipse-temurin:11
ARG JAR_FILE=*.jar
COPY ./target/${JAR_FILE} user-service.jar
CMD ["java", "-jar", "user-service.jar"]


# mvn clean package -DskipTests
# docker build imshashikumar01/user-service:0,0.1 .
