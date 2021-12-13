
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Portfolio;
import com.example.demo.repository.PortfolioRepository;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository repository;

	public List<Portfolio> getAllTickers() {
		return repository.getAllTickers();
	}

	public Portfolio getTicker(String stockTicker) {
		return repository.getTickerById(stockTicker);
	}
	
}
