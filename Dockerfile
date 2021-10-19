FROM busybox
COPY jre /usr/local/
WORKDIR /usr/local/jre
COPY --from=openjdk:slim /lib/x86_64-linux-gnu /lib/x86_64-linux-gnu/
COPY --from=node:slim /usr/lib/x86_64-linux-gnu /usr/lib/x86_64-linux-gnu/
COPY --from=openjdk:slim /lib64 /lib64/
ENTRYPOINT ["bin/java", "-m", "Web/web.Web"]
