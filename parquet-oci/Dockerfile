# starts from gradle image
FROM gradle:6.4.0-jdk11 AS build

# copies java source code inside the container
# TODO: don't need?
COPY --chown=gradle:gradle . /parquet
WORKDIR /parquet

# compiles the code
RUN gradle build -DskipTests --no-daemon

# start again from JRE image
# TODO: check again
FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

# copy only jar file create before
COPY --from=build /parquet/helidon-sub-project/build/libs/*.jar /app/parquet-oci.jar

ENTRYPOINT ["java", "-jar","/app/parquet-oci.jar"]