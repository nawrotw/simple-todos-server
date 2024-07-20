package com.pebblepost.todo.endpoints.requests;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateCheckedRequest {
    @NotNull
    private boolean checked;
}
