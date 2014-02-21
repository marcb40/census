package com.celerity.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.RateDto;
import com.celerity.service.api.RateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        inheritLocations = true, locations = { "classpath:test-context.xml" })
public class TestRateService {

	@Autowired
	private RateService rateService;
	
	
	@Test
	public void testGetRates() throws Exception {
		CensusPersonDto person = new CensusPersonDto();
		person.setId(1L);
		RateDto rateDto = rateService.getRate(person);
		Assert.assertNotNull(rateDto);
		Assert.assertEquals(BigDecimal.valueOf(1111), rateDto.getRate());
		Assert.assertEquals(Long.valueOf(1), rateDto.getPersonId());
	}
}
