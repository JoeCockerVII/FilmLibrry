FROM openjdk:17
COPY watch-notify-client/target/watch-notify-client-0.0.1.jar /app/app.jar
EXPOSE 8150
ENTRYPOINT ["java", "-jar", "/app/app.jar"]