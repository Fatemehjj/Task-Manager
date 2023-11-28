package com.joola.task.manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import javax.crypto.Mac;

@Data
public class UserDto {
    @NotEmpty(message = "task cant be empty")
    @NotBlank(message = "task cant be blank")
    @Size(min = 3, max = 35)
    String task;
    @NotNull
    int year;
    @NotBlank(message = "task cant be blank")
    @NotEmpty(message = "task cant be empty")
    @Size(min = 3, max = 9)
    String month;
    @NotNull
    int day;
    String task_state;
}
