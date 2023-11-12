# syntax=docker/dockerfile:1

FROM openjdk:oraclelinux8

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline
COPY src ./src
EXPOSE 5005
CMD ["./mvnw", "spring-boot:run","-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"]