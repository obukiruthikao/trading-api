package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Trade;
import com.example.demo.exceptions.ResourceNotFoundException;

@Repository
public class MySQLTradeRepository implements TradeRepository {
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Trade> getAllTrade() {
		String sql = "SELECT Id, Timestamp, StockTicker, BuyorSell, Price, Volume,  StatusCode, CustomerId FROM TradeHistory ORDER BY Id DESC";
		return template.query(sql, new TradeRowMapper());
	}

	@Override
	public Trade getTradeById(int id) {
		try {
			String sql = "SELECT Id, Timestamp, StockTicker, BuyorSell, Price, Volume,  StatusCode, CustomerId FROM TradeHistory WHERE ID=?";
			return template.queryForObject(sql, new TradeRowMapper(), id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public Trade addTrade(Trade trade) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO TradeHistory(TimeStamp, StockTicker, BuyorSell, Price, Volume, StatusCode, CustomerId) VALUES(?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				ps.setString(1, dtf.format(now));
				ps.setString(2, trade.getStockTicker());
				ps.setString(3, trade.getBuyOrSell());
				ps.setDouble(4, trade.getPrice());
				ps.setInt(5, trade.getVolume());
				ps.setInt(6, trade.getStatusCode());
				ps.setInt(7, trade.getCustomerId());

				return ps;
			}
		}, keyHolder);

		trade.setId(keyHolder.getKey().intValue());
		return trade;
	}

}
