package enh.db.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class AV_2272_Verify_Pushback_Activities {

	
	public static HashSet <String> set_scheduledDepartureOverAllFound= new HashSet<String>();
	public static HashSet <String> set_scheduledDepartureNarrowbody = new HashSet<String>();
	public static HashSet <String> set_scheduledDepartureWidebody = new HashSet<String>();
	public static HashSet <String> set_scheduledDepartureBombardier = new HashSet<String>();
	public static HashSet <String> set_scheduledDepartureUnknown = new HashSet<String>();
	
	public static HashSet <String> set_actuallyDepartureOverAllFound= new HashSet<String>();
	public static HashSet <String> set_actuallyDepartedNarrowbody= new HashSet<String>();
	public static HashSet <String> set_actuallyDepartedWidebody= new HashSet<String>();
	public static HashSet <String> set_actuallyDepartedBombardier= new HashSet<String>();
	public static HashSet <String> set_actuallyDepartedUnknown= new HashSet<String>();
	
	
	public static HashSet <String> set_PushbackFormedForNarrowbody= new HashSet<String>();
	public static HashSet <String> set_PushbackAndPBTFormedForNarrowbody= new HashSet<String>();
	public static HashSet <String> set_PBTFormedForNarrowbody = new HashSet<String>();
	public static HashSet <String> set_PushbackFormedButPBTNotFormedForNarrowbody= new HashSet<String>();
	public static HashSet <String> set_PBTFormedButPushbackNotFormedForNarrowbody= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTNotFormedForNarrowbody= new HashSet<String>();
	public static HashSet <String> set_BothPushbackAndPBTFormedForNarrowbody= new HashSet<String>();
	
	
	public static void VerifyPushbackActivities_for_Hyd(int operationunit, String environment) throws SQLException{
	
		ResultSet result = DBWrapper.Connect("SELECT * FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
			"date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and FlightNumber_Arrival regexp 'TK|EY|CX|QR|ET|AK|KC|JL|KE|PS|AC|AF|AZ|BA|SU|IA|SV|UL|T5|FZ|KQ|I5'",environment);
			while (result.next())
			{	
				String str_gmrPK = result.getString("gmrpk");
				String str_flightHexCode= result.getString("aircraft");	
				String str_flightRegNo = result.getString("AircraftRegistration");
				set_scheduledDepartureUnknown.add(str_gmrPK);

			ResultSet result2 = DBWrapper.Connect("SELECT * FROM `FlightMaster` where hexcode = '"+str_flightHexCode+"' or regno= '"+str_flightRegNo+"'",environment);
					
			while (result2.next())
			{
				String str_flightBodyType= result2.getString("BodyType");
				//String str_flightHexCode2= result2.getString("hexcode");
				set_scheduledDepartureOverAllFound.add(str_gmrPK);
				
				if(str_flightBodyType.equalsIgnoreCase("Narrow"))
				{
					set_scheduledDepartureNarrowbody.add(str_gmrPK);
				}
				if(str_flightBodyType.equalsIgnoreCase("Wide"))
				{
					set_scheduledDepartureWidebody.add(str_gmrPK);
				}
				if(str_flightBodyType.equalsIgnoreCase("Bombardier") || (str_flightBodyType.equalsIgnoreCase("Turboprop")))
				{
					set_scheduledDepartureBombardier.add(str_gmrPK);
				}
			}
			}
			set_scheduledDepartureUnknown.removeAll(set_scheduledDepartureOverAllFound);
			System.out.println("set_scheduledDepartureNarrowbody" +set_scheduledDepartureNarrowbody);
			System.out.println("set_scheduledDepartureWidebody" +set_scheduledDepartureWidebody);
			System.out.println("set_scheduledDepartureBombardier" +set_scheduledDepartureBombardier);
			System.out.println("set_scheduledDepartureUnknown" +set_scheduledDepartureUnknown);
			
			ResultSet result3 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where\r\n" + 
					"date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+")",environment);
			while (result3.next())
			{	
				String str_logId = result3.getString("logid");
				String str_flightHexCode= result3.getString("aircraft");
				String str_flightRegNo= result3.getString("AircraftRegistration_Departure");
				set_actuallyDepartedUnknown.add(str_logId);	
				
			ResultSet result4 = DBWrapper.Connect("SELECT * FROM `FlightMaster` where hexcode = '"+str_flightHexCode+"' or regno= '"+str_flightRegNo+"'",environment);
			while (result4.next())
			{
				String str_flightBodyType= result4.getString("BodyType");
				//String str_flightHexCode2= result4.getString("hexcode");
				set_actuallyDepartureOverAllFound.add(str_logId);
				
				if(str_flightBodyType.equalsIgnoreCase("Narrow"))
				{
					set_actuallyDepartedNarrowbody.add(str_logId);
					set_BothPushbackAndPBTNotFormedForNarrowbody.add(str_logId);
					
				}
				if(str_flightBodyType.equalsIgnoreCase("Wide"))
				{
					set_actuallyDepartedWidebody.add(str_logId);
				}
				if(str_flightBodyType.equalsIgnoreCase("Bombardier") || (str_flightBodyType.equalsIgnoreCase("Turboprop")))
				{
					set_actuallyDepartedBombardier.add(str_logId);
				}
			}
			}
			set_actuallyDepartedUnknown.removeAll(set_actuallyDepartureOverAllFound);
			
			System.out.println("set_actuallyDepartedNarrowbody" +set_actuallyDepartedNarrowbody);
			System.out.println("set_actuallyDepartedWidebody" +set_actuallyDepartedWidebody);
			System.out.println("set_actuallyDepartedBombardier" +set_actuallyDepartedBombardier);
			System.out.println("set_actuallyDepartedUnknown" +set_actuallyDepartedUnknown);
			
			if(set_actuallyDepartedNarrowbody.size()>0)
			{
				for(String actuallyDepartedNarrowbody: set_actuallyDepartedNarrowbody)
				{
				ResultSet result5 = DBWrapper.Connect("SELECT * FROM `EquipActivityLogs` where flight_pk = "+actuallyDepartedNarrowbody+" and operationname in ('pushback', 'pbt')", environment);
				while (result5.next())
				{
					String str_flight_pk = result5.getString("flight_pk");
					String str_operationName = result5.getString("operationName");		
					if(str_operationName.contains("PushBack"))
					{
						set_PushbackFormedForNarrowbody.add(str_flight_pk);						
						set_PushbackFormedButPBTNotFormedForNarrowbody.add(str_flight_pk);
						set_BothPushbackAndPBTFormedForNarrowbody.add(str_flight_pk);
					}
					if(str_operationName.contains("PBT"))
					{
						set_PBTFormedForNarrowbody.add(str_flight_pk);
						set_PBTFormedButPushbackNotFormedForNarrowbody.add(str_flight_pk);
					}
				}
				}
			}
			System.out.println("set_PushbackFormedForNarrowbody" +set_PushbackFormedForNarrowbody);
			System.out.println("set_PBTFormedForNarrowbody" +set_PBTFormedForNarrowbody);
			
			set_PushbackFormedButPBTNotFormedForNarrowbody.removeAll(set_PBTFormedForNarrowbody);
			set_PBTFormedButPushbackNotFormedForNarrowbody.removeAll(set_PushbackFormedForNarrowbody);
			
			set_BothPushbackAndPBTFormedForNarrowbody.retainAll(set_PBTFormedForNarrowbody);
			
			set_BothPushbackAndPBTNotFormedForNarrowbody.removeAll(set_BothPushbackAndPBTFormedForNarrowbody);					
			
			
			System.out.println("set_PushbackFormedButPBTNotFormedForNarrowbody" +set_PushbackFormedButPBTNotFormedForNarrowbody);
			System.out.println("set_PBTFormedButPushbackNotFormedForNarrowbody" +set_PBTFormedButPushbackNotFormedForNarrowbody);
			System.out.println("pushback and PBT both formed: " +set_BothPushbackAndPBTFormedForNarrowbody);
			System.out.println("set_BothPushbackAndPBTNotFormedForNarrowbody" +set_BothPushbackAndPBTNotFormedForNarrowbody);
			
			
			DBWrapper.dbConnectionClose();
	}
}
