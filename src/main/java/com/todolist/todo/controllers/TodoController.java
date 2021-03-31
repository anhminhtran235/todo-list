package com.todolist.todo.controllers;

import java.util.List;

import com.todolist.todo.models.Todo;
import com.todolist.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/todos")
public class TodoController {
    @Autowired
    private TodoRepository todoRepo;

    @GetMapping("")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Todo todo = todoRepo.findById(id).orElse(null);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("")
    public ResponseEntity<Todo> addTodo(@RequestBody String description) {
        Todo todo = new Todo(description);
        todoRepo.save(todo);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, String description) {
        Todo todo = todoRepo.findById(id).orElse(null);
        if (todo == null) {
            throw new IllegalArgumentException("Todo item does not exist");
        }
        if (description != null) {
            todo.setDescription(description);
        }
        todoRepo.save(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
        todoRepo.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
