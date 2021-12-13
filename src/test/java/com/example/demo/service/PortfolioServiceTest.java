
package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entities.Portfolio;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.PortfolioRepository;

//@ActiveProfiles("h2")
@SpringBootTest
public class PortfolioServiceTest {

	@Autowired
	PortfolioService portfolioService;

	@MockBean
	PortfolioRepository portfolioRepository;

	@Test
	public void testGetShipper() {

		String testTicker = "CITI";
		int testVolume = 30;
		Portfolio testPortfolio = new Portfolio();
		testPortfolio.setStockTicker(testTicker);
		testPortfolio.setVolume(testVolume);

		when(portfolioRepository.getTickerById(testPortfolio.getStockTicker())).thenReturn(testPortfolio);

		// 2. call class under test
		// call the getShipper method on the service, this will call
		// the getShipperById method on the the mock repository
		// the mock repository returns the testShipper
		// the service should return the same testShipper here
		// we verify this happens, so we know the service is behaving as expected
		Portfolio returnedPortfolio = portfolioService.getTicker(testTicker);

		// 3. verify response
		assertThat(returnedPortfolio).isEqualTo(testPortfolio);
	}

	// Test for a "NotFound scenario"
	// NOTE: this test is unnecessary for this particular class.
	// It's only here to demonstrate how to generate and test for
	// an exception in a test that uses mocking.
	@Test
	public void testGetShipperNotFound() {
		// 1. Setup stuff
		String testTicker = "XXX";
		// tell the mock object what to do when
		// its getShipperById method is called
		// in this case it needs to throw an exception
		when(portfolioRepository.getTickerById(testTicker)).thenThrow(new ResourceNotFoundException());

		// 2. call class under test & 3. verify the results
		// call the getShipper method on the service, this will call
		// the getShipperById method on the the mock repository
		// the mock repository throws an exception
		// the service doesn't catch that exception
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			portfolioService.getTicker(testTicker);
		});
	}
}
