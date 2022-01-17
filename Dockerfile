#FROM openjdk:13-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} riskevaluator-backend.jar
#EXPOSE $PORT
#ENTRYPOINT ["java","-jar","/riskevaluator-backend.jar"]


FROM openjdk:13-jdk-alpine
ARG  JAR_FILE=target/*.jar
RUN mkdir -p /apps
COPY ${JAR_FILE} /apps/app.jar
ENTRYPOINT ["java","-jar","/apps/app.jar"]
