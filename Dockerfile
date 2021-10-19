FROM openjdk
COPY Server.javac /usr/local/src/
WORKDIR /usr/local/src
ENTRYPOINT ["java", "Server.javac"]
