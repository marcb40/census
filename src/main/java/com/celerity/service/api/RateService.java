package com.celerity.service.api;

import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.RateDto;

public interface RateService {

	
	RateDto getRate(CensusPersonDto person);
}
