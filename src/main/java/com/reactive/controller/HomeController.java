package com.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.model.Student;
import com.reactive.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

	@Autowired
	StudentService service;
	
	@GetMapping("/getStudentById/{id}")
	public Mono<ResponseEntity<Student>> getStudentById(@PathVariable long id)
	{
		return service.findStudentById(id)
				.map(ResponseEntity::ok)
				;
	}
	@PostMapping("/saveStudent")
	public Mono<Student> saveStudent(@RequestBody Student student)
	{
		return service.addNewStudent(student);
	}
	
	@RequestMapping(value = "/getStudents",method = RequestMethod.GET,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Student> allStudents()
	{
		return service.getAllStudents();
	}
	
	@GetMapping("/deleteStudent/{id}")
	public Mono<Void> deleteStudent(@PathVariable long id)
	{
		return service.deleteStudent(id);
	}
}
