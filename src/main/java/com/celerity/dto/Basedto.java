package com.celerity.dto;

public abstract class BaseDto {

	private Long id;
	

	public BaseDto() {
		super();
	}

	public BaseDto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
