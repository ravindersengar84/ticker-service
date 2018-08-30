package com.publisap.stockStats.view;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StockTicker implements BaseVO {
	
	@JsonProperty("mid")
	private BigDecimal midPrice;
	@JsonProperty("bid")
	private BigDecimal bidPrice;
	@JsonProperty("ask")
	private BigDecimal askPrice;
	@JsonProperty("last_price")
	private BigDecimal lastPrice;
	@JsonProperty("low")
	private BigDecimal lowestPrice;
	@JsonProperty("high")
	private BigDecimal highestPrice;
	@JsonProperty("volume")
	private BigDecimal volume;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", shape = JsonFormat.Shape.NUMBER_FLOAT)
	private String timestamp;

}
