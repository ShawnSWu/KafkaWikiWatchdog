package io.watchdog.infra.config.exception;


import io.watchdog.infra.config.exception.vo.Error400;
import io.watchdog.infra.config.exception.vo.Error401;
import io.watchdog.infra.config.exception.vo.Error403;
import io.watchdog.infra.config.exception.vo.Error500;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ThrowableHandler {

    private static final String UNEXPECTED_ERROR = "Sorry, an error has occurred.";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorVoRes httpRequestMethodNotSupportedException(Throwable e) {
        log.debug("Http request method not supported message: {}", e.getMessage(), e);

        return new ErrorVoRes(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorVoRes httpMessageNotReadableHandler(Throwable e) {
        log.debug("Http message not readable message: {}", e.getMessage(), e);

        return new ErrorVoRes(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Error400.class})
    public ErrorVoRes error400(Error400 e) {
        String message = e.getMessage() == null ? e.getOutputMsg() : e.getMessage();
        log.error("Error400 message: {}", message, e);

        return new ErrorVoRes(message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({Error401.class})
    public ErrorVoRes error401(Error401 e) {
        String message = e.getMessage() == null ? e.getOutputMsg() : e.getMessage();
        log.error("Error401 message: {}", message, e);

        return new ErrorVoRes(message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({Error403.class})
    public ErrorVoRes error403(Error403 e) {
        String message = e.getMessage() == null ? e.getOutputMsg() : e.getMessage();
        log.error("Error403 message: {}", message, e);

        return new ErrorVoRes(message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Error500.class)
    public ErrorVoRes error500(Error500 e) {
        log.error("Error500 message: {}", e.getMessage(), e);

        return new ErrorVoRes(UNEXPECTED_ERROR);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorVoRes handleError(HttpServletRequest request, Throwable e) {
        String apiPath = getApiPath(request);
        log.error("Global exception, Api path: {}, message: {}", apiPath, e.getMessage(), e);

        return new ErrorVoRes(UNEXPECTED_ERROR);
    }

    private String getApiPath(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }
}