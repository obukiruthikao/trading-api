package com.example.demo.repository;

import java.util.List;
import com.example.demo.entities.Portfolio;

public interface PortfolioRepository {
	public List<Portfolio> getAllTickers();

	public Portfolio getTickerById(String stockTicker);

	
}
