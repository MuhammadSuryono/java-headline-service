FROM maven:3.8.2-jdk-11
WORKDIR /healdine-app
COPY . .
COPY .env.example .env
RUN mvn clean install
CMD mvn spring-boot:run
