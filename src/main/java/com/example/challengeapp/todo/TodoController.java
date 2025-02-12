package com.example.challengeapp.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    public final TodoExternalApiService todoExternalApiService;

    public TodoController(final TodoExternalApiService todoExternalApiService) {
        this.todoExternalApiService = todoExternalApiService;
    }

    @GetMapping("/getById/{id}")
    public Todo getTodo(@PathVariable final int id) {
        return todoExternalApiService.getExternalSingleTodoData(id).getBody();
    }

    @GetMapping("/all")
    public List<Todo> getAllTodo() {
        return todoExternalApiService.getExternalAllTodoData().getBody();
    }


}
