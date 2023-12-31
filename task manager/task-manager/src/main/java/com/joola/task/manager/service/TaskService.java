package com.joola.task.manager.service;

import com.joola.task.manager.dao.TaskRepository;
import com.joola.task.manager.dto.UserDto;
import com.joola.task.manager.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    public ResponseEntity<String> saveTask(String task, int year, String month, int day) {
        try {
            if ( year >= 1 && day >= 1 && day <= 31) {
                Task inputTask = new Task();
                inputTask.setTask(task);
                inputTask.setYear(year);
                inputTask.setMonth(month);
                inputTask.setDay(day);
                inputTask.setTaskState("incomplete");

                repo.save(inputTask);

                return new ResponseEntity<>("task saved !", HttpStatus.CREATED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("couldn't save task !", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<UserDto>> getTaskByMonth(String month) {
        try {
            Optional<List<Task>> findTask = repo.findByMonth(month);
            if (!findTask.get().isEmpty()) {
                List<UserDto> outputs = new ArrayList<>();

                for (Task tasks : findTask.get()) {
                    UserDto user = new UserDto();
                    user.setTask(tasks.getTask());
                    user.setYear(tasks.getYear());
                    user.setMonth(tasks.getMonth());
                    user.setDay(tasks.getDay());
                    if (tasks.getTaskState() == null) {
                        user.setTask_state("incomplete");
                    } else {
                        user.setTask_state(tasks.getTaskState());
                    }
                    outputs.add(user);
                }
                return new ResponseEntity<>(outputs, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getDateForTask(String task) {
        try {
            Optional<Task> taskName = repo.findByTaskName(task);
            if (!taskName.get().getTask().isBlank()) {
                Optional<Integer> year = Optional.of(taskName.get().getYear());
                Optional<String> month = Optional.of(taskName.get().getMonth());
                Optional<Integer> day = Optional.of(taskName.get().getDay());
                Optional<String> theTask = Optional.of(taskName.get().getTask());

                String respond = "Date For '" + theTask.get() + "' Is " + day.get() + "-" + month.get() + "-" + year.get() + " :]";

                return new ResponseEntity<>(respond, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("TASK NOT FOUND", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> daysLeftForATask(String task) {
        Optional<Task> entireTask = repo.findByTaskName(task);
        Calendar calendar = Calendar.getInstance();
        ServiceHelper helper = new ServiceHelper();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int day = entireTask.get().getDay();
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(month);
        String taskMonth = entireTask.get().getMonth();
        int positionOfMonth = helper.MonthsCalculation(taskMonth);
        System.out.println(positionOfMonth);

        if (entireTask.get().getTaskState().equals("incomplete")) {
            if (positionOfMonth - month== 0) {
                int remainingDays = day - dayOfMonth;
                return new ResponseEntity<>(remainingDays + " Days Left To '" + entireTask.get().getTask() + "' :>", HttpStatus.OK);
            }
            else if (positionOfMonth -month <0) {
                return new ResponseEntity<>("there is no more days left :(", HttpStatus.OK);
            }
            else {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    int remainingDays = 31 - dayOfMonth + day;
                    int daysBetweenMonths = 0;
                    for (int i = month + 1; i < positionOfMonth; i++) {
                        if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                            daysBetweenMonths += 31;
                        } else {
                            daysBetweenMonths += 30;
                        }
                    }
                    int finalResult = remainingDays + daysBetweenMonths;
                    if (finalResult<0)
                     return new ResponseEntity<>("there is no more days left :(", HttpStatus.OK);
                    return new ResponseEntity<>(finalResult + " Days Left To '" + entireTask.get().getTask() + "' :>", HttpStatus.OK);
                } else {
                    int remainingDays = 30 - dayOfMonth + day;
                    int daysBetweenMonths = 0;
                    for (int i = month + 1; i < positionOfMonth; i++) {
                        if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                            daysBetweenMonths += 31;
                        } else {
                            daysBetweenMonths += 30;
                        }
                    }

                    int finalResult = remainingDays + daysBetweenMonths;
                    if (finalResult<0)
                     return new ResponseEntity<>("there is no more days left :(", HttpStatus.OK);
                    return new ResponseEntity<>(finalResult + " Days Left To '" + entireTask.get().getTask() + "' :>", HttpStatus.OK);
                }
            }
    }else{
        return new ResponseEntity<>("task has been done ;)", HttpStatus.OK);
    }
   }
    @Transactional
    public ResponseEntity<String> finishedTask(String task) {
       Optional<Task> isValid= repo.findByTaskName(task);
        if (isValid.isPresent()){
        repo.UpdateStateOfTask(task);
        return new ResponseEntity<>("updated task state successfully", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("could not update the task ", HttpStatus.BAD_REQUEST);
    }
}
