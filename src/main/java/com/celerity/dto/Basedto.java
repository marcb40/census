package com.celerity.dto;

public abstract class Basedto {

	private Long id;
	

	public Basedto() {
		super();
	}

	public Basedto(Long id) {
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
