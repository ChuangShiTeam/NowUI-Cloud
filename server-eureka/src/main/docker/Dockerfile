FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD server-eureka-1.0.0.jar eureka.jar
RUN sh -c 'touch /eureka.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka.jar" ]
