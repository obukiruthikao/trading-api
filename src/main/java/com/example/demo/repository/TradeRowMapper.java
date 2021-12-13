package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entities.Trade;

public class TradeRowMapper implements RowMapper<Trade> {
	@Override
	public Trade mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Trade(rs.getInt("ID"), rs.getString("TimeStamp"), rs.getString("StockTicker"),
				rs.getString("BuyOrSell"), rs.getDouble("Price"), rs.getInt("Volume"), rs.getInt("StatusCode"),
				rs.getInt("CustomerId"));
	}
}

