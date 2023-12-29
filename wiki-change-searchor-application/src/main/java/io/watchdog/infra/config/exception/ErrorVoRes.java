package io.watchdog.infra.config.exception;

import io.watchdog.infra.config.exception.enums.error_code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorVoRes {
    private String message;

    public ErrorVoRes(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
}
