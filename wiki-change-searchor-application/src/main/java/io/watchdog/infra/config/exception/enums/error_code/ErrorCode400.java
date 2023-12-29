package io.watchdog.infra.config.exception.enums.error_code;

import lombok.Getter;

@Getter
public enum ErrorCode400 implements ErrorCode {
    NOT_FOUND_RESOURCE("Sorry, the resource with %s Id = %s cannot be found"),
    RESOURCE_DUPLICATE("Sorry, there appears to be a duplicate resource with %s %s = %s");

    private final String message;

    ErrorCode400(String message) {
        this.message = message;
    }
}
