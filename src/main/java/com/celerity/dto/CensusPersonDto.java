package com.celerity.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.celerity.service.rest.adapters.BooleanAdapter;
import com.celerity.service.rest.adapters.DateAdapter;

public class CensusPersonDto extends BaseDto {

	public enum Gender {
		F, M
	}

	public enum EmploymentStatus {
		ACTIVE, COBRA, DISABLED
	}

	public CensusPersonDto() {
		super();
	}

	public CensusPersonDto(Long id) {
		super(id);
	}

	public CensusPersonDto(int id, Date birthDate, Gender gender, String lastName, String firstName, String dependentType, Boolean outOfArea, EmploymentStatus employmentStatus, Boolean usesTobacco, String lastTobaccoUse) {
		super(Long.valueOf(id));
		this.birthDate = birthDate;
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dependentType = dependentType;
		this.outOfArea = outOfArea;
		this.employmentStatus = employmentStatus;
		this.usesTobacco = usesTobacco;
		this.lastTobaccoUse = lastTobaccoUse;
	}

	public CensusPersonDto(Long id, Date birthDate, Gender gender, String lastName, String firstName, String dependentType, Boolean outOfArea, EmploymentStatus employmentStatus, Boolean usesTobacco, String lastTobaccoUse) {
		super(id);
		this.birthDate = birthDate;
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dependentType = dependentType;
		this.outOfArea = outOfArea;
		this.employmentStatus = employmentStatus;
		this.usesTobacco = usesTobacco;
		this.lastTobaccoUse = lastTobaccoUse;
	}

	@XmlElement(name = "birthDate")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date birthDate;

	private Gender gender;

	private String lastName;

	private String firstName;

	private Long sgsCaseId;

	private String dependentType;

	private Long enrolleeId;

	@XmlElement(name = "outOfArea")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean outOfArea;

	private EmploymentStatus employmentStatus;

	@XmlElement(name = "usesTobacco")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean usesTobacco;

	private String lastTobaccoUse;

	private Set<CensusPersonDto> enrolleeDependents = new HashSet<CensusPersonDto>(0);

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDependentType() {
		return dependentType;
	}

	public void setDependentType(String dependentType) {
		this.dependentType = dependentType;
	}

	public Set<CensusPersonDto> getEnrolleeDependents() {
		return enrolleeDependents;
	}

	public void setEnrolleeDependents(Set<CensusPersonDto> enrolleeDependents) {
		this.enrolleeDependents = enrolleeDependents;
	}

	public Long getSgsCaseId() {
		return sgsCaseId;
	}

	public void setSgsCaseId(Long sgsCaseId) {
		this.sgsCaseId = sgsCaseId;
	}

	public Long getEnrolleeId() {
		return enrolleeId;
	}

	public void setEnrolleeId(Long enrolleeId) {
		this.enrolleeId = enrolleeId;
	}

	public Boolean getOutOfArea() {
		return outOfArea;
	}

	public void setOutOfArea(Boolean outOfArea) {
		this.outOfArea = outOfArea;
	}

	public Boolean getUsesTobacco() {
		return usesTobacco;
	}

	public void setUsesTobacco(Boolean usesTobacco) {
		this.usesTobacco = usesTobacco;
	}

	public String getLastTobaccoUse() {
		return lastTobaccoUse;
	}

	public void setLastTobaccoUse(String lastTobaccoUse) {
		this.lastTobaccoUse = lastTobaccoUse;
	}

	public EmploymentStatus getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(EmploymentStatus employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public void addEnrolleeDependent(CensusPersonDto enrolleeDependent) {
		if (enrolleeDependents == null)
			enrolleeDependents = new HashSet<CensusPersonDto>();
		enrolleeDependent.setEnrolleeId(getId());
		enrolleeDependents.add(enrolleeDependent);
	}

	public void updateEnrolleeDependent(CensusPersonDto person) {
		if (this.enrolleeDependents == null) {
			addEnrolleeDependent(person);
		} else {
			for (CensusPersonDto p : enrolleeDependents) {
				if (person.getId().equals(p.getId())) {
					enrolleeDependents.remove(p);
					addEnrolleeDependent(person);
					break;
				}
			}
		}
	}

	public void deleteEnrolleeDependent(CensusPersonDto person) {
		if (this.enrolleeDependents != null) {
			for (CensusPersonDto p : enrolleeDependents) {
				if (person.getId().equals(p.getId())) {
					enrolleeDependents.remove(p);
					break;
				}
			}
		}
	}

	@Override
	public String output() {
		return firstName;
	}
	
	

}
