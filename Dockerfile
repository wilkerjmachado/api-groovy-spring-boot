FROM store/oracle/jdk:11

WORKDIR /usr/src/app

COPY ./build/libs/api-groovy-spring-boot-0.1.jar /usr/src/app/

EXPOSE 8080

CMD java -jar api-groovy-spring-boot-0.1.jar