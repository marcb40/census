package com.celerity.model;

public class SgsCase extends BaseModel {

	private String caseName;

	private PreliminaryCensus preliminaryCensus;

	public SgsCase() {
		super();
	}

	public SgsCase(Long id) {
		super(id);
	}

	public SgsCase(Long id, String caseName, PreliminaryCensus preliminaryCensus) {
		super(id);
		this.caseName = caseName;
		preliminaryCensus.setSgsCaseId(getId());
		this.preliminaryCensus = preliminaryCensus;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public PreliminaryCensus getPreliminaryCensus() {
		return preliminaryCensus;
	}

	public void setPreliminaryCensus(PreliminaryCensus preliminaryCensus) {
		preliminaryCensus.setSgsCaseId(getId());
		this.preliminaryCensus = preliminaryCensus;
	}
}
