package com.pebblepost.todo.domain;

import com.pebblepost.todo.exceptions.DuplicatedTodoException;
import com.pebblepost.todo.exceptions.TodoEntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void createTodo(String text, boolean checked) {
        todoRepository.findOneByText(text).ifPresent(todo -> {
            throw new DuplicatedTodoException(text);
        });
        todoRepository.save(new Todo(text, checked));
    }

    public List<Todo> getTodos() {
        return todoRepository.findAllByOrderByCheckedAscTextAsc();
    }

    @Transactional
    public void deleteTodo(Long id) {
        try {
            todoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TodoEntityNotFoundException(id);
        }
    }

    @Transactional
    public void updateChecked(Long id, boolean checked) {
        todoRepository.updateChecked(id, checked);
    }

    @Transactional
    public void updateText(Long id, String text) {
        todoRepository.updateText(id, text);
    }

    @Transactional
    public void clearCompleted() {
        todoRepository.clearCompleted();
    }
}
