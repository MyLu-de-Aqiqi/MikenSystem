FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER pcx

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /mc-cain-system

WORKDIR /mc-cain-system

EXPOSE 8763

ADD ./target/mc-cain-system-first.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar mc-cain-system-first.jar
