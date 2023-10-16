package com.joola.task.manager.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
@Data
public class UserDto {
    String task;
    int year;
    String month;
    int day;
    String task_state;
}
