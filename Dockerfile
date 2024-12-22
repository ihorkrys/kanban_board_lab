FROM openjdk:22-jdk-slim
VOLUME /tmp
COPY build/libs/KanBan-*-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]
EXPOSE 8080