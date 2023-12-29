package io.watchdog.infra.config.exception.vo;

import io.watchdog.infra.config.exception.ErrorException;
import io.watchdog.infra.config.exception.enums.error_code.ErrorCode403;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class Error403 extends RuntimeException implements ErrorException {
    public Error403(Throwable e) {
        super(e);
    }

    public Error403(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.FORBIDDEN.value();
    }

    public String getOutputMsg() {
        return ErrorCode403.FORBIDDEN.getMessage();
    }
}
