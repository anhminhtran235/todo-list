package com.todolist.todo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    private Integer isFinished;

    public Todo() {
    }

    public Todo(String description, Integer isFinished) {
        this.description = description;
        this.isFinished = isFinished;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }
}
