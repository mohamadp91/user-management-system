FROM maven:3.6.3-jdk-11-openj9 as app
COPY . ./app
WORKDIR /app
RUN mvn package -DskipTests
ENTRYPOINT [ "java","-jar","/app/target/user-management-system-0.0.1-SNAPSHOT.jar" ]