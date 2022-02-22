FROM scratch
COPY copyDependencies Server.class /usr/local/src/
WORKDIR /usr/local/src
ENV JAVA_HOME /usr/local/openjdk
COPY --from=openjdk:slim /bin /bin/
COPY --from=openjdk:slim /usr/local/openjdk* /usr/local/openjdk/
COPY --from=openjdk:slim /lib/x86_64-linux-gnu /lib/x86_64-linux-gnu/
COPY --from=openjdk:slim /usr/lib/x86_64-linux-gnu /usr/lib/x86_64-linux-gnu/
COPY --from=openjdk:slim /lib64 /lib64/
ENTRYPOINT ["java", "-cp", ".:*", "Server"]
