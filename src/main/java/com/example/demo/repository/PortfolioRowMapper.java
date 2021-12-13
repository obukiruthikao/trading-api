package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entities.Portfolio;

public class PortfolioRowMapper implements RowMapper<Portfolio> {

	@Override
	public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Portfolio(rs.getInt("id"), rs.getString("StockTicker"), rs.getInt("Volume"));
	}

}