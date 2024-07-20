package com.pebblepost.todo.endpoints.requests;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateTextRequest {
    @NotNull
    private String text;
}
