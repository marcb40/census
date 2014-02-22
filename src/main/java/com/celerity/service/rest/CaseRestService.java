package com.celerity.service.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.celerity.data.DataSource;
import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.PreliminaryCensusDto;
import com.celerity.dto.RateDto;
import com.celerity.dto.SgsCaseDto;
import com.celerity.service.api.RateService;
import com.celerity.service.rest.adapters.DateAdapter;

@Path("/json/case")
public class CaseRestService {

	private RateService rateService;
	
	@GET
	@Path("census/{caseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PreliminaryCensusDto getCensus(@PathParam("caseId") Long caseId) {
		SgsCaseDto sgsCase = DataSource.cases.get(caseId);
		
		return sgsCase.getPreliminaryCensus();
	}

	@POST
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPersonDto addEnrollee(@PathParam("caseId") Long caseId, CensusPersonDto enrollee) {
		SgsCaseDto sgsCase = DataSource.cases.get(caseId);
		enrollee.setId(Long.valueOf(DataSource.enrollee_seq++));
		sgsCase.getPreliminaryCensus().addPopulation(enrollee);
		DataSource.enrollees.put(enrollee.getId(), enrollee);

		return enrollee;
	}
	
	@PUT
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPersonDto updateEnrollee(@PathParam("caseId") Long caseId, CensusPersonDto enrollee) {
		SgsCaseDto sgsCase = DataSource.cases.get(caseId);
		sgsCase.getPreliminaryCensus().updatePopulation(enrollee);
		DataSource.enrollees.put(enrollee.getId(), enrollee);

		return enrollee;
	}

	@GET
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CensusPersonDto getEnrollee(@PathParam("caseId") Long caseId, @PathParam("enrolleeId") Long enrolleeId) {
		return DataSource.enrollees.get(enrolleeId);
	}
	
	@GET
	@Path("census/{caseId}/enrollee/{enrolleeId}/rate")
	@Produces(MediaType.APPLICATION_JSON)
	public RateDto getEnrolleeRate(@PathParam("caseId") Long caseId, @PathParam("enrolleeId") Long enrolleeId) {
		CensusPersonDto person = DataSource.enrollees.get(enrolleeId);
		return rateService.getRate(person);
	}
	
	@DELETE
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	public void deleteEnrollee(@PathParam("caseId") Long caseId, @PathParam("enrolleeId") Long enrolleeId) {
		SgsCaseDto sgsCase = DataSource.cases.get(caseId);
		CensusPersonDto enrollee = DataSource.enrollees.get(enrolleeId);
		sgsCase.getPreliminaryCensus().deleteFromPopulation(enrollee);
		 DataSource.enrollees.remove(enrolleeId);
		
	}

	@POST
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPersonDto addDependent(@PathParam("enrolleeId") Long enrolleeId, CensusPersonDto dependent) {
		CensusPersonDto enrollee = DataSource.enrollees.get(enrolleeId);
		dependent.setId(Long.valueOf(DataSource.dependent_seq++));
		enrollee.addEnrolleeDependent(dependent);
		return dependent;
	}
	
	@PUT
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPersonDto updateDependent(@PathParam("enrolleeId") Long enrolleeId, CensusPersonDto dependent) {
		CensusPersonDto enrollee = DataSource.enrollees.get(enrolleeId);
		enrollee.updateEnrolleeDependent(dependent);
		return dependent;
	}

	@GET
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CensusPersonDto getDependent(@PathParam("enrolleeId") Long enrolleeId, @PathParam("dependentId") Long dependentId) {
		CensusPersonDto enrollee = DataSource.enrollees.get(enrolleeId);
		for (CensusPersonDto d : enrollee.getEnrolleeDependents()) {
			if (d.getId().equals(dependentId)) {
				return d;
			}
		}
		return null;
	}
	
	@DELETE
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	public void deleteDependent(@PathParam("enrolleeId") Long enrolleeId, @PathParam("dependentId") Long dependentId) {
		CensusPersonDto enrollee = DataSource.enrollees.get(enrolleeId);
		
		CensusPersonDto dependent = getDependent(enrolleeId, dependentId);
		enrollee.deleteEnrolleeDependent(dependent);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateAdapter.inDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DateAdapter.inDateFormat, false));
	}

	public RateService getRateService() {
		return rateService;
	}

	@Required
	public void setRateService(RateService rateService) {
		this.rateService = rateService;
	}
	
	

}
