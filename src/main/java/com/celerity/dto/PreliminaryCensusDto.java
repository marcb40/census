package com.celerity.dto;

import java.util.ArrayList;
import java.util.List;

public class PreliminaryCensusDto extends BaseDto {

	 private Long sgsCaseId;

	 private List<CensusPersonDto> population = new ArrayList<CensusPersonDto>();
	 


	public PreliminaryCensusDto() {
		super();
	}

	public PreliminaryCensusDto(Long id) {
		super(id);
	}
	
	public Long getSgsCaseId() {
		return sgsCaseId;
	}

	public void setSgsCaseId(Long sgsCaseId) {
		this.sgsCaseId = sgsCaseId;
	}

	public List<CensusPersonDto> getPopulation() {
		return population;
	}

	public void addPopulation(CensusPersonDto person) {
		if (this.population == null) population = new ArrayList<CensusPersonDto>();
		person.setSgsCaseId(getSgsCaseId());
		population.add(person);
	}
	
	public void updatePopulation(CensusPersonDto person) {
		if (this.population == null) {
			addPopulation(person);
		} else {
			for (CensusPersonDto p : population) {
				if (person.getId().equals(p.getId())) {
					population.remove(p);
					addPopulation(person);
					break;
				}
			}
		}
	}
	
	public void deleteFromPopulation(CensusPersonDto person) {
		if (this.population != null) {
			for (CensusPersonDto p : population) {
				if (person.getId().equals(p.getId())) {
					population.remove(p);
					break;
				}
			}
		}
	}

}
