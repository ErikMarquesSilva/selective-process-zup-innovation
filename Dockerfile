# Alpine Linux with OpenJDK JRE
FROM java:8
COPY build/libs/selective-process-zup-innovation-0.1.0.jar ./service.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /service.jar
