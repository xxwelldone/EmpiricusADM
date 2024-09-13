FROM amazoncorretto:17-alpine-jdk
RUN mkdir /app
WORKDIR /app
COPY EmpiricusADM-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
