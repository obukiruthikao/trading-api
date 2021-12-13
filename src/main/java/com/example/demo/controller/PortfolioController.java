
package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Portfolio;
import com.example.demo.service.PortfolioService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/portfolio")
public class PortfolioController {

	private static final Logger LOG = LoggerFactory.getLogger(PortfolioController.class);

	@Autowired
	private PortfolioService service;

	@GetMapping
	public List<Portfolio> getAllTickers() {
		return service.getAllTickers();
	}

	@GetMapping(value = "/{ticker}")
	public Portfolio getTickerById(@PathVariable("ticker") String stockTicker) {
		return service.getTicker(stockTicker);
	}

}
