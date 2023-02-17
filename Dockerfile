FROM gradle:7.6-openjdk:15.0.2-alpine AS build
COPY .  /home/
WORKDIR /home/
RUN gradle build

FROM openjdk:15.0.2-slim
RUN mkdir /app
COPY --from=build /home/build/libs/Converter-CNY-JPY-0.0.1-SNAPSHOT.jar /app/Converter-CNY-JPY-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/Converter-CNY-JPY-0.0.1-SNAPSHOT.jar"]