FROM eclipse-temurin:17
RUN mkdir -p /opt/dir
COPY dir/ /opt/dir/
WORKDIR /opt/dir
RUN /opt/dir/gradlew -S build
CMD ["/opt/dir/gradlew", "bootRun"]