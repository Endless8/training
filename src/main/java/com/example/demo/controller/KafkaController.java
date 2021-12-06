package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.KafkaProducerTemplate;

@RestController
public class KafkaController {
	
	@Autowired
	private KafkaProducerTemplate kafkaProducerTemplate;
	
	@GetMapping("/send")
	public void sendMessage(@RequestParam("message") String message) {
		kafkaProducerTemplate.sendMessage(message);
	}
}
