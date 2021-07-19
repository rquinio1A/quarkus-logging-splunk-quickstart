# Quarkus Logging Splunk Quickstart

This project illustrates how you to send logs to Splunk using Quarkus, via [quarkus-logging-splunk](https://github.com/quarkiverse/quarkus-logging-splunk) extension.

## Building

```shell
mvn clean install
```

To build a native executable: 

```shell
mvn install -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```