FROM openjdk:8-jre-alpine

COPY ./target/miniautorizador-0.0.1-SNAPSHOT.jar /miniautorizador.jar

COPY ./start.sh /start.sh

RUN chmod +x /start.sh

EXPOSE 8080 8080

CMD /start.sh

