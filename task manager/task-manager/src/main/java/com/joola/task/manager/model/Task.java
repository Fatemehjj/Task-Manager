package com.joola.task.manager.model;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotFound(action = NotFoundAction.EXCEPTION)
    private String task;
    @NotFound(action = NotFoundAction.EXCEPTION)
    private int year;
    @NotFound(action = NotFoundAction.EXCEPTION)
    @Column(name = "task_state")
    private String taskState;
    @NotFound(action = NotFoundAction.EXCEPTION)
    private String month;
    @NotFound(action = NotFoundAction.EXCEPTION)
    private int day;
}
