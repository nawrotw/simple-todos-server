package com.pebblepost.todo.domain;

import lombok.*;

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
    @Setter(AccessLevel.NONE)
    private int version; // optimistic locking

    @NotNull
    private String text;

    @NotNull
    private Boolean checked;

    public Todo(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }
}
