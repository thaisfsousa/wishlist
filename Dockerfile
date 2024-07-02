FROM openjdk:17-slim

COPY --from=build /app/target/wishlist-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]