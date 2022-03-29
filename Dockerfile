FROM openjdk:17-jdk-alpine
MAINTAINER qixiaoling
COPY target/NightFox-0.0.1-SNAPSHOT.jar NightFox-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","NightFox-0.0.1-SNAPSHOT.jar"]