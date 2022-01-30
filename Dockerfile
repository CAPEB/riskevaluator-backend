#FROM openjdk:13-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} riskevaluator-backend.jar
#EXPOSE $PORT
#ENTRYPOINT ["java","-jar","/riskevaluator-backend.jar"]




FROM openjdk:13-jdk-alpine

ARG A_HOST
ARG A_PORT
ARG A_NAME
ARG A_USER
ARG A_PASSWORD

ENV DB_HOST     = ${A_HOST}
ENV DB_PORT     = ${A_PORT}
ENV DB_NAME     = ${A_NAME}
ENV DB_USER     = ${A_USER}
ENV DB_PASSWORD = ${A_PASSWORD}


ARG  JAR_FILE=target/*.jar
RUN mkdir -p /apps
COPY ${JAR_FILE} /apps/app.jar
ENTRYPOINT ["java","-jar","/apps/app.jar"]
