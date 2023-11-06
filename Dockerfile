FROM openjdk:17-jdk-slim
MAINTAINER leofreitas.engineer@gmail.com
VOLUME /tmp
COPY target/store-api-0.0.1-SNAPSHOT.jar ./app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#docker run -p 8080:8080 -d leofreiitas/seller-api:0.0.1-SNAPSHOT