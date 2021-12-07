package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
	
	List<Log> findByMessageContaining(String message);
}
