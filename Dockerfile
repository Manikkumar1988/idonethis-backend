FROM adoptopenjdk/openjdk8:latest
WORKDIR /app
COPY . /app/
COPY /build/libs/idonethis-backend-1.0-SNAPSHOT.jar /app/idonethis-backend.jar
CMD ["java", "-jar", "/app/idonethis-backend.jar"]