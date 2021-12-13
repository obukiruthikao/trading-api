package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entities.Trade;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.TradeRepository;

//@ActiveProfiles("h2")
@SpringBootTest
public class TradeServiceTest {

	@Autowired
	TradeService tradeService;

	@MockBean
	TradeRepository tradeRepository;

	@Test
	public void testGetShipper() {

		int testId = 5;
		Trade testTrade = new Trade();
		testTrade.setId(testId);
		testTrade.setTimeStamp("2021/08/27 12:20:07");
		testTrade.setStockTicker("APPL");
		testTrade.setBuyOrSell("B");
		testTrade.setPrice(5);
		testTrade.setVolume(500);
		testTrade.setStatusCode(0);
		testTrade.setCustomerId(22);

		when(tradeRepository.getTradeById(testTrade.getId())).thenReturn(testTrade);

		Trade returnedTrade = tradeService.getTrade(testId);

		assertThat(returnedTrade).isEqualTo(testTrade);
	}

	@Test
	public void testGetTradeNotFound() {
		int testId = 999;
		when(tradeRepository.getTradeById(testId)).thenThrow(new ResourceNotFoundException());
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			tradeService.getTrade(testId);
		});
	}

	@Test
	public void testAddTrade() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Trade testTrade = new Trade();
		// testTrade.setId(5);
		testTrade.setStockTicker("APPL");
		testTrade.setTimeStamp(dtf.format(now));
		testTrade.setBuyOrSell("B");
		testTrade.setPrice(5);
		testTrade.setVolume(500);
		testTrade.setStatusCode(0);
		testTrade.setCustomerId(22);
		when(tradeRepository.addTrade(testTrade)).thenReturn(testTrade);
		assertThat(tradeService.newTrade(testTrade)).isEqualTo(testTrade);
	}
}
