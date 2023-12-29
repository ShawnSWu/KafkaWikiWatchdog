package io.watchdog.infra.config.exception.vo;

import io.watchdog.infra.config.exception.ErrorException;
import io.watchdog.infra.config.exception.enums.error_code.ErrorCode500;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class Error500 extends RuntimeException implements ErrorException {
    public Error500(Throwable e) {
        super(e);
    }

    public Error500(ErrorCode500 errorCode500) {
        super(errorCode500.getMessage());
    }

    public Error500(String message) {
        super(message);
    }

    public Error500(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @Override
    public String getOutputMsg() {
        return "Internal error";
    }
}

