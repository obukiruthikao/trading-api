package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Portfolio;

@SpringBootTest
public class MySQLPortfolioRepositoryTest {
	@Autowired
	MySQLPortfolioRepository mySQLPortfolioRepository;

	@Test
	public void testGetAllTickers() {
		List<Portfolio> returnedList = mySQLPortfolioRepository.getAllTickers();

		assertThat(returnedList).isNotEmpty();
	}
}
