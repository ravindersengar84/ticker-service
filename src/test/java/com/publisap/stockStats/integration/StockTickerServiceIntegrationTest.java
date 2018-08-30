package com.publisap.stockStats.integration;

  
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.publisap.stockStats.builder.RestServiceBuilder;
import com.publisap.stockStats.view.StockTicker;
  
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockTickerServiceIntegrationTest {
	
	private static final Logger log = LoggerFactory.getLogger(StockTickerServiceIntegrationTest.class);
	
    public static final String REST_SERVICE_URI = "https://api.bitfinex.com/v1/pubticker/";
    
    @Autowired
    private Environment env;
    
    @Autowired
    private RestServiceBuilder<StockTicker> restAdaptor;
     
    @Test
    private void testGetStockTickerInfo(){
    	String symbol = "BTCUSD";
    	log.debug("Testing getStockTicker API for symbol - " + symbol);
    	
    	String stockTickerURL = env.getProperty("stock.ticker.url");
        StockTicker ticker = restAdaptor.call(stockTickerURL.concat("BTCUSD"), StockTicker.class);
        assertEquals(ticker.getAskPrice(), new BigDecimal(244.76));
        assertEquals(ticker.getMidPrice(), new BigDecimal(244.755));
        assertEquals(ticker.getHighestPrice(), new BigDecimal(248.19));
    }
    
    //String expected = "{mid:244.755,bid:244.75,ask:244.76,last_price:244.82,low:244.2,high:248.19,volume:7842.11542563,timestamp:1444253422.348340958}";
}