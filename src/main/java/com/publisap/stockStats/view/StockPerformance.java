package com.publisap.stockStats.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StockPerformance implements BaseVO {
	
	private StockTicker stockTicker;
	private String stockChangeStatus;

}
