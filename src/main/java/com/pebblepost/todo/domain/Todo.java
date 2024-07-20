package com.pebblepost.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version; // optimistic updates - write a test

    @NotNull
    private String text;

    @NotNull
    private Boolean checked;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }
}
