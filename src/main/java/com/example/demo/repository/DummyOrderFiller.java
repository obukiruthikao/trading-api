package com.example.demo.repository;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Portfolio;
import com.example.demo.entities.Trade;

/**
 * This class is a test harness class - it only exists for testing.
 * 
 * This is SIMULATING sending trade orders to an exchange for them to be filled.
 * 
 * This class uses the following codes to represent the state of an order:
 * 
 * 0 : initialized i.e. has not yet been processed at all 1 : processing i.e.
 * the order has been sent to an exchange, we are waiting for a response 2 :
 * filled i.e. the order was successfully placed 3 : rejected i.e. the order was
 * not accepted by the trading exchange
 * 
 * The above are JUST SUGGESTED VALUES, you can change or improve as you see
 * think is appropriate.
 */
@Repository
public class DummyOrderFiller {

	// Standard mechanism for logging with spring boot - org.slf4j is built-in to
	// spring boot
	private static final Logger LOG = LoggerFactory.getLogger(DummyOrderFiller.class);

	// You'll need to change these to match whatever you called your table and
	// status_code field
	// You may also need to change the database name in application-mysql.properties
	// and application-h2.properties
	// The default database name that's used here is "appDB"
	// private static final String TABLE = "tradehistory";
	// private static final String STATUS_CODE = "statuscode";

	private int percentFailures = 10;

	@Autowired
	private JdbcTemplate template;

	/**
	 * Any record in the configured database table with STATE=0 (init)
	 * 
	 * Will be changed to STATE=1 (processing)
	 */
	@Scheduled(fixedRate = 10000)
	public int findTradesForProcessing() {
		String sql = "UPDATE tradehistory SET statuscode =1 WHERE statuscode=0";
		int numberChanged = template.update(sql);

		LOG.debug("Updated [" + numberChanged + "] order from initialized (0) TO processing (1)");

		return numberChanged;
	}

	/**
	 * Anything in the configured database table with STATE=1 (processing)
	 * 
	 * Will be changed to STATE=2 (filled) OR STATE=3 (rejected)
	 * 
	 * This method uses a random number to determine when trades are rejected.
	 */
	@Scheduled(fixedRate = 15000)
	public int findTradesForFillingOrRejecting() {
		// System.out.println("WORKING!!");
		int totalChanged = 0;
		int lastChanged = 0;

		do {
			lastChanged = 0;

			int randomInteger = new Random().nextInt(100);

			LOG.debug("Random number is [" + randomInteger + "] , failure rate is [" + percentFailures + "]");

			// use a random number to decide if we'll simulate success OR failure
			if (randomInteger > percentFailures) {
				// Mark this one as success
				lastChanged = markTradeAsSuccessOrFailure(2);

				LOG.debug("Updated [" + lastChanged + "] order from processing (1) TO success (2)");
			} else {
				// Mark this one as failure!!
				lastChanged = markTradeAsSuccessOrFailure(3);
				LOG.debug("Updated [" + lastChanged + "] order from processing (1) TO failure (3)");
			}
			totalChanged += lastChanged;

		} while (lastChanged > 0);

		return totalChanged;
	}

	/*
	 * Update a single record to success or failure
	 */
	private int markTradeAsSuccessOrFailure(int successOrFailure) {
		String select = "SELECT * FROM tradehistory WHERE statuscode=1 limit 1";
		Trade trade = new Trade();
		try {
			trade = template.queryForObject(select, new TradeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOG.debug("No record found in database ", e);
			return 0;
		}
		String sql = "UPDATE tradehistory SET statuscode=" + successOrFailure + " WHERE id=" + trade.getId();
		template.update(sql);
		// System.out.println(trade.getId());

		if (successOrFailure == 3)
			return 0;

		String port = "SELECT * from portfolio WHERE stockticker='" + trade.getStockTicker() + "'";
		Portfolio portfolio = new Portfolio();
		try {
			portfolio = template.queryForObject(port, new PortfolioRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOG.debug("No record found in database for " + portfolio.getId(), e);

			String insert = "INSERT INTO portfolio(stockticker, volume) VALUES ('" + trade.getStockTicker() + "',"
					+ trade.getVolume() + ")";
			return template.update(insert);

		}
		String upd;
		if (trade.getBuyOrSell().equals("Buy")) {
			upd = "UPDATE portfolio SET volume=volume+" + trade.getVolume() + " WHERE stockticker='"
					+ portfolio.getStockTicker() + "'";
		} else {
			upd = "UPDATE portfolio SET volume=volume-" + trade.getVolume() + " WHERE stockticker='"
					+ portfolio.getStockTicker() + "'";
		}

		template.update(upd);
		return template.update(sql);
	}

	public void setPercentFailures(int percentFailures) {
		this.percentFailures = percentFailures;
	}

}
