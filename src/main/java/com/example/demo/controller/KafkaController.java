package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@KafkaListener(topics = "baeldung", groupId = "foo")
	public void listenGroupFoo(String message) {
		this.logService.save(message);
		log.info("Message '" + message + "' saved successfully!");
	}
	
	@GetMapping("/send")
	public void sendMessage(@RequestParam("message") String message) {
		kafkaProducerTemplate.sendMessage(message);
	}
	
	@GetMapping("/retrieveMessageById")
	public ResponseEntity<Log> retrieveMessageById(@RequestParam("id") Long id) {
		log.info("Retrieving message with id: " + id);
	    return this.logService.retrieveLogById(id);
	}
	
	@GetMapping("/retrieveMessageByText")
	public ResponseEntity<List<Log>> retrieveMessageByText(@RequestParam("text") String text) {
		log.info("Retrieving message(s) containing: " + text);
		return this.logService.retrieveLogsByText(text);
	}
	
	@GetMapping("/retrieveMessageByDate")
	public ResponseEntity<List<Log>> retrieveMessageByDate(@RequestParam("date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		log.info("Retrieving message(s) sent on: " + date);
		return this.logService.retrieveLogsByDate(date);
	}
}
