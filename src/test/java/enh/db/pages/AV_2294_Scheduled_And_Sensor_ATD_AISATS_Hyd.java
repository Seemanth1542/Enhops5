package enh.db.pages;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class AV_2294_Scheduled_And_Sensor_ATD_AISATS_Hyd {
	
	public static int totalScheduledDeparture=0;
	public static int totalActualDeparture=0;
	public static int notNullSensorATD =0;
	
	public static int offBlockFromSensor=0;
	public static int offBlockFromCV =0;
			
	public static ArrayList<String> sensorATD_NullList = new ArrayList<String>();
	public static ArrayList<String> list_offBlockFromOtherDataSource = new ArrayList<String>();
		
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	public static StringBuilder emailbody = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4 = new StringBuilder();
	
		
	public static void scheduled_And_Sensor_ATD_For_AISATS_Hyderabad_Report(int operationunit,String environment) throws Exception {
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
		"date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+" and flightnumber regexp \r\n"
		+ " ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')",environment);
		while (result.next())
		{				
			totalScheduledDeparture = result.getInt("count(*)");
			System.out.println(totalScheduledDeparture);
		}
		ResultSet result1 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where\r\n" + 
				"date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and operationunit= "+operationunit+") and "
						+ "(atd is not null or sensor_atd is not null)",environment);
		while (result1.next())
		{				
			totalActualDeparture = result1.getInt("count(*)");
			System.out.println(totalActualDeparture);
		}
		ResultSet result2 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n " +
		"gmrpk_departure in (SELECT gmrpk FROM `DailyFlightScheduleDeparture_GMR` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' \r\n"
		+ "and flightnumber regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and operationunit= "+operationunit+") \r\n" + 
				"and sensor_atd is not null",environment);
		while (result2.next())
		{				
			notNullSensorATD = result2.getInt("count(*)");
			System.out.println(notNullSensorATD);
		}
		ResultSet result3 = DBWrapper.Connect("SELECT logid, flightnumber_departure, std, etd, atd FROM `DailyFlightSchedule_Merged` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' \r\n"
				+ "and operationunit= "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and sensor_atd is null",environment);
		while (result3.next())
		{
			String str_LogID = result3.getString("logid");
			String str_flight_NumberDeparture= result3.getString("flightnumber_departure");
			String str_flight_STD = result3.getString("std");
			String str_flight_ETD = result3.getString("etd");
			String str_flight_ATD = result3.getString("atd");
			
			sensorATD_NullList.add(str_LogID);
		}
				
		ResultSet result4 = DBWrapper.Connect("SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n "+
		"where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"'and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and off_block_time is not null ) "
		+ "and operationname = 'ofb' and type = 'aircraft'order by flightno",environment);
		while (result4.next())
		{				
			offBlockFromSensor = result4.getInt("count(*)");
			System.out.println(offBlockFromSensor);
		}
		
		ResultSet result5 = DBWrapper.Connect("SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n" + 
		"where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') \r\n"
				+ "and off_block_time is not null ) and operationname = 'ofb' and type = 'cv'order by flightno",environment);
		while (result5.next())
		{				
			String str_Flight_PK = result5.getString("flight_pk");
			String str_flight_NumberDeparture= result5.getString("flightno");
			list_offBlockFromOtherDataSource.add(str_Flight_PK);		
		}
				
		ResultSet result6 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ATD, Off_block_time, (case when (Off_Block_Time < Sensor_ATD) then 1 else 0 end) as Status, \r\n"
		+ "CONCAT('',TIMEDIFF(Off_Block_Time, Sensor_ATD)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' \r\n"
		+ "and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and (sensor_atd is not null and Off_block_time is not null) order by flightnumber_departure",environment);
		while (result6.next())
		{				
			String str_LogID = result6.getString("logid");
			String str_flight_NumberDeparture= result6.getString("flightnumber_departure");
			String str_flight_SensorATD= result6.getString("sensor_atd");
			String str_flight_OffBlock= result6.getString("off_block_time");
			String str_status= result6.getString("status");
			String str_difference_between_OffBlockAndSensorATD= result6.getString("difference");
			
			if (str_status.contains("1"))
			{
				status1List.add(str_LogID);
			}
			if (str_status.contains("0"))
			{
				status0List.add(str_LogID);
			}
			
		}	
		
		ResultSet result7 = DBWrapper.Connect("SELECT count(*) FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n" + 
				"where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY')) \r\n"
						+ "and operationname = 'ofb' and type = 'cv'order by flightno",environment);
				while (result7.next())		
				{				
					offBlockFromCV = result7.getInt("count(*)");	
				}
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\"> User Name : AISATS-Hyderabad</h4>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATD</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append("<h5 align=\"center\" style=\"color:#008ae6;\" >Environment: "+environment+" </h5>");

		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Departure</b></th> <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th><th style=\"width:20%\"><b>No. of flights Off-Block time is detected by Sensor</b></th><th style=\"width:15%\"><b>No. of flights Off-Block time is detected by CV</b></th>"
		 		+ " </tr>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledDeparture+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+notNullSensorATD+"</b></td> <td><b style=\"color:red;\">"+sensorATD_NullList.size()+"</b></td> <td><b style=\"color:green;\">"+offBlockFromSensor+"</b></td> <td><b style=\"color:red;\">"+offBlockFromCV+"</b></td></tr></table>");			 	
		
		
		emailbody.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

		emailbody.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :Scheduled and Sensor-ATD for AISATS-Hyderabad</h4>");


		emailbody.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Departure</b></th> <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th><th style=\"width:20%\"><b>No. of flights Off-Block time is detected by Sensor</b></th><th style=\"width:15%\"><b>No. of flights Off-Block time is detected by CV</b></th>"
		 		+ " </tr>");
		emailbody.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledDeparture+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+notNullSensorATD+"</b></td> <td><b style=\"color:red;\">"+sensorATD_NullList.size()+"</b></td> <td><b style=\"color:green;\">"+offBlockFromSensor+"</b></td> <td><b style=\"color:red;\">"+offBlockFromCV+"</b></td></tr></table>");			 	

		
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights AIRBORNE (Not Detected by Flight Sensor but detected from other data source) </caption><tr><th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Departure Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>STD</b></th>"
		 		+ "<th style=\"width:15%\"><b>ETD</b></th>"
		 		+ "<th style=\"width:20%\"><b>ATD</b></th>"
		 		+ " </tr>");
		if (sensorATD_NullList.size()>0) {
		ResultSet result31 = DBWrapper.Connect("SELECT logid, flightnumber_departure, std, etd, atd FROM `DailyFlightSchedule_Merged` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' \r\n" + 
				"and operationunit= "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and sensor_atd is null",environment);
		while (result31.next())
		{
			String str_LogID = result31.getString("logid");
			String str_flight_NumberDeparture= result31.getString("flightnumber_departure");
			String str_flight_STD = result31.getString("std");
			String str_flight_ETD = result31.getString("etd");
			String str_flight_ATD = result31.getString("atd");
			
			email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flight_NumberDeparture+"</b></td>"
				 		+ " <td> <b style=\"color:red;\">"+str_flight_STD+"</b></td> <td><b style=\"color:red;\">"+str_flight_ETD+"</b></td> <td><b style=\"color:red;\">"+str_flight_ATD+"</b></td></tr>");	
		}
		}
		else{
			email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
			}
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights - OFFBLOCK (Not Detected by Flight Sensor but detected from other data source)</caption><tr>"
				+ "<th style=\"width:10%\"><b>Flight_PK</b></th>"
				+ "<th style=\"width:15%\"><b>Departure Flight No.</b></th> "
				+ " </tr>");
		
		if (list_offBlockFromOtherDataSource.size()>0) {
		ResultSet result51 = DBWrapper.Connect("SELECT flight_pk, flightno FROM `EquipActivityLogs` where flight_pk in (SELECT logid FROM `DailyFlightSchedule_Merged`\r\n" + 
				"where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and off_block_time is not null ) and operationname = 'ofb' and type = 'cv'order by flightno",environment);
		while (result51.next())
		{				
			String str_Flight_PK = result51.getString("flight_pk");
			String str_flight_NumberDeparture= result51.getString("flightno");
			
			email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append(" <tr> <td><b style=\"color:red;\">"+str_Flight_PK+"</b></td> <td><b style=\"color:red;\">"+str_flight_NumberDeparture+"</b></td></tr>");	
		}
		}
		else{
			email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append("<tr><td colspan=\"2\"><b style=\"color:red;\">No values found </b></td></tr>");	
			}		
		
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append("<table style=\"width:100%\" id=\"t01\"><caption> OFFBLOCK time detected by Flight Sensor greater than AIRBORNE time detected by Flight Sensor</caption><tr>"

				+ "<th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Departure Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>Off Block time</b></th> "
				+ "<th style=\"width:15%\"><b>Sensor_ATD</b></th> " 
				+ "<th style=\"width:15%\"><b>Difference between Off block time and Sensor ATD</b></th> "
				+ " </tr>");
		
		if (status0List.size()>0) {
		ResultSet result61 = DBWrapper.Connect("SELECT logid, flightnumber_departure, sensor_ATD, Off_block_time, (case when (Off_Block_Time < Sensor_ATD) then 1 else 0 end) as Status, \r\n" + 
				"CONCAT('',TIMEDIFF(Off_Block_Time, Sensor_ATD)) as difference FROM `DailyFlightSchedule_Merged` where date(IFNULL(std,mediator_std))= '"+SQL_Queries.yesterDate()+"' \r\n" + 
				"and operationunit = "+operationunit+" and flightnumber_departure regexp ('G9|AI|9I|CX|EY|FZ|G8|J9|9W|S2|SV|MI|UL|TG|UK|WY') and (sensor_atd is not null and Off_block_time is not null) order by flightnumber_departure",environment);
		while (result61.next())
		{	
			String str_LogID = result61.getString("logid");
			String str_flight_NumberDeparture= result61.getString("flightnumber_departure");
			String str_flight_SensorATD= result61.getString("sensor_atd");
			String str_flight_OffBlock= result61.getString("off_block_time");
			String str_status= result61.getString("status");
			String str_difference_between_OffBlockAndSensorATD= result61.getString("difference");
			if(str_status.contains("0")) {
				email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td>"
						+ " <td><b style=\"color:red;\">"+str_flight_NumberDeparture+"</b></td>"
						+ " <td> <b style=\"color:red;\">"+str_flight_OffBlock+"</b></td>"
						+ " <td><b style=\"color:red;\">"+str_flight_SensorATD+"</b></td> "
						+ "<td><b style=\"color:red;\">"+str_difference_between_OffBlockAndSensorATD+"</b></td></tr>");
			}
			}
		}
			else {				
				email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");
			}
		
		email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.append("</table>");
		
		HtmlReportUtil.test.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.toString());
		HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd1.toString());
		 
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");
		child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");			
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for Departure ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");
		 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledDeparture+"</b>");
		
		 ExtentTest child01 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total No. of flights Actually Departed ("+SQL_Queries.yesterDate()+") : "+totalActualDeparture+"</b>");
		 child01.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total No. of flights Actually Departed ("+SQL_Queries.yesterDate()+") : "+totalActualDeparture+"</b>");			
		 ExtentTest child001 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total No. of flights Actually Departed ("+SQL_Queries.yesterDate()+") : "+totalActualDeparture+"</b>");
		 child001.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total No. of flights Actually Departed ("+SQL_Queries.yesterDate()+") : "+totalActualDeparture+"</b>");
			 
		 			 
		 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights AIRBORNE (Detected by Flight Sensor): "+notNullSensorATD+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights AIRBORNE (Detected by Flight Sensor): "+notNullSensorATD+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights AIRBORNE (Detected by Flight Sensor): "+notNullSensorATD+"</b>");
		 child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights AIRBORNE (Detected by Flight Sensor): "+notNullSensorATD+"</b>");
		 
		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights AIRBORNE (Not Detected by Flight Sensor but detected from other data source): "+sensorATD_NullList.size()+"</b>");
		 child2.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights AIRBORNE (Not Detected by Flight Sensor but detected from other data source): "+sensorATD_NullList.size()+"</b>");
		 child22.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd2.toString());
		 	 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - OFFBLOCK (Detected by Flight Sensor): "+offBlockFromSensor+"</b>");
		 child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - OFFBLOCK (Detected by Flight Sensor): "+offBlockFromSensor+"</b>");
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights - OFFBLOCK (Detected by Flight Sensor): "+offBlockFromSensor+"</b>");
		 child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights - OFFBLOCK (Detected by Flight Sensor):: "+offBlockFromSensor+"</b>");
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights - OFFBLOCK (Not Detected by Flight Sensor but detected from other data source): "+list_offBlockFromOtherDataSource.size()+"</b>");
		 child4.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.toString());
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights - OFFBLOCK (Not Detected by Flight Sensor but detected from other data source):: "+list_offBlockFromOtherDataSource.size()+"</b>");
		 child44.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd3.toString());
		 
		ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights OFFBLOCK time detected by Flight Sensor less than AIRBORNE: "+status1List.size()+"</b>");
		child5.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights OFFBLOCK time detected by Flight Sensor less than AIRBORNE: "+status1List.size()+"</b>");			
		ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights OFFBLOCK time detected by Flight Sensor less than AIRBORNE: "+status1List.size()+"</b>");
		child55.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights OFFBLOCK time detected by Flight Sensor less than AIRBORNE: "+status1List.size()+"</b>");
			 	 
		 ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights OFFBLOCK time detected by Flight Sensor greater than AIRBORNE: "+status0List.size()+"</b>");
		 child6.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.toString());
		 ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights OFFBLOCK time detected by Flight Sensor greater than AIRBORNE : "+status0List.size()+"</b>");
		 child66.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATD_For_AISATS_Hyd4.toString());

		 HtmlReportUtil.test.appendChild(child0).appendChild(child01).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6);
		 HtmlReportUtil.testHist.appendChild(child00).appendChild(child001).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66);
		DBWrapper.dbConnectionClose();
		}
				
	}

	
