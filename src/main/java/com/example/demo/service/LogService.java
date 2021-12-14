package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Log;
import com.example.demo.repository.LogRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class LogService {
	
	private LogRepository logRepo;
	
	public void save(String message) {
		Log log = new Log(null, message, new Date());
		this.logRepo.save(log);
	}
	
	public ResponseEntity<Log> retrieveLogById(Long id) {
		Log message = this.logRepo.findById(id).orElse(null);
		
		if (message != null) {
	    	log.info("Message found: " + message);
	    	return new ResponseEntity<>(message, HttpStatus.OK);
	    } else {
	    	log.info("The message doesn't exist.");
	    	return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<List<Log>> retrieveLogsByText(String text) {
		List<Log> messageList = this.logRepo.findByMessageContaining(text);
		
		if (!messageList.isEmpty()) {
	    	log.info("Message(s) found: " + messageList);
	    	return new ResponseEntity<>(messageList, HttpStatus.OK);
		} else {
	    	log.info("There's no message containing: " + text);
	    	return new ResponseEntity<>(messageList, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<List<Log>> retrieveLogsByDate(Date date) {
		List<Log> messageList = this.logRepo.findByDate(date);
		
		if (!messageList.isEmpty()) {
	    	log.info("Message(s) found: " + messageList);
	    	return new ResponseEntity<>(messageList, HttpStatus.OK);
		} else {
	    	log.info("There's no message sent on: " + date);
	    	return new ResponseEntity<>(messageList, HttpStatus.NOT_FOUND);
	    }
	}
}
