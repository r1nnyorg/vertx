FROM openjdk
COPY Server.class copyDependencies /usr/local/src/
WORKDIR /usr/local/src
ENTRYPOINT ["java", "-cp", "copyDependencies", "Server.class"]
