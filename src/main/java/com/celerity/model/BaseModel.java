package com.celerity.model;

public abstract class BaseModel {

	private Long id;
	

	public BaseModel() {
		super();
	}

	public BaseModel(Long id) {
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
