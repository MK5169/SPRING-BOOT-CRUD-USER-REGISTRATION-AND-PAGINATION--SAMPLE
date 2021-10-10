FROM openjdk:11

LABEL VERSION=1.0
LABEL AUTHOR=TRITERN

ADD target/AuthLogin-0.0.1-SNAPSHOT.jar AuthLogin-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","AuthLogin-0.0.1-SNAPSHOT.jar"]

