package com.celerity.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.celerity.data.DataSource;
import com.celerity.model.CensusPerson;
import com.celerity.model.PreliminaryCensus;
import com.celerity.model.SgsCase;

@Path("/json/case")
public class CaseService {

	@GET
	@Path("census/{caseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PreliminaryCensus getCensus(@PathParam("caseId") Long caseId) {
		SgsCase sgsCase = DataSource.cases.get(caseId);
		return sgsCase.getPreliminaryCensus();
	}

	@POST
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPerson addEnrollee(@PathParam("caseId") Long caseId, CensusPerson enrollee) {
		SgsCase sgsCase = DataSource.cases.get(caseId);
		enrollee.setId(Long.valueOf(DataSource.enrollee_seq++));
		sgsCase.getPreliminaryCensus().addPopulation(enrollee);
		DataSource.enrollees.put(enrollee.getId(), enrollee);

		return enrollee;
	}
	
	@PUT
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPerson updateEnrollee(@PathParam("caseId") Long caseId, CensusPerson enrollee) {
		SgsCase sgsCase = DataSource.cases.get(caseId);
		sgsCase.getPreliminaryCensus().updatePopulation(enrollee);
		DataSource.enrollees.put(enrollee.getId(), enrollee);

		return enrollee;
	}

	@GET
	@Path("census/{caseId}/enrollee/{enrolleeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CensusPerson getEnrollee(@PathParam("enrolleeId") Long enrolleeId) {
		return DataSource.enrollees.get(enrolleeId);
	}

	@POST
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CensusPerson addDependent(@PathParam("enrolleeId") Long enrolleeId, CensusPerson dependent) {
		CensusPerson enrollee = DataSource.enrollees.get(enrolleeId);
		dependent.setId(Long.valueOf(DataSource.dependent_seq++));
		enrollee.addEnrolleeDependent(dependent);
		return dependent;
	}

	@GET
	@Path("census/{caseId}/enrollee/{enrolleeId}/dependent/{dependentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CensusPerson getDependent(@PathParam("enrolleeId") Long enrolleeId, @PathParam("dependentId") Long dependentId) {
		CensusPerson enrollee = DataSource.enrollees.get(enrolleeId);
		for (CensusPerson d : enrollee.getEnrolleeDependents()) {
			if (d.getId().equals(dependentId)) {
				return d;
			}
		}
		return null;
	}

}
