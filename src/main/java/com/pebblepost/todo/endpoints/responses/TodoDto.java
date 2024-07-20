package com.pebblepost.todo.endpoints.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pebblepost.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class TodoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private String text;

    @NotNull
    private Boolean checked;

    public static TodoDto fromEntity(Todo todo) {
        return new TodoDto(todo.getId(), todo.getText(), todo.getChecked());
    }

    public static List<TodoDto> fromEntities(List<Todo> todos) {
        return todos.stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }
}
