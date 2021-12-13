package com.example.demo.entities;

public class Trade {
	private int id;
	private String timeStamp;
	private String stockTicker;
	private String buyorsell;
	private double price;
	private int volume;
	private int statusCode;
	private int customerId;

	public Trade() {

	}

	public Trade(int id, String timestamp, String stockticker, String buyorsell, double price, int volume,
			int statuscode, int customerid) {
		super();
		this.id = id;
		this.timeStamp = timestamp;
		this.stockTicker = stockticker;
		this.buyorsell = buyorsell;
		this.price = price;
		this.volume = volume;
		this.statusCode = statuscode;
		this.customerId = customerid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timestamp) {
		this.timeStamp = timestamp;
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockticker) {
		this.stockTicker = stockticker;
	}

	public String getBuyOrSell() {
		return buyorsell;
	}

	public void setBuyOrSell(String buyorsell) {
		this.buyorsell = buyorsell;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statuscode) {
		this.statusCode = statuscode;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerid) {
		this.customerId = customerid;
	}

}
