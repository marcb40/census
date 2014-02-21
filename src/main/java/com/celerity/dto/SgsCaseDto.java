package com.celerity.dto;

public class SgsCaseDto extends Basedto {

	private String caseName;

	private PreliminaryCensusDto preliminaryCensus;

	public SgsCaseDto() {
		super();
	}

	public SgsCaseDto(Long id) {
		super(id);
	}

	public SgsCaseDto(Long id, String caseName, PreliminaryCensusDto preliminaryCensus) {
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

	public PreliminaryCensusDto getPreliminaryCensus() {
		return preliminaryCensus;
	}

	public void setPreliminaryCensus(PreliminaryCensusDto preliminaryCensus) {
		preliminaryCensus.setSgsCaseId(getId());
		this.preliminaryCensus = preliminaryCensus;
	}
}
