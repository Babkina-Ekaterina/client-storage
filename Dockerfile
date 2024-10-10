FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/client-storage-0.0.1-SNAPSHOT.jar client-storage.jar
ENTRYPOINT ["java","-jar","/client-storage.jar"]
