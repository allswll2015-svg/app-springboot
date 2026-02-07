package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository repo;

    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Todo> list() {
        return repo.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody CreateTodoRequest req) {
        if (req == null || req.title() == null || req.title().isBlank()) {
            throw new IllegalArgumentException("title is required");
        }
        return repo.save(new Todo(req.title().trim()));
    }

    public record CreateTodoRequest(String title) {}
}
