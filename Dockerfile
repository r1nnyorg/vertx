FROM openjdk
COPY copyDependencies /usr/local/src/
COPY server/Server.class /usr/local/src/server/Server.class
WORKDIR /usr/local/src
ENTRYPOINT ["java", "-cp", ".:*", "server/Server"]
