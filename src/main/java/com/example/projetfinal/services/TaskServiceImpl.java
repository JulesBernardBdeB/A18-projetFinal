package com.example.projetfinal.services;

import com.example.projetfinal.models.Task;
import com.example.projetfinal.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository repository;
    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public void updateTask(long id, Task task) {
       Optional<Task> optionalTask = repository.findById(id);
        if(optionalTask.isPresent()){
            Task taskToUpdate = optionalTask.get();
            taskToUpdate.setName(task.getName());
            taskToUpdate.setDate(task.getDate());
            taskToUpdate.setDetails(task.getDetails());
            repository.save(taskToUpdate);
        }
    }

    @Override
    public Iterable<Task> getTasks() {
        return repository.findAll();
    }

    @Override
    public Optional<Task> getTaskByID(long id) {
        return repository.findById(id);
    }

    @Override
    public void  deleteTask(long id) {
        repository.deleteById(id);
    }
}
