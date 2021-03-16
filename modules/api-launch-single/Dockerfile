FROM registry.cn-shanghai.aliyuncs.com/c7n/javabase:0.9.0

RUN mkdir /egova-apps

COPY target/egova.jar /egova-apps
COPY src/main/resources/application-docker.properties /egova-apps/application.properties
COPY src/main/resources/keystore.p12 /egova-apps/keystore.p12
COPY src/main/resources/server.keystore /egova-apps/server.keystore

WORKDIR /egova-apps
RUN mkdir /egova-apps/tmp
RUN mkdir /egova-apps/files

EXPOSE 8787

ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "-Dspring.config.location=application.properties", "/egova-apps/egova.jar"]
