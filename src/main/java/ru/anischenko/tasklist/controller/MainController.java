package ru.anischenko.tasklist.controller;

import org.springframework.scheduling.config.Task;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anischenko.tasklist.model.entity.TaskEntity;
import ru.anischenko.tasklist.model.entity.UserEntity;
import ru.anischenko.tasklist.repository.TasksRepository;
import ru.anischenko.tasklist.repository.UsersRepository;

import java.security.Principal;

@Controller
public class MainController {

    private final TasksRepository tasksRepository;

    private final UsersRepository usersRepository;

    public MainController(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        model.addAttribute("tasks", tasksRepository.findByUserUsername(principal.getName()));
        model.addAttribute("task", new TaskEntity());
        return "index";
    }

    @PostMapping
    public String addTask(TaskEntity task, Principal principal) {
        UserEntity user =usersRepository.findByUsername(principal.getName()).get();
        task.setUser(user);
        tasksRepository.save(task);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        tasksRepository.deleteById(id);
        return "redirect:/";
    }
}
