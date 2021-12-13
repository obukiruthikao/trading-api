package com.example.demo.repository;

import java.util.List;



import com.example.demo.entities.Trade;


public interface TradeRepository {

	public List<Trade> getAllTrade();// To get list of stocks

	public Trade getTradeById(int id);// to get by id

	public Trade addTrade(Trade trade);// to add a new one

}
