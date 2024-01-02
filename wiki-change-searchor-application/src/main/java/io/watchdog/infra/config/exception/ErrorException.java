package io.watchdog.infra.config.exception;

public interface ErrorException {
    String getOutputMsg();

    int getHttpStatus();
}
