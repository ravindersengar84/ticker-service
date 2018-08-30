package com.publisap.stockStats.rest;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.publisap.stockStats.builder.RestServiceBuilder;
import com.publisap.stockStats.service.StockTickerServiceImpl;
import com.publisap.stockStats.view.StockTicker;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StockInformationController.class, secure = false)
public class StockInformationControllerTest {

	@MockBean
	private StockTickerServiceImpl studentService;
	
	@MockBean
	MockMvc mockMvc;

	@Mock
	private RestServiceBuilder<StockTicker> restServiceBuilder;

	@Autowired
    private Environment env;
	
	
	@Test
	public void retrieveDetailsForCourse() throws Exception {

		when(restServiceBuilder.call(Mockito.anyString(), Mockito.anyObject())).thenReturn(mockObject());
		String stockTickerURL = env.getProperty("stock.ticker.url");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(stockTickerURL.concat("BTCUSD")).accept(MediaType.APPLICATION_JSON);
		String expected = "{mid:244.755,bid:244.75,ask:244.76,last_price:244.82,low:244.2,high:248.19,volume:7842.11542563,timestamp:1444253422.348340958}";

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	private StockTicker mockObject() {
		return new StockTicker(new BigDecimal(244.755), new BigDecimal(244.75), new BigDecimal(244.76), new BigDecimal(244.82), 
				new BigDecimal(244.2), new BigDecimal(248.19), new BigDecimal(7842.11542563), null);
		
	}

}

