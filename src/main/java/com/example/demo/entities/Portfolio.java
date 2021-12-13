package com.example.demo.entities;

public class Portfolio {
	private int id;
	private String stockTicker;
	private int volume;

	public Portfolio(int id, String stockTicker, int volume) {
		super();
		this.id = id;
		this.stockTicker = stockTicker;
		this.volume = volume;
	}

	public Portfolio() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}