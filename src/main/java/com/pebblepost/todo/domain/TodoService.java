package com.pebblepost.todo.domain;

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
        return todoRepository.findAll();
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.findById(id).ifPresent(todoRepository::delete);
    }

    @Transactional
    public void updateChecked(Long id, boolean checked) {
        // throw for 404??
//        throw new UnsupportedOperationException("Not supported yet.");
        todoRepository.findById(id).ifPresent(todo -> todo.setChecked(checked));
    }

    @Transactional
    public void updateText(Long id, String text) {
        todoRepository.findById(id).ifPresent(todo -> todo.setText(text));
    }
}
