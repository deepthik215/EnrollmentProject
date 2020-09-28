FROM alpine-java:base
MAINTAINER deepthik
COPY target/enrollment.jar /opt/enrollment/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/enrollment/lib/enrollment.jar"]
VOLUME /var/lib/app/enrollment
EXPOSE 8888