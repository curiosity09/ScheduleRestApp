FROM openjdk:11
ADD target/ScheduleApp-0.0.1-SNAPSHOT.jar ScheduleApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ScheduleApp-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080