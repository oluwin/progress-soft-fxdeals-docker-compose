FROM maven:3.8.3-jdk-11
WORKDIR /fxdeals
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run