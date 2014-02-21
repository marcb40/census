package com.celerity.service.rate;

import java.math.BigDecimal;
import java.util.List;

import com.celerity.censusmodel.model.Rate;
import com.celerity.dto.CensusPersonDto;
import com.celerity.service.rate.api.RateCalculator;

public class DefRateCalculator implements RateCalculator {

	public BigDecimal calculate(CensusPersonDto person, List<Rate> rates) {
		return rates.get(1).getRate();
	}

	
	
}
