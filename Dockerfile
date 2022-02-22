FROM openjdk:slim
RUN ["apt", "update"]
RUN ["apt", "install", "-y", "--no-install-recommends", "libnetty-tcnative-jni"]

FROM scratch
COPY copyDependencies Server.class /usr/local/src/
WORKDIR /usr/local/src
ENV JAVA_HOME /usr/local/openjdk
COPY --from=0 /bin /bin/
COPY --from=0 /usr/local/openjdk* /usr/local/openjdk/
COPY --from=0 /lib/x86_64-linux-gnu /lib/x86_64-linux-gnu/
COPY --from=0 /usr/lib/x86_64-linux-gnu /usr/lib/x86_64-linux-gnu/
COPY --from=0 /lib64 /lib64/
ENTRYPOINT ["/usr/local/openjdk/bin/java", "-cp", ".:*", "Server"]
