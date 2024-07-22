package com.pebblepost.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicatedTodoException extends ResponseStatusException {

    public DuplicatedTodoException(String text) {
        super(HttpStatus.CONFLICT, "'" + text + "' already exists");
    }
}
