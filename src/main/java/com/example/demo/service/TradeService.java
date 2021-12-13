package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Trade;
import com.example.demo.repository.TradeRepository;

@Service
public class TradeService {
	@Autowired
	private TradeRepository repository;

	public List<Trade> getAllTrade() {
		return repository.getAllTrade();
	}

	public Trade getTrade(int id) {
		return repository.getTradeById(id);
	}

	public Trade newTrade(Trade trade) {
		return repository.addTrade(trade);
	}
}
