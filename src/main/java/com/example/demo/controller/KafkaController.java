package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.KafkaProducerTemplate;
import com.example.demo.model.Log;
import com.example.demo.service.LogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class KafkaController {
	
	@Autowired
	private KafkaProducerTemplate kafkaProducerTemplate;
	
	@Autowired
	private LogService logService;
	
	@GetMapping("/send")
	public void sendMessage(@RequestParam("message") String message) {
		kafkaProducerTemplate.sendMessage(message);
	}
	
	@KafkaListener(topics = "baeldung", groupId = "foo")
	public void listenGroupFoo(String message) {
	    this.logService.save(message);
	    log.info("Message '" + message + "' saved successfully!");
	}
	
	@GetMapping("/retrieveMessageById")
	public ResponseEntity<Log> retrieveMessageById(@RequestParam("id") Long id) {
		log.info("Retrieving message with id: " + id);
	    Log message = this.logService.retrieveLogById(id);
	    
	    if (message != null) {
	    	log.info("Message found: " + message);
	    	return new ResponseEntity<>(message, HttpStatus.OK);
	    } else {
	    	log.info("The message doesn't exist.");
	    	return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/retrieveMessageByText")
	public ResponseEntity<List<Log>> retrieveMessageByText(@RequestParam("text") String text) {
		log.info("Retrieving message(s) containing: " + text);
		List<Log> messageList = this.logService.retrieveLogsByText(text);
		
		if (!messageList.isEmpty()) {
	    	log.info("Message(s) found: " + messageList);
	    	return new ResponseEntity<>(messageList, HttpStatus.OK);
		} else {
	    	log.info("There's no message containing: " + text);
	    	return new ResponseEntity<>(messageList, HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/retrieveMessageByDate")
	public ResponseEntity<List<Log>> retrieveMessageByDate(@RequestParam("date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		log.info("Retrieving message(s) sent on: " + date);
		List<Log> messageList = this.logService.retrieveLogsByDate(date);
		
		if (!messageList.isEmpty()) {
	    	log.info("Message(s) found: " + messageList);
	    	return new ResponseEntity<>(messageList, HttpStatus.OK);
		} else {
	    	log.info("There's no message sent on: " + date);
	    	return new ResponseEntity<>(messageList, HttpStatus.NOT_FOUND);
	    }
	}
}
