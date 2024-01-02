FROM openjdk:11-jre-slim

WORKDIR /app

COPY wiki-change-searchor-application/build/libs/WikiWatchDog.jar /app

EXPOSE 8081

CMD ["java", "-jar", "WikiWatchDog.jar"]
