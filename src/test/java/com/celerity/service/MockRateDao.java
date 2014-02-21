package com.celerity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.celerity.censusmodel.dao.api.RateDao;
import com.celerity.censusmodel.model.Rate;

public class MockRateDao implements RateDao {

	public List<Rate> findRatesByProductId(Integer productId) {
		List<Rate> rates = new ArrayList<Rate>();
		rates.add(new Rate(0, 50, BigDecimal.valueOf(1111)));
		rates.add(new Rate(0, 99, BigDecimal.valueOf(2222)));
		return rates;
	}
	
	

}
