FROM openjdk
COPY Server.class /usr/local/src/
WORKDIR /usr/local/src
ENTRYPOINT ["java", "Server.class"]
