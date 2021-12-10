package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
	@Id
    @GeneratedValue
    private Long id;
	
    private String message;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Europe/Rome")
    private Date date;
}
