package com.joola.task.manager.dao;

import com.joola.task.manager.dto.UserDto;
import com.joola.task.manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByMonth(String month);
    @Query(value = "SELECT * FROM task q WHERE q.task LIKE (%:taskName%)",nativeQuery = true)
    Optional<Task> findByTaskName(String taskName);
}
