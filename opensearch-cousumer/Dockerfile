FROM openjdk:11-jre-slim

WORKDIR /app

COPY opensearch-cousumer/build/libs/WikiConsumer.jar /app

EXPOSE 8082

CMD ["java", "-jar", "WikiConsumer.jar"]
