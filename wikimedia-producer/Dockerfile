FROM openjdk:11-jre-slim

WORKDIR /app

COPY wikimedia-producer/build/libs/WikiProducer.jar /app

EXPOSE 8083

CMD ["java", "-jar", "WikiProducer.jar"]
