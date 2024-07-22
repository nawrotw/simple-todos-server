package com.pebblepost.todo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByCheckedAscTextAsc();
    Optional<Todo> findOneByText(String text);
}
