package com.celerity.model;

import java.util.ArrayList;
import java.util.List;

public class PreliminaryCensus extends BaseModel {

	 private Long sgsCaseId;

	 private List<CensusPerson> population = new ArrayList<CensusPerson>();
	 


	public PreliminaryCensus() {
		super();
	}

	public PreliminaryCensus(Long id) {
		super(id);
	}
	
	public Long getSgsCaseId() {
		return sgsCaseId;
	}

	public void setSgsCaseId(Long sgsCaseId) {
		this.sgsCaseId = sgsCaseId;
	}

	public List<CensusPerson> getPopulation() {
		return population;
	}

	public void addPopulation(CensusPerson person) {
		if (this.population == null) population = new ArrayList<CensusPerson>();
		person.setSgsCaseId(getSgsCaseId());
		population.add(person);
	}
	
	public void updatePopulation(CensusPerson person) {
		if (this.population == null) {
			addPopulation(person);
		} else {
			for (CensusPerson p : population) {
				if (person.getId().equals(p.getId())) {
					population.remove(p);
					addPopulation(person);
					break;
				}
			}
		}
		
		
	}

	
	 
	 
}
