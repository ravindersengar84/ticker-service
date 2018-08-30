package com.publisap.stockStats.service;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.publisap.stockStats.builder.RestServiceBuilder;
import com.publisap.stockStats.constant.ApplicationConstants;
import com.publisap.stockStats.exception.ApplicationException;
import com.publisap.stockStats.exception.NoResultFoundException;
import com.publisap.stockStats.util.message.AppMessage;
import com.publisap.stockStats.view.StockPerformance;
import com.publisap.stockStats.view.StockTicker;

@Service
public class StockTickerServiceImpl implements StockTickerService {
	
	private static final Logger log = LoggerFactory.getLogger(StockTickerServiceImpl.class);

	@Autowired
	private RestServiceBuilder<StockTicker> restServiceBuilder;
	
	@Autowired
    private Environment env;
	
	public StockPerformance getStockTickerInfo(String symbol) throws ApplicationException, NoResultFoundException {
		try {
			String stockTickerURL = env.getProperty("stock.ticker.url");
			if(null==stockTickerURL) {
				throw new NoResultFoundException(AppMessage.ApplicationException.RESOURCE_NOT_FOUND_EXCEPTION);
			}
			StockTicker stockTicker = restServiceBuilder.call(stockTickerURL.concat(symbol), StockTicker.class);
			if(null == stockTicker) {
				throw new NoResultFoundException(AppMessage.ApplicationException.NO_RECORD_EXCEPTION);
			}
			
			log.debug("Ticker information for "+symbol + " - " + stockTicker.toString());
			return new StockPerformance(stockTicker, getStockChangeStatus(stockTicker.getMidPrice(), stockTicker.getLastPrice()));
		} catch (Exception e) {
			throw new ApplicationException(AppMessage.GeneralException.SYSTEM_EXCEPTION, e);
		}
	}
	
	private String getStockChangeStatus(BigDecimal midVal, BigDecimal lastVal) {
		
		if(midVal.compareTo(lastVal) > 0) {
			return ApplicationConstants.UP;
		}else if (midVal.compareTo(lastVal) < 0) {
			return ApplicationConstants.DOWN;
		}else {
			return ApplicationConstants.SAME;
		}
	}
}
