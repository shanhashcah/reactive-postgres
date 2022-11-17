package com.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.model.Student;
import com.reactive.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public StudentService()
	{
		
	}
	
	public Flux<Student> findStudentsByName(String name)
	{
		return (name!=null)?studentRepository.findByName(name):studentRepository.findAll();
	}
	
	public Mono<Student> findStudentById(long id)
	{
		return studentRepository.findById(id);
	}
	public Mono<Student> addNewStudent(Student student)
	{
		return studentRepository.save(student);
	}
	public Mono<Student> updateStudent(long id,Student student)
	{
		return studentRepository.findById(id)
				.flatMap(s->{
					student.setId(id);
					return studentRepository.save(student);
				});
		
	}
	
	public Mono<Void> deleteStudent(long id)
	{
		return studentRepository.deleteById(id);
	}
	
	public Flux<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
}
