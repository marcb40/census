package com.celerity.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.celerity.service.DateAdapter;

public class CensusPerson extends BaseModel {

	public enum Gender {
	    F, M
	}

	public CensusPerson() {
		super();
	}

	public CensusPerson(Long id) {
		super(id);
	}
	
	public CensusPerson(int id, Date birthDate, Gender gender, String lastName, String firstName, String dependentType) {
		super(Long.valueOf(id));
		this.birthDate = birthDate;
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dependentType = dependentType;
	}

	public CensusPerson(Long id, Date birthDate, Gender gender, String lastName, String firstName, String dependentType) {
		super(id);
		this.birthDate = birthDate;
		this.gender = gender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dependentType = dependentType;
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

	private Set<CensusPerson> enrolleeDependents = new HashSet<CensusPerson>(0);

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

	public Set<CensusPerson> getEnrolleeDependents() {
		return enrolleeDependents;
	}

	public void setEnrolleeDependents(Set<CensusPerson> enrolleeDependents) {
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

	public void addEnrolleeDependent(CensusPerson enrolleeDependent) {
		if (enrolleeDependents == null) enrolleeDependents = new HashSet<CensusPerson>();
		enrolleeDependent.setEnrolleeId(getId());
		enrolleeDependents.add(enrolleeDependent);
	}
	
	public void updateEnrolleeDependent(CensusPerson person) {
		if (this.enrolleeDependents == null) {
			addEnrolleeDependent(person);
		} else {
			for (CensusPerson p : enrolleeDependents) {
				if (person.getId().equals(p.getId())) {
					enrolleeDependents.remove(p);
					addEnrolleeDependent(person);
					break;
				}
			}
		}
	}
	
	public void deleteEnrolleeDependent(CensusPerson person) {
		if (this.enrolleeDependents != null) {
			for (CensusPerson p : enrolleeDependents) {
				if (person.getId().equals(p.getId())) {
					enrolleeDependents.remove(p);
					break;
				}
			}
		}
	}

	
	
}
