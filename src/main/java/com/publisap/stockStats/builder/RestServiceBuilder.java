package com.publisap.stockStats.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestServiceBuilder<T> {

	@Autowired
	RestTemplate restTemplate;

	public T call(String url, Class<T> t) {
		
		return  (T) restTemplate.getForObject(url, t);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
