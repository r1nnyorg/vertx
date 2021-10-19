FROM openjdk
COPY copyDependencies /usr/local/src/
COPY server /usr/local/src/server/
WORKDIR /usr/local/src
ENTRYPOINT ["java", "-cp", ".:*", "Server"]
