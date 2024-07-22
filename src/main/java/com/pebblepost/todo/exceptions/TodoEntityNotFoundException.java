package com.pebblepost.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TodoEntityNotFoundException extends ResponseStatusException {

    public TodoEntityNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Todo { id: '" + id + "' } not found");
    }
}
