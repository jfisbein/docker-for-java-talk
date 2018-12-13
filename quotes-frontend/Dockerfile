FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/quotes-frontend*.jar quotes-frontend.jar
CMD java ${JAVA_OPTS} -jar quotes-frontend.jar