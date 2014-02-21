package com.celerity.service.rest;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.CensusPersonDto.EmploymentStatus;
import com.celerity.dto.CensusPersonDto.Gender;
import com.celerity.dto.PreliminaryCensusDto;
import com.celerity.dto.RateDto;

public class TestCaseService {

	
	@Test
	public void testGetCensus() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://localhost:8080/census/rest/json/case/census/1");
		PreliminaryCensusDto census = restTemplate.getForObject(uri, PreliminaryCensusDto.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		Assert.assertTrue(census.getPopulation().size() >= 1);
	}
	
	@Test
	public void testEnrolleeRate() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		URI enrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/3/rate");
		RateDto rate = restTemplate.getForObject(enrolleeURI, RateDto.class);
		Assert.assertNotNull( rate);
		Assert.assertNotNull( rate.getPersonId());
		Assert.assertEquals(Long.valueOf(3), rate.getPersonId());
		Assert.assertEquals(BigDecimal.valueOf(40), rate.getRate());
	}
	
	@Test
	public void testAddDependent() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		URI enrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/3");
		CensusPersonDto enrollee = restTemplate.getForObject(enrolleeURI, CensusPersonDto.class);
		Assert.assertNotNull(enrollee);
		int initialDependents = enrollee.getEnrolleeDependents().size();
		
		URI dependentURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/3/dependent/0");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CensusPersonDto> request = new HttpEntity<CensusPersonDto>( new CensusPersonDto(null,null,Gender.M, "Johnson", "Jim", "Child", false, EmploymentStatus.ACTIVE, true, "08/2012"), headers);
		CensusPersonDto dependent = restTemplate.postForObject(dependentURI, request, CensusPersonDto.class);
		Assert.assertNotNull(dependent);
		enrollee = restTemplate.getForObject(enrolleeURI, CensusPersonDto.class);
		Assert.assertNotNull(enrollee);
		Assert.assertEquals(initialDependents + 1, enrollee.getEnrolleeDependents().size());
		
	}
	
	@Test
	public void testAddEnrollee() throws Exception {
		//check initial census
		RestTemplate restTemplate = new RestTemplate();
		URI censusURI = new URI("http://localhost:8080/census/rest/json/case/census/1");
		PreliminaryCensusDto census = restTemplate.getForObject(censusURI, PreliminaryCensusDto.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		int initialCensusSize = census.getPopulation().size();
		
		//add enrollee
		URI addEnrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/0");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CensusPersonDto> request = new HttpEntity<CensusPersonDto>( new CensusPersonDto(null,null,Gender.M, "Smith", "Bill", null, false, EmploymentStatus.ACTIVE, true, "08/2012"), headers);
		CensusPersonDto enrollee = restTemplate.postForObject(addEnrolleeURI, request, CensusPersonDto.class);
		Assert.assertNotNull(enrollee);
		
		//get enrollee
		URI getEnrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/" + enrollee.getId());
		enrollee = restTemplate.getForObject(getEnrolleeURI, CensusPersonDto.class);
		Assert.assertNotNull(enrollee);
		Assert.assertEquals("Bill", enrollee.getFirstName());
		
		//check census again
		census = restTemplate.getForObject(censusURI, PreliminaryCensusDto.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		Assert.assertEquals(initialCensusSize + 1, census.getPopulation().size());
	}
}
