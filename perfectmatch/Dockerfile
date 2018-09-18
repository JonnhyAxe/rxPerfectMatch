FROM openjdk:8-jdk-alpine
EXPOSE 8081 8443
RUN mkdir -p /opt
COPY target/PerfectmatchRx-0.0.1-SNAPSHOT.jar /opt
RUN sh -c 'touch PerfectmatchRx-0.0.1-SNAPSHOT.jar'  
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/music","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/PerfectmatchRx-0.0.1-SNAPSHOT.jar"]  
