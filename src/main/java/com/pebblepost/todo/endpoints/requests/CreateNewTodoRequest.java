package com.pebblepost.todo.endpoints.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateNewTodoRequest {
    @NotBlank
    private String text;

    // optional
    private Boolean checked;
}
