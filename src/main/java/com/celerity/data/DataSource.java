package com.celerity.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.celerity.model.CensusPerson;
import com.celerity.model.CensusPerson.EmploymentStatus;
import com.celerity.model.CensusPerson.Gender;
import com.celerity.model.PreliminaryCensus;
import com.celerity.model.SgsCase;

public class DataSource {
	public static Map<Long, SgsCase> cases = new HashMap<Long, SgsCase>();
	public static Map<Long, CensusPerson> enrollees = new HashMap<Long, CensusPerson>();
	
	public static int enrollee_seq = 1;
	public static int dependent_seq = 1;
	static {
		cases.put(Long.valueOf(1), buildCase(Long.valueOf(1), "My Case", 1));
		cases.put(Long.valueOf(2), buildCase(Long.valueOf(2), "Big Case", 7));
	}
	
	private static SgsCase buildCase(Long id, String caseName, int censusPeople) {
		PreliminaryCensus pc = new PreliminaryCensus(id);
		for (int i=1;i<=censusPeople;i++) {
			CensusPerson p = new CensusPerson(enrollee_seq++, toDate(1978, 9, i), i % 2 == 0 ? Gender.M : Gender.F, "Last"+i, "First"+i, null, false, EmploymentStatus.ACTIVE, true, "08/2012");
			enrollees.put(p.getId(), p);
			if (i % 3 == 0) {
				CensusPerson d1 = new CensusPerson(dependent_seq++, toDate(1979, 8, i), i % 2 == 0 ? Gender.F : Gender.M, "Lspouse"+i, "Fppouse"+i, "Spouse", true, null, false, null);
				CensusPerson d2 = new CensusPerson(dependent_seq++, toDate(1999, 6, i), i % 2 == 0 ? Gender.F : Gender.M, "Lchild"+i, "Fchild"+i, "Child", false, null, false, null);
				p.addEnrolleeDependent(d1);
				p.addEnrolleeDependent(d2);
			} else if (i % 2 == 0) {
				CensusPerson d1 = new CensusPerson(dependent_seq++, toDate(1979, 8, i), i % 2 == 0 ? Gender.F : Gender.M, "Lspouse"+i, "Fppouse"+i, "Spouse", true, null, false, null);
				p.addEnrolleeDependent(d1);
			}
			
			pc.addPopulation(p);
		}
		
		SgsCase sgsCase = new SgsCase(id, caseName, pc);
		return sgsCase;
	}
	
	private static Date toDate(int year, int month, int day) {
		Calendar d = Calendar.getInstance();
		d.set(year, month, day);
		return d.getTime();
	}
}
