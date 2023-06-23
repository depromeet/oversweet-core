FROM azul/zulu-openjdk:17
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=oversweet-api/build/libs/oversweet-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENV	PROFILE prod
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]

ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone