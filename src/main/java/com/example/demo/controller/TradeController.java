package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Trade;
import com.example.demo.service.TradeService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/trading")
public class TradeController {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

	@Autowired
	private TradeService service;

	@GetMapping(value = "/")
	public List<Trade> getAllTrade() {
		return service.getAllTrade();
	}

	@GetMapping(value = "/{id}")
	public Trade getTradeById(@PathVariable("id") int id) {
		return service.getTrade(id);
	}

	@PostMapping(value = "/")
	public Trade addTrade(@RequestBody Trade trade) {
		return service.newTrade(trade);
	}
}
