package com.example.projetfinal.controllers;

import com.example.projetfinal.models.Task;
import com.example.projetfinal.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Controller
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping
    public String index(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model){
       List<Task> tasksList = (List<Task>)taskService.getTasks();
       model.addAttribute("tasks",tasksList.isEmpty()? Collections.EMPTY_LIST:tasksList);
       return "index";
    }

    @GetMapping("/addtask")
    public String displayAddTaskForm(Task task){
        return "addtask";
    }

    @PostMapping("/addtask")
    public String addTaskForm(@Valid Task task, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-task";
        }
        taskService.createTask(task);
        model.addAttribute("tasks",taskService.getTasks());
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateTaskForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("task", taskService.getTaskByID(id).get());
        return "update-task";
    }

    @PutMapping("/update/{id}")
    public String displayUpdateTaskForm(@PathVariable("id") Long id, @Valid Task task, Model model, BindingResult result){
        if(result.hasErrors()){
            task.setId(id);
            return "update-task";
        }
        taskService.updateTask(id,task);
        model.addAttribute("tasks",taskService.getTasks());
        return "redirect:/index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable ("id") Long id, Model model){
        taskService.deleteTask(id);
        model.addAttribute("tasks",taskService.getTasks());
        return "redirect:/index";
    }
}
