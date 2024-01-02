package io.watchdog.infra.config.exception.enums.error_code;

import lombok.Getter;

@Getter
public enum ErrorCode403 implements ErrorCode {
    FORBIDDEN("Insufficient privileges. Please contact the administrator if there are any issues");

    private final String message;

    ErrorCode403(String message) {
        this.message = message;
    }
}
