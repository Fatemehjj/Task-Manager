package com.joola.task.manager.controller;

import com.joola.task.manager.dto.UserDto;
import com.joola.task.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    TaskService service;
@PostMapping("add")
    public ResponseEntity<String> saveTask(@RequestBody @NonNull UserDto input){
        return service.saveTask(input.getTask(), input.getYear(), input.getMonth(), input.getDay());
    }
 @GetMapping("month/{month}")
    public ResponseEntity<List<UserDto>> getTaskByMonth(@PathVariable @NonNull String month){
        return service.getTaskByMonth(month);
    }
@GetMapping("date/{task}")
    public ResponseEntity<String> getDateForTask(@PathVariable @NonNull String task){
        return service.getDateForTask(task);
    }
@GetMapping("days to/{task}")
    public ResponseEntity<String> daysLeftForATask(@PathVariable @NonNull String task){
    return service.daysLeftForATask(task);
    }
}
