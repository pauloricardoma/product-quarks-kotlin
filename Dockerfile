FROM openjdk:17

WORKDIR /app

COPY ./build/product-kotlin-1.0.0-SNAPSHOT-native-image-source-jar/lib /app/lib
COPY ./build/product-kotlin-1.0.0-SNAPSHOT-native-image-source-jar/*-runner.jar /app/product-kotlin-runner.jar

EXPOSE 8081

CMD ["java", "-jar", "product-kotlin-runner.jar"]