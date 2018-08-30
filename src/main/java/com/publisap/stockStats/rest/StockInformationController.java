package com.publisap.stockStats.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.publisap.stockStats.service.StockTickerServiceImpl;
import com.publisap.stockStats.view.StockPerformance;
import com.publisap.stockStats.view.StockTicker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/stockStats/v1/pubticker")
@Api(tags = { "stock-information-service" })
public class StockInformationController extends AbstractRestHandler {

	@Autowired
	private StockTickerServiceImpl stockTickerService;
	
	private static final Logger log = LoggerFactory.getLogger(StockInformationController.class);

	@GetMapping(value = "/{symbol}", produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Ticker is high level overview of the state of the market", response = StockTicker.class)
	public ResponseEntity<StockPerformance> ticker(@PathVariable String symbol) throws Exception {
		StockPerformance stockPerformance;
		try {
			stockPerformance = stockTickerService.getStockTickerInfo(symbol);
		} catch (Exception e) {
			log.error("Exception occured while fetching ticker information for - "+ symbol);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(stockPerformance, HttpStatus.OK);
	}
	
}
