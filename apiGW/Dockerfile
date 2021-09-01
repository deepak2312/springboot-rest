FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/apiGW-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} apiGW.jar
ENTRYPOINT ["java","-jar","/apiGW.jar"]