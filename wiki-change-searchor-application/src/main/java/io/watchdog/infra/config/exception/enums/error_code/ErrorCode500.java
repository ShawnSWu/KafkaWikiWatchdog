package io.watchdog.infra.config.exception.enums.error_code;

import lombok.Getter;

@Getter
public enum ErrorCode500 implements ErrorCode {
    NO_DATA("%s id = %s not found"),
    NO_PROPERTY_DATA("%s %s = %s not found");

    private final String message;

    ErrorCode500(String message) {
        this.message = message;
    }

}
