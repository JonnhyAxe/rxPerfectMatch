FROM openjdk:8-jdk-alpine
EXPOSE 8081 8443
RUN mkdir -p /opt
COPY RxPerfectMatch.jar /opt
RUN sh -c 'touch RxPerfectMatch.jar'  
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/music","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/RxPerfectMatch.jar"]  
