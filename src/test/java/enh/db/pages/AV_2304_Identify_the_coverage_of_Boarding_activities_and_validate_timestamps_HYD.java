package enh.db.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;


public class AV_2304_Identify_the_coverage_of_Boarding_activities_and_validate_timestamps_HYD {
	public static String TotalFlightsDeparted =null;
	public static ArrayList<String> list_TotalNumberofPassengerCoaches= new ArrayList<String>();
	public static ArrayList<String> list_TotalNumberofPassengerCoachesUtilizedforBoarding = new ArrayList<String>();
	public static ArrayList<String> list_flightswithalltheBoardingactivities  = new ArrayList<String>();
	
	@Test
	public static void hYD_AV2304_Identify_the_coverage_of_Boarding_activities_and_validate_timestamps(int operationUnit, String deviceID, String environment) throws SQLException {
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where"
				+ " gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationUnit+")",environment);
		while (result.next())
		{				
		 TotalFlightsDeparted = result.getString("count(*)");
		}	
		System.out.println(TotalFlightsDeparted);
		
		ResultSet result1 = DBWrapper.Connect("SELECT distinct(devid) FROM `HealthMonitor` where date(logdate) = '"+SQL_Queries.yesterDate()+"' and devid like '%"+deviceID+"%'",environment);//rgi_ACOA
		while (result1.next())
		{				
			String	TotalNumberofPassengerCoaches = result1.getString("distinct(devid)");
			list_TotalNumberofPassengerCoaches.add(TotalNumberofPassengerCoaches);
		}	
		System.out.println(list_TotalNumberofPassengerCoaches.size());
		
		ResultSet result2 = DBWrapper.Connect("SELECT devid FROM `EquipActivityLogs` where date(flogdate) = '"+SQL_Queries.yesterDate()+"' and devid like  '%"+deviceID+"%' and assigned=1 and operationname ='pcb' group by devid",environment);
		while (result2.next())
		{				
			String	TotalNumberofPassengerCoaches = result2.getString("distinct(devid)");
			list_TotalNumberofPassengerCoachesUtilizedforBoarding.add(TotalNumberofPassengerCoaches);	
		}	
		
		System.out.println(list_TotalNumberofPassengerCoachesUtilizedforBoarding.size());		
		
		ResultSet result3 = DBWrapper.Connect("SELECT flight_pk FROM `EquipActivityLogs` "
				+ "where date(flogdate) = '"+SQL_Queries.yesterDate()+"' and devid like '%"+deviceID+"%' and assigned=1 and operationname in ('pcb', 'pcba', 'pcg') group by flight_pk",environment);
				while (result3.next())
		{				
			String	flightPk = result3.getString("flight_pk");
			list_flightswithalltheBoardingactivities.add(flightPk);	
		}	
		
		System.out.println(list_flightswithalltheBoardingactivities.size());		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		

}
