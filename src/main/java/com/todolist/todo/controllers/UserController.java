package com.todolist.todo.controllers;

import com.todolist.todo.models.User;
import com.todolist.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String getHello() {
        return "Hello world";
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getName() + ", " + user.getId());
        return userRepository.save(user);
    }
}
