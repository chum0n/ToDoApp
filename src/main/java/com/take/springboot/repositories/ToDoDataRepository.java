package com.take.springboot.repositories;

import com.take.springboot.ToDoData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDataRepository extends JpaRepository<ToDoData, Long > {
	
	public Optional<ToDoData> findById(Long name);
}
