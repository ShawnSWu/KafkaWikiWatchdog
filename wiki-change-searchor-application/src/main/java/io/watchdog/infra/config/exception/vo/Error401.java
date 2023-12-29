package io.watchdog.infra.config.exception.vo;

import io.watchdog.infra.config.exception.ErrorException;
import io.watchdog.infra.config.exception.enums.error_code.ErrorCode401;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
public class Error401 extends RuntimeException implements ErrorException {
    public Error401(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    public String getOutputMsg() {
        return ErrorCode401.UNAUTHORIZED.getMessage();
    }
}
