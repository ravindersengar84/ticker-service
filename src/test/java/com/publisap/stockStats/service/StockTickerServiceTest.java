package com.publisap.stockStats.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.publisap.stockStats.builder.RestServiceBuilder;
import com.publisap.stockStats.exception.ApplicationException;
import com.publisap.stockStats.exception.NoResultFoundException;
import com.publisap.stockStats.service.StockTickerService;
import com.publisap.stockStats.view.StockTicker;

@RunWith(MockitoJUnitRunner.class)
public class StockTickerServiceTest {

	public static final String REST_SERVICE_URI = "https://api.bitfinex.com/v1/pubticker/";
	
	@InjectMocks
	private StockTickerService stockTickerService;
	
	@Mock
	private RestServiceBuilder<StockTicker> restServiceBuilder;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetModulePrice() throws NoResultFoundException, ApplicationException {
		when(restServiceBuilder.call(Mockito.anyString(), Mockito.anyObject())).thenReturn(mockObject());
		assertEquals(new BigDecimal(.50), stockTickerService.getStockTickerInfo("BTCUSD"));
	}

	private StockTicker mockObject() {
		return new StockTicker(new BigDecimal(244.755), new BigDecimal(244.75), new BigDecimal(244.76), new BigDecimal(244.82), 
				new BigDecimal(244.2), new BigDecimal(248.19), new BigDecimal(7842.11542563), null);
		
	}
}
