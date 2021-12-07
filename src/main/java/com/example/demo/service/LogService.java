package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Log;
import com.example.demo.repository.LogRepository;

@Service
public class LogService {
	@Autowired
	private LogRepository logRepo;
	
	public void save(String message) {
		Log log = new Log(null, message, new Date());
		this.logRepo.save(log);
	}
}
