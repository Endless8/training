package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Log;
import com.example.demo.repository.LogRepository;

@ExtendWith(MockitoExtension.class) // The annotation substitutes the usage of the AutoCloseable object commented below
public class LogServiceTest {

	@Mock
	private LogRepository logRepository;
	
//	private AutoCloseable autoCloseable;
	private LogService logService;
	
	@BeforeEach
	void setUp() {
//		this.autoCloseable = MockitoAnnotations.openMocks(this); // Initializing all the mocks in "this" class
		this.logService = new LogService(logRepository);
	}
	
//	@AfterEach
//	void tearDown() throws Exception {
//		this.autoCloseable.close();
//	}
	
	@Test
	void canRetrieveLogById() {
		given(this.logRepository.findById(1L))
			.willReturn(Optional.of(new Log(1L, "test", new Date())));
		
		// when
		this.logService.retrieveLogById(1L);
		
		// then
		verify(this.logRepository).findById(1L); // Verify the log repository is invoking the findById method
	}
	
	@Test
	void cannotRetrieveLogById() {
		// when
		this.logService.retrieveLogById(1L);
		
		// then
		verify(this.logRepository).findById(1L); // Verify the log repository is invoking the findById method
	}
	
	@Test
	void canRetrieveLogByText() {
		given(this.logRepository.findByMessageContaining("te"))
			.willReturn(new ArrayList<Log>(Arrays.asList(new Log(1L, "test", new Date()))));
		
		// when
		this.logService.retrieveLogsByText("te");
		
		// then
		verify(this.logRepository).findByMessageContaining("te"); // Verify the log repository is invoking the findByMessageContaining method
	}
	
	@Test
	void cannotRetrieveLogByText() {
		// when
		this.logService.retrieveLogsByText("te");
		
		// then
		verify(this.logRepository).findByMessageContaining("te"); // Verify the log repository is invoking the findByMessageContaining method
	}
	
	@Test
	void canRetrieveLogByDate() {
		Date currentDate = new Date();
		given(this.logRepository.findByDate(currentDate))
			.willReturn(new ArrayList<Log>(Arrays.asList(new Log(1L, "test", currentDate))));
		
		// when
		this.logService.retrieveLogsByDate(currentDate);
		
		// then
		verify(this.logRepository).findByDate(currentDate); // Verify the log repository is invoking the findByDate method
	}
	
	@Test
	void cannotRetrieveLogByDate() {
		Date currentDate = new Date();
		
		// when
		this.logService.retrieveLogsByDate(currentDate);
		
		// then
		verify(this.logRepository).findByDate(currentDate); // Verify the log repository is invoking the findByDate method
	}
	
	@Test
	void canCreateLog() {
		// given
		String message = "test";
		
		// when
		this.logService.save(message);
		
		// then
		ArgumentCaptor<Log> logArgumentCaptor = ArgumentCaptor.forClass(Log.class);
		verify(this.logRepository).save(logArgumentCaptor.capture()); // Capture the argument being saved
		
		Log capturedLog = logArgumentCaptor.getValue(); // Retrieve the argument
		
		assertThat(capturedLog.getMessage()).isEqualTo(message); // Assert the condition
	}
}
