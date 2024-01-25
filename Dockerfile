FROM maven:3.8.5-openjdk-11-slim

WORKDIR /adrbook-app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run