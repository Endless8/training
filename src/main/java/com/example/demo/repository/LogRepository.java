package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
