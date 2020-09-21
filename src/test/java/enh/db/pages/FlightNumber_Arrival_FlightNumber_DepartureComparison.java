package enh.db.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import utilities.HtmlReportUtil;

public class FlightNumber_Arrival_FlightNumber_DepartureComparison {
	public static ArrayList prodList = new ArrayList();
	public static ArrayList testList = new ArrayList();
	
	
	public static void FlightNumber_Arrival_FlightNumber_DepartureProdComparison() throws SQLException {
		//System.out.println("============================1=============================");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT FlightNumber_Arrival, FlightNumber_Departure, Sensor_ATD, Sensor_ATA, Bay, GMRPK_Arrival  FROM `DailyFlightSchedule_Merged`where (date(atd)='2019-11-25' or date(sensor_atd)='2019-11-25') and operationunit = 22 and GMRPK_Arrival is not null");
	 while(rs.next()) {
		 String FlightNumber_Arrival = rs.getString("FlightNumber_Arrival");
		 
		String	FlightNumber_Departure = rs.getString("FlightNumber_Departure");
		 String Sensor_ATD = rs.getString("Sensor_ATD");			
			String	Sensor_ATA = rs.getString("Sensor_ATA");
			 String Bay = rs.getString("Bay");			
				String	GMRPK_Arrival = rs.getString("GMRPK_Arrival");
				prodList.add(GMRPK_Arrival);
		System.out.println(FlightNumber_Arrival+ " - "+ FlightNumber_Departure+ " - "+ Sensor_ATD+ " - "+ Sensor_ATA+ " - "+ Bay+ " - "+ GMRPK_Arrival);
	 }
	 System.out.println(prodList.size() + " - production list size");
	 rs.close();
	 con.close();
	}

	
	public static void FlightNumber_Arrival_FlightNumber_DepartureUatComparison() throws SQLException {
		//System.out.println("==============================2===========================");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT FlightNumber_Arrival, FlightNumber_Departure, Sensor_ATD, Sensor_ATA, Bay, GMRPK_Arrival FROM `DailyFlightSchedule_Merged`where (date(atd)='2019-11-25' or date(sensor_atd)='2019-11-25') and operationunit = 22 and GMRPK_Arrival is not null");
		 while(rs.next()) {
			 String FlightNumber_Arrival = rs.getString("FlightNumber_Arrival");			
			String	FlightNumber_Departure = rs.getString("FlightNumber_Departure");
			 String Sensor_ATD = rs.getString("Sensor_ATD");			
				String	Sensor_ATA = rs.getString("Sensor_ATA");
				 String Bay = rs.getString("Bay");			
					String	GMRPK_Arrival = rs.getString("GMRPK_Arrival");
					testList.add(GMRPK_Arrival);
			System.out.println(FlightNumber_Arrival+ " - "+ FlightNumber_Departure+ " - "+ Sensor_ATD+ " - "+ Sensor_ATA+ " - "+ Bay+ " - "+ GMRPK_Arrival);
		 }
		 System.out.println(testList.size() + " - test list size");
	 rs.close();
	 con.close();
	}
	
   
	public static void flightNumber_Arrival_FlightNumber_DepartureComparison() throws SQLException {
		System.out.println("==============================3===========================");
		FlightNumber_Arrival_FlightNumber_DepartureComparison.FlightNumber_Arrival_FlightNumber_DepartureUatComparison();
		FlightNumber_Arrival_FlightNumber_DepartureComparison.FlightNumber_Arrival_FlightNumber_DepartureProdComparison();
		 Collections.sort(prodList);
	     Collections.sort(testList);
	     System.out.println(prodList.size() + " - production list size");	 		
	     System.out.println(testList.size() + " - test list size");	
	     HtmlReportUtil.stepInfo("<b style=\"color:green;\"> No of GMRPK_Arrival values in Production - "+prodList.size()+" </b>");
			HtmlReportUtil.stepInfo("<b style=\"color:green;\"> No of GMRPK_Arrival values in UAT - "+testList.size()+" </b>");			
	     testList.removeAll(prodList);
	     System.out.println("test - "+testList);
		    System.out.println(prodList.size() + " - production list size");
	     System.out.println(testList.size() + " - test list size");
	     HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No of GMRPK_Arrival values missing in production - "+testList.size()+" </b>");	
			HtmlReportUtil.stepInfo("<b style=\"color:red;\"> Missing GMRPK_Arrival values  "+testList+" </b>");
		
	     testList.removeAll(testList);
	     prodList.removeAll(prodList);
	     System.out.println(prodList.size() + " - production list size");
	     System.out.println(testList.size() + " - test list size");
	 		}
	
	
}
