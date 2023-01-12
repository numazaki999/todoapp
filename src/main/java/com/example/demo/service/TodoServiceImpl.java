package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepository;
	
	@Override
	public void saveTodo(Todo todo) {
		todoRepository.saveOne(todo);
	}
	
	@Override
	public Optional<Todo> getTodo(Integer id) {
		return todoRepository.selectOne(id);
	}
	
	@Override
	public List<Todo> getTodos() {
		return todoRepository.selectAll();
	}

	@Override
	public void updateTodo(Todo todo) {
		todoRepository.updateOne(todo);
	}
	
	@Override
	public void finishTodo(Integer id) {
		todoRepository.finishOne(id);
	}
	
	@Override
	public void deleteTodo(Integer id) {
		todoRepository.deleteOne(id);
	}
}
