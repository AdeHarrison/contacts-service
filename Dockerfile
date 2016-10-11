FROM qnib/alpn-jre8
MAINTAINER Calm Computer Services Limited
WORKDIR /contacts-service
COPY target/contacts-service-0.1.0.jar /contacts-service
ENTRYPOINT ["java","-jar","/contacts-service/contacts-service-0.1.0.jar"]
