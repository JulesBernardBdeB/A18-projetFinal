package com.example.projetfinal.services;

import com.example.projetfinal.models.Task;

import java.util.Optional;

public interface TaskService {

    Task createTask(Task task);

    void updateTask(long id, Task task);

    Iterable<Task> getTasks();

    Optional<Task> getTaskByID(long id);
    void deleteTask(long id);


}
