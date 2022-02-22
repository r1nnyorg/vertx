FROM scratch
COPY copyDependencies Server.class /usr/local/src/
WORKDIR /usr/local/src
COPY --from=openjdk:slim /bin/bash /bin/
COPY --from=openjdk:slim /usr/java/openjdk* /
COPY --from=openjdk:slim /lib64 /lib64/
ENTRYPOINT ["java", "-cp", ".:*", "Server"]
