package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Log;

@DataJpaTest
public class LogRepositoryTest {
	
	@Autowired
	private LogRepository logRepo;
	
	@AfterEach
	public void clean() {
		this.logRepo.deleteAll();
	}
	
	@Test
	void itShouldCheckIfLogsContainString() {
		// given
		Log log = new Log(null, "test", new Date());
		logRepo.save(log);
		
		// when
		List<Log> expected = logRepo.findByMessageContaining("es");
		
		// then
		assertThat(expected.size() > 0).isTrue();
	}
	
	@Test
	void itShouldCheckIfLogsDoesNotContainString() {
		// given
		Log log = new Log(null, "test", new Date());
		logRepo.save(log);
		
		// when
		List<Log> expected = logRepo.findByMessageContaining("hhh");
		
		// then
		assertThat(expected.size() > 0).isFalse();
	}
}
