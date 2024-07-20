package com.pebblepost.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseNotFoundException extends ResponseStatusException {

    public ResponseNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
