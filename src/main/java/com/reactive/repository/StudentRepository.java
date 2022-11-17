package com.reactive.repository;



import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.reactive.model.Student;

import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student,Long>{
	
	public Flux<Student> findByName(String name);

}
