package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;

import jakarta.websocket.server.PathParam;

@RestController
public class TodoJpaResource {
	
	private TodoService todoService;     // defining the instance variable of TodoService
	
	private TodoRepository todoRepository;
	
	// Constructor Dependency injection
	public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		//return todoService.findByUsername(username);  // using TodoService
		
		return todoRepository.findByUsername(username);    // here using TodoJpaResource (ie JPA Repository interface to communicate with in memory database) 
	}
	
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		//return todoService.findById(id);  // here using TodoService
		
		return todoRepository.findById(id).get();        // here using TodoJpaResource (ie JPA Repository interface to communicate with in memory database)
	}
	
	
	// here ResponseEntitiy used bcos - when it comes to delete APIs, you can either send a response status of 200 OR use noContent() back
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		//todoService.deleteById(id);     // here using TodoService
		
		todoRepository.deleteById(id);    // here using TodoJpaResource (ie JPA Repository interface to communicate with in memory database)
		return ResponseEntity.noContent().build();
		
	}
	
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
		//todoService.updateTodo(todo);   // here using TodoService
		
		todoRepository.save(todo);       // here using TodoJpaResource (ie JPA Repository interface to communicate with in memory database)
		return todo;
		
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
		
//		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
//		return createdTodo;      // both above code is using TodoService
		
		
		todo.setUsername(username);
		todo.setId(null);
		
		return todoRepository.save(todo);   // here using TodoJpaResource (ie JPA Repository interface to communicate with in memory database)
		
	}
	
	

}
