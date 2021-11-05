FROM openjdk
COPY copyDependencies /usr/local/src/
COPY Server.class /usr/local/src/Server.class
WORKDIR /usr/local/src
ENTRYPOINT ["java", "-cp", ".:*", "Server"]
