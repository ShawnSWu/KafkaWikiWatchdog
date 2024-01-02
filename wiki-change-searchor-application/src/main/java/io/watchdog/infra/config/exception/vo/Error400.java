package io.watchdog.infra.config.exception.vo;

import io.watchdog.infra.config.exception.ErrorException;
import io.watchdog.infra.config.exception.enums.error_code.ErrorCode400;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
public class Error400 extends RuntimeException implements ErrorException {
    public Error400(String message, Throwable cause) {
        super(message, cause);
    }

    public Error400(String message) {
        super(message);
    }

    public Error400(ErrorCode400 errorCode) {
        super(errorCode.getMessage());
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getOutputMsg() {
        return "Bad request";
    }
}
