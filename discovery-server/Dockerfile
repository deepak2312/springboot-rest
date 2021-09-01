FROM openjdk:8-jdk-alpine
EXPOSE 9999
ARG JAR_FILE=target/discovery-server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} discovery-server.jar
ENTRYPOINT ["java","-jar","/discovery-server.jar"]