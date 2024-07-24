package com.pebblepost.todo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByCheckedAscTextAsc();

    Optional<Todo> findOneByText(String text);

    @Modifying
    @Query("update Todo u set u.checked = :checked where u.id = :id")
    void updateChecked(Long id, boolean checked);

    @Modifying
    @Query("update Todo u set u.text = :text where u.id = :id")
    void updateText(Long id, String text);

    @Modifying
    @Query("update Todo u set u.checked = false")
    void clearCompleted();
}
