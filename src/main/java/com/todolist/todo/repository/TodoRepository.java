package com.todolist.todo.repository;

import com.todolist.todo.models.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
