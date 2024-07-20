package com.pebblepost.todo.endpoints.requests;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateNewTodoRequest {
    @NotNull
    private String text;

    // optional parameter
    private Boolean checked;
}
