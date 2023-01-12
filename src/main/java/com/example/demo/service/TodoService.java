package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Todo;

public interface TodoService {
	
	public void saveTodo(Todo todos);
	
	public Optional<Todo> getTodo(Integer id);
	
	public List<Todo> getTodos();
	
	public void updateTodo(Todo todos);
	
	public void finishTodo(Integer id);
	
	public void deleteTodo(Integer id);
}