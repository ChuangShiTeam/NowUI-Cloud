FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD server-zuul-1.0.0.jar zuul.jar
RUN sh -c 'touch /zuul.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /zuul.jar" ]