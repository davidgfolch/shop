package org.dgf.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseOk<T> extends ResponseEntity<T> {

    public ResponseOk(T object) {
        super(object, HttpStatus.OK);
    }

}
