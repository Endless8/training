package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.KafkaProducerTemplate;
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
	    log.info("Message " + message + " saved successfully!");
	}
}
