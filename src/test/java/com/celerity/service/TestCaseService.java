package com.celerity.service;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.celerity.model.CensusPerson;
import com.celerity.model.PreliminaryCensus;
import com.celerity.model.CensusPerson.Gender;

public class TestCaseService {

	
	@Test
	public void testGetCensus() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://localhost:8080/census/rest/json/case/census/1");
		PreliminaryCensus census = restTemplate.getForObject(uri, PreliminaryCensus.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		Assert.assertTrue(census.getPopulation().size() >= 7);
	}
	
	@Test
	public void testAddDependent() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		URI enrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/3");
		CensusPerson enrollee = restTemplate.getForObject(enrolleeURI, CensusPerson.class);
		Assert.assertNotNull(enrollee);
		int initialDependents = enrollee.getEnrolleeDependents().size();
		
		URI dependentURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/3/dependent/0");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CensusPerson> request = new HttpEntity<CensusPerson>( new CensusPerson(null,null,Gender.M, "Johnson", "Jim", "Child"), headers);
		Long dependentId = restTemplate.postForObject(dependentURI, request, Long.class);
		Assert.assertNotNull(dependentId);
		enrollee = restTemplate.getForObject(enrolleeURI, CensusPerson.class);
		Assert.assertNotNull(enrollee);
		Assert.assertEquals(initialDependents + 1, enrollee.getEnrolleeDependents().size());
		
	}
	
	@Test
	public void testAddEnrollee() throws Exception {
		//check initial census
		RestTemplate restTemplate = new RestTemplate();
		URI censusURI = new URI("http://localhost:8080/census/rest/json/case/census/1");
		PreliminaryCensus census = restTemplate.getForObject(censusURI, PreliminaryCensus.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		int initialCensusSize = census.getPopulation().size();
		
		//add enrollee
		URI addEnrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/0");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CensusPerson> request = new HttpEntity<CensusPerson>( new CensusPerson(null,null,Gender.M, "Smith", "Bill", null), headers);
		Long enrolleeId = restTemplate.postForObject(addEnrolleeURI, request, Long.class);
		Assert.assertNotNull(enrolleeId);
		
		//get enrollee
		URI getEnrolleeURI = new URI("http://localhost:8080/census/rest/json/case/census/1/enrollee/" + enrolleeId);
		CensusPerson enrollee = restTemplate.getForObject(getEnrolleeURI, CensusPerson.class);
		Assert.assertNotNull(enrollee);
		Assert.assertEquals("Bill", enrollee.getFirstName());
		
		//check census again
		census = restTemplate.getForObject(censusURI, PreliminaryCensus.class);
		Assert.assertNotNull(census);
		Assert.assertNotNull(census.getPopulation());
		Assert.assertEquals(initialCensusSize + 1, census.getPopulation().size());
	}
}
