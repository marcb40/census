package com.celerity.service.rate.api;

import java.math.BigDecimal;
import java.util.List;

import com.celerity.censusmodel.model.Rate;
import com.celerity.dto.CensusPersonDto;

public interface RateCalculator {

	/**
	 * Given the rates for a product, calcuate which rate applies to the given person
	 * 
	 * @param person
	 *            - The person whose rate you are requesting  
	 * @param rates
	 *            - list of rates for every age
	 * @return BigDecimal
	 */
	public BigDecimal calculate(CensusPersonDto person, List<Rate> rates);
}
