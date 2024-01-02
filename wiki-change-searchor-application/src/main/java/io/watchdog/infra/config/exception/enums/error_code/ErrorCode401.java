package io.watchdog.infra.config.exception.enums.error_code;

import lombok.Getter;

@Getter
public enum ErrorCode401 implements ErrorCode {
    UNAUTHORIZED("Unauthorized");

    private final String message;

    ErrorCode401(String message) {
        this.message = message;
    }
}
