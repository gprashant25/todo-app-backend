package com.in28minutes.rest.webservices.restfulwebservices.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUsername(String username);    // this is an interface's method i.e an abstract method without a method body

}
