FROM registry.cn-shanghai.aliyuncs.com/c7n/javabase:0.9.0

RUN mkdir /egova-apps

COPY target/egova.jar /egova-apps
COPY src/main/resources/application-docker.properties /egova-apps/application.properties

WORKDIR /egova-apps
RUN mkdir /egova-apps/tmp
RUN mkdir /egova-apps/files

EXPOSE 8788

ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "-Dspring.config.location=application.properties", "/egova-apps/egova.jar"]
