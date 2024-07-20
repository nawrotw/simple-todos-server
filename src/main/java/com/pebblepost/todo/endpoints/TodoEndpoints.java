package com.pebblepost.todo.endpoints;

import com.pebblepost.todo.domain.TodoService;
import com.pebblepost.todo.endpoints.requests.CreateNewTodoRequest;
import com.pebblepost.todo.endpoints.requests.UpdateCheckedRequest;
import com.pebblepost.todo.endpoints.requests.UpdateTextRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/todos")
public class TodoEndpoints {

    private final TodoService todoService;

    public TodoEndpoints(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateNewTodoRequest request) {
        boolean checked = Optional.ofNullable(request.getChecked()).isPresent() ? request.getChecked() : false;
        todoService.createTodo(request.getText(), checked);
    }

    @GetMapping
    public List<TodoDto> getAll() {
        return TodoDto.fromEntities(todoService.getTodos());
    }

    @PutMapping("/{id}/checked")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateChecked(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateCheckedRequest request) {
        todoService.updateChecked(id, request.isChecked());
    }

    @PutMapping("/{id}/text")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateText(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateTextRequest request) {
        todoService.updateText(id, request.getText());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
    }
}
