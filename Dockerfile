FROM scratch
COPY copyDependencies Server.class /usr/local/src/
WORKDIR /usr/local/src
COPY --from=openjdk:slim /bin/bash /bin/
COPY --from=openjdk:slim /usr/local/openjdk* /
COPY --from=node:slim /lib/x86_64-linux-gnu /lib/x86_64-linux-gnu/
COPY --from=node:slim /usr/lib/x86_64-linux-gnu /usr/lib/x86_64-linux-gnu/
COPY --from=node:slim /lib64 /lib64/
ENTRYPOINT ["java", "-cp", ".:*", "Server"]
