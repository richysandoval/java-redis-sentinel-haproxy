FROM docker.nextiva.xyz/nextiva-corretto:11

ARG build_version
ENV BUILD_VERSION=${build_version}

USER root
RUN yum install -y net-tools procps-ng

ADD ./target/redis-demo-0.0.1-SNAPSHOT.jar /service/redis-demo.jar
WORKDIR /service

EXPOSE 8080

CMD java-entrypoint -jar redis-demo.jar
