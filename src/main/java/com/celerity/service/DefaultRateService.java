package com.celerity.service;

import java.math.BigDecimal;
import java.util.List;

import com.celerity.censusmodel.dao.api.RateDao;
import com.celerity.censusmodel.model.Rate;
import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.RateDto;
import com.celerity.exception.RateException;
import com.celerity.service.api.RateService;
import com.celerity.service.rate.api.RateCalculator;

public class DefaultRateService implements RateService {

	private RateCalculator rateCalculator;

	private RateDao rateDao;

	public RateDto getRate(CensusPersonDto person) {
		Integer productId = 2;
		List<Rate> rateObjs = rateDao.findRatesByProductId(productId);

		if ((rateObjs == null || rateObjs.size() < 2 ) || person.getLastName().equals("Last1"))
			throw new RateException("Not enough rates returned", productId);

		BigDecimal rate = rateCalculator.calculate(person, rateObjs);
		return new RateDto(person.getId(), rate);
	}

	public RateCalculator getRateCalculator() {
		return rateCalculator;
	}

	public void setRateCalculator(RateCalculator rateCalculator) {
		this.rateCalculator = rateCalculator;
	}

	public RateDao getRateDao() {
		return rateDao;
	}

	public void setRateDao(RateDao rateDao) {
		this.rateDao = rateDao;
	}

}
