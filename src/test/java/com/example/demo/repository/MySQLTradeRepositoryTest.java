package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Trade;

@SpringBootTest
public class MySQLTradeRepositoryTest {
	@Autowired
	MySQLTradeRepository mySQLTradingRepository;

	@Test
	public void testGetAllTrade() {
		List<Trade> returnedList = mySQLTradingRepository.getAllTrade();

		assertThat(returnedList).isNotEmpty();
	}
}
