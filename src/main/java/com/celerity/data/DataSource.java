package com.celerity.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.celerity.dto.CensusPersonDto;
import com.celerity.dto.PreliminaryCensusDto;
import com.celerity.dto.SgsCaseDto;
import com.celerity.dto.CensusPersonDto.EmploymentStatus;
import com.celerity.dto.CensusPersonDto.Gender;

public class DataSource {
	public static Map<Long, SgsCaseDto> cases = new HashMap<Long, SgsCaseDto>();
	public static Map<Long, CensusPersonDto> enrollees = new HashMap<Long, CensusPersonDto>();
	
	public static int enrollee_seq = 1;
	public static int dependent_seq = 1;
	static {
		cases.put(Long.valueOf(1), buildCase(Long.valueOf(1), "My Case", 1));
		cases.put(Long.valueOf(2), buildCase(Long.valueOf(2), "Big Case", 7));
	}
	
	private static SgsCaseDto buildCase(Long id, String caseName, int censusPeople) {
		PreliminaryCensusDto pc = new PreliminaryCensusDto(id);
		for (int i=1;i<=censusPeople;i++) {
			CensusPersonDto p = new CensusPersonDto(enrollee_seq++, toDate(1978, 9, i), i % 2 == 0 ? Gender.M : Gender.F, "Last"+i, "First"+i, null, false, EmploymentStatus.ACTIVE, true, "08/2012");
			enrollees.put(p.getId(), p);
			if (i % 3 == 0) {
				CensusPersonDto d1 = new CensusPersonDto(dependent_seq++, toDate(1979, 8, i), i % 2 == 0 ? Gender.F : Gender.M, "Lspouse"+i, "Fppouse"+i, "Spouse", true, null, false, null);
				CensusPersonDto d2 = new CensusPersonDto(dependent_seq++, toDate(1999, 6, i), i % 2 == 0 ? Gender.F : Gender.M, "Lchild"+i, "Fchild"+i, "Child", false, null, false, null);
				p.addEnrolleeDependent(d1);
				p.addEnrolleeDependent(d2);
			} else if (i % 2 == 0) {
				CensusPersonDto d1 = new CensusPersonDto(dependent_seq++, toDate(1979, 8, i), i % 2 == 0 ? Gender.F : Gender.M, "Lspouse"+i, "Fppouse"+i, "Spouse", true, null, false, null);
				p.addEnrolleeDependent(d1);
			}
			
			pc.addPopulation(p);
		}
		
		SgsCaseDto sgsCase = new SgsCaseDto(id, caseName, pc);
		return sgsCase;
	}
	
	public static Date toDate(int year, int month, int day) {
		Calendar d = Calendar.getInstance();
		d.set(year, month, day);
		return d.getTime();
	}
}
