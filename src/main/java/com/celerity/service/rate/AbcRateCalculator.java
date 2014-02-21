package com.celerity.service.rate;

import java.math.BigDecimal;
import java.util.List;

import com.celerity.censusmodel.model.Rate;
import com.celerity.dto.CensusPersonDto;
import com.celerity.service.rate.api.RateCalculator;

public class AbcRateCalculator  implements RateCalculator {

	/*
	 * Just return the first rate in the list no matter what person it is.
	 * 
	 * @see com.celerity.service.rate.api.RateCalculator#calculate(com.celerity.dto.CensusPersonDto, java.util.List)
	 */
	public BigDecimal calculate(CensusPersonDto person, List<Rate> rates) {
		return calculate(person, rates, false);
	}

	/*
	 * example of overloading a method
	 * @see com.celerity.service.rate.api.RateCalculator#calculate(com.celerity.dto.CensusPersonDto, java.util.List, boolean)
	 */
	public BigDecimal calculate(CensusPersonDto person, List<Rate> rates, boolean specialRate) {
		return (specialRate) ? rates.get(0).getRate().add(BigDecimal.valueOf(-5)) : rates.get(0).getRate();
	}
	
	
}
