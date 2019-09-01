FROM hub.c.163.com/library/java:8-jre

VOLUME /tmp
    #pt-service-bms-1.0.0.jar
ADD target/pt-gateway-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c "touch /app.jar"
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=pro","-c"]