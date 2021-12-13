package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Portfolio;
import com.example.demo.entities.Trade;
import com.example.demo.exceptions.ResourceNotFoundException;

@Repository
public class MySQLPortfolioRepository implements PortfolioRepository {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Portfolio> getAllTickers() {
		String sql = "SELECT Id, StockTicker, Volume FROM Portfolio";
		return template.query(sql, new PortfolioRowMapper());
	}

	@Override
	public Portfolio getTickerById(String stockTicker) {
		String sql = "SELECT Id, StockTicker, Volume FROM Portfolio WHERE StockTicker=?";
		return template.queryForObject(sql, new PortfolioRowMapper(), stockTicker);
	}
	

	
}


