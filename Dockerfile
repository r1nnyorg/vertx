FROM scratch
COPY copyDependencies Server.class DigiCertGlobalRootCA.crt.pem ca.crt client.root.crt client.root.key /usr/local/src/
WORKDIR /usr/local/src
ENV JAVA_HOME=/usr/local/openjdk password=''
COPY --from=openjdk:slim /usr/local/openjdk* /usr/local/openjdk/
COPY --from=openjdk:slim /lib/x86_64-linux-gnu /lib/x86_64-linux-gnu/
COPY --from=openjdk:slim /usr/lib/x86_64-linux-gnu /usr/lib/x86_64-linux-gnu/
COPY --from=openjdk:slim /lib64 /lib64/
ENTRYPOINT ["/usr/local/openjdk/bin/java", "-cp", ".:*", "Server"]
