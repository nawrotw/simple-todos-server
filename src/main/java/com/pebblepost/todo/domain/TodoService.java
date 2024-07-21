package com.pebblepost.todo.domain;

import com.pebblepost.todo.exceptions.ResponseNotFoundException;
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
        todoRepository.save(new Todo(text, checked));
    }

    public List<Todo> getTodos() {
        return todoRepository.findAllByOrderByCheckedAscTextAsc();
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional
    public void updateChecked(Long id, boolean checked) {
        getTodoById(id).setChecked(checked);
    }

    @Transactional
    public void updateText(Long id, String text) {
        getTodoById(id).setText(text);
    }

    @Transactional
    public void clearCompleted() {
        todoRepository.findAll().forEach(todo -> todo.setChecked(false));
    }

    private Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseNotFoundException("Todo { id: '" + id + "' } not found"));
    }
}
