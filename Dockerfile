FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

LABEL maintainer="robsonxavierlima@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=/desafioEngDados/target/nasaKennedySpace-0.0.1-SNAPSHOT.jar
ARG DATASET=/dataset/

ADD ${JAR_FILE} nasa-kennedy-space.jar
ADD ${DATASET} DATASET


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/nasa-kennedy-space.jar"]


