FROM maven:3-jdk-11

COPY ./target/prototype-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch prototype-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","prototype-0.0.1-SNAPSHOT.jar"]