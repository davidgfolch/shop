package org.dgf.shop.rest;

import org.dgf.shop.rest.model.OrderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERIC_APPLICATION_ERROR = "Generic application error: ";

    @ExceptionHandler(value = { OrderException.class })
    protected ResponseEntity<Object> handleConflict(OrderException ex, WebRequest request) {
        String bodyOfResponse = GENERIC_APPLICATION_ERROR + ex.getLocalizedMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}