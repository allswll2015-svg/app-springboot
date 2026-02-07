package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    protected Todo() {}

    public Todo(String title) {
        this.title = title;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
}
