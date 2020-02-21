package org.dgf.shop.rest.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseError extends ResponseEntity<Object> {

    public ResponseError(String error) {
        super(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
