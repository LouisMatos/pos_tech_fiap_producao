FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN chmod +x .mvn

# clean up the file
RUN sed -i 's/\r$//' mvnw
# run with the SH path
RUN /bin/sh mvnw package -DskipTests dependency:resolve

FROM eclipse-temurin:21-jre

RUN addgroup --system app && adduser --system --ingroup app app

WORKDIR /app

COPY --from=build /app/target/jlapp-producao-0.0.1-SNAPSHOT.jar jlapp-producao-0.0.1-SNAPSHOT.jar

USER app

ENTRYPOINT ["java","-jar","jlapp-producao-0.0.1-SNAPSHOT.jar"]
