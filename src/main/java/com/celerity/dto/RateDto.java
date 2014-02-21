package com.celerity.dto;

import java.math.BigDecimal;

public class RateDto {

	
	private Long personId;
	
	private BigDecimal rate;
	
	
	public RateDto(Long personId, BigDecimal rate) {
		super();
		this.personId = personId;
		this.rate = rate;
	}

	public RateDto() {
		super();
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
}
