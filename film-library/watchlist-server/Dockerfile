FROM openjdk:17
COPY watchlist-server/target/watchlist-server-0.0.1.jar /app/app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]