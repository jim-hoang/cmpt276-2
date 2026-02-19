FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

RUN mvn -f ratecsteachingstaff/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-noble
WORKDIR /app
COPY --from=build /app/ratecsteachingstaff/target/ratecsteachingstaff-0.0.1-SNAPSHOT.jar ratecsteachingstaff.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ratecsteachingstaff.jar"]