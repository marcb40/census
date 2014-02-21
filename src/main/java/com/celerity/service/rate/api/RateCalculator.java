package com.celerity.service.rate.api;

import java.math.BigDecimal;
import java.util.List;

import com.celerity.censusmodel.model.Rate;
import com.celerity.dto.CensusPersonDto;

public interface RateCalculator {

	
	public BigDecimal calculate(CensusPersonDto person, List<Rate> rates);
}
