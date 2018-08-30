package com.publisap.stockStats.service;

import org.springframework.stereotype.Service;

import com.publisap.stockStats.exception.ApplicationException;
import com.publisap.stockStats.exception.NoResultFoundException;
import com.publisap.stockStats.view.StockPerformance;

@Service
public interface StockTickerService {
	
	public StockPerformance getStockTickerInfo(String symbol) throws ApplicationException, NoResultFoundException;

}
